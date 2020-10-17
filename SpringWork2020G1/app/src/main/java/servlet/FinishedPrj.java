package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import beans.User;
import beans.Project;
import beans.Participate;
import control.UserManager;
import control.ProjectManager;
import control.ParticipateManager;


//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/FinishedPrj")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class FinishedPrj extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        
        ProjectManager manager = new ProjectManager();
        List<Project> projectList = manager.finprojectList(); //Projectのリストの全レコードの取得
        request.setAttribute("projectList", projectList); //ProjectのリストをprojctListという名前
        
        ParticipateManager manager1 = new ParticipateManager();
        List<Participate> participateList = manager1.participateList();
        request.setAttribute("participateList", participateList);

        UserManager manager2 = new UserManager();
        List<User> userList = manager2.userList();
        request.setAttribute("userList", userList);

        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/finished.jsp");
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
        
        // Userオブジェクトに情報を格納
        //User user = new User(loginid, null, password);
    
        // StudentManagerオブジェクトの生成
        UserManager manager = new UserManager();
    
        // ログイン
        User login_user = manager.login(loginid,password);

        if(login_user != null){
            HttpSession session = request.getSession();
            session.setAttribute("login_user", login_user);
            System.out.println("ログイン成功");

            ProjectManager manager1 = new ProjectManager();
            List<Project> projectList = manager1.projectList();
            request.setAttribute("projectList", projectList);
            
            ParticipateManager manager2 = new ParticipateManager();
            List<Participate> participateList = manager2.participateList();
            request.setAttribute("participateList", participateList);

            UserManager manager3 = new UserManager();
            List<User> userList = manager3.userList();
            request.setAttribute("userList", userList);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/loginError.jsp");
            dispatcher.forward(request, response);
        }

    }
}