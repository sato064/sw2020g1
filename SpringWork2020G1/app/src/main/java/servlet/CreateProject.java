package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.User;
import beans.Project;
import beans.Participate;
import control.UserManager;
import control.ProjectManager;
import control.ParticipateManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/CreateProject")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class CreateProject extends HttpServlet {

    private static final long serialVersionUID = 1L;
    

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        UserManager userManager = new UserManager();
        List<User> userList = userManager.userList();
        request.setAttribute("userList",userList);

        String[] errorMessage = {"null"};
        request.setAttribute("errorMessage",errorMessage);

        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createProject.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String title = request.getParameter("title");
        String overview = request.getParameter("overview");
        String deadline = request.getParameter("deadline");
        String user[] = request.getParameterValues("user");

        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("UserId");
        Object hostid = session.getAttribute("UserId");
        String hostIdStr = hostid.toString();

        /*
        ここでdeadlineの型変換をする

        */

        // コンソールに確認するために出力
        System.out.println("取得した文字列は" + title + "です！");
        System.out.println("取得した文字列は" + overview + "です！");
        System.out.println("取得した文字列は" + deadline + "です！");
        System.out.println("取得した文字列は" + hostIdStr + "です！");
        // projectオブジェクトに情報を格納
        Project project = new Project(0,title,overview,hostIdStr,deadline,0,false);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(date);
        String[] date1 = deadline.split("-",0);
        String[] date2 = today.split("-",0);
    
        int check = 0;
        if(date1[0].compareTo(date2[0])>0){
            check = 1;
        }else if(date1[0].compareTo(date2[0])==0){
            if(date1[1].compareTo(date2[1])>0){
                check = 1;
            }else if(date1[1].compareTo(date2[1])==0){
                if(date1[2].compareTo(date2[2])>=0){
                    check =1;
                }else{
                    check =0;
                }
            }else{
                check =0;
            }
        }else{
            check =0;
        }

        if(check ==1){
            ProjectManager manager = new ProjectManager();
            manager.registProject(project);
            int prj_id = manager.getProject(project);
            Participate pt = new Participate(id,prj_id);
            ParticipateManager party = new ParticipateManager();
            party.createParticipate(pt);
            for(int i=0;i<user.length;i++){
                pt = new Participate(user[i],prj_id);
                party = new ParticipateManager();
                party.createParticipate(pt);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createProjectSuccess.jsp");
            dispatcher.forward(request, response);
        }else{
            String[] errorMessage = new String[1];
            errorMessage[0] = "期日が違います。";
            request.setAttribute("errorMessage",errorMessage);

            UserManager userManager = new UserManager();
            List<User> userList = userManager.userList();
            request.setAttribute("userList",userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createProject.jsp");
            dispatcher.forward(request, response);
        }
    }
}