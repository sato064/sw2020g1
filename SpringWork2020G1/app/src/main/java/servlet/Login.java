import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import beans.Project;
import beans.User;
import control.ProjectManager;
import control.UserManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/Login")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;
    List<Project> projects = new ArrayList<Project>();

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        
        String[] errorMessage = {"null"};
        request.setAttribute("errorMessage",errorMessage);
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        // RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registReview.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String loginid = request.getParameter("loginid");
        String password = request.getParameter("password");

        // コンソールに確認するために出力
        System.out.println("取得した文字列は" + loginid + "です！");
        System.out.println("取得した文字列は" + password + "です！");


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserManager manager = new UserManager();
        ProjectManager pManager = new ProjectManager();
    

        String pwd_hash = encoder.encode(password);
        //System.out.println(manager.getPass(loginid));
        boolean login = encoder.matches(password,manager.getPass(loginid));

        if(login){
            User login_user = manager.getUser(loginid);
            HttpSession session = request.getSession(true);
            session.setAttribute("login_user", login_user);
            System.out.println(login_user.getName());
            session.setAttribute("UserName", login_user.getName());
            session.setAttribute("UserId", login_user.getId());
            System.out.println((String)session.getAttribute("UserName"));
            System.out.println(session.getId());
            System.out.println("------------------------");
            //projects = pManager.projectList();
            pManager.delayChecker();
            //session.setAttribute("ProjectList",projects);
            //System.out.println(projects);
            System.out.println("session set lists");

            response.sendRedirect("./Main");
        }else{
            String[] errorMessage = new String[1];
            errorMessage[0] = "ユーザIDまたはパスワードが違います。";
            request.setAttribute("errorMessage",errorMessage);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        }

    }
}