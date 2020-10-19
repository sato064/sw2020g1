package servlet;

//自分が格納されているフォルダの外にある必要なクラス

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
@WebServlet("/UpdateProject")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateProject extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        String id_str = request.getParameter("id");
        int prj_id = Integer.parseInt(id_str);
        ProjectManager manager = new ProjectManager();
        Project project = manager.findProject(prj_id);
        request.setAttribute("project", project);


        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        UserManager userManager = new UserManager();
        List<User> userList = userManager.userList();
        request.setAttribute("userList",userList);
        String[] errorMessage = {"null","null","null"};
        request.setAttribute("errorMessage",errorMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateProject.jsp");
        

        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Project getprj = (Project)request.getAttribute("prj");
        //System.out.println(getprj);
        

        //request.getParameter("id");
        //System.out.println("---------------");
        //System.out.println(id_str);
        //int prj_id = Integer.parseInt(id_str);

        


        String title = request.getParameter("title");
        String overview = request.getParameter("overview");
        String deadline = request.getParameter("deadline");
        String user[] = request.getParameterValues("user");
        String prj_id_s = request.getParameter("prjid");
        int prj_id = Integer.parseInt(prj_id_s);

        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("UserId");
        Object hostid = session.getAttribute("UserId");
        String hostIdStr = hostid.toString();


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

            Project project = new Project(prj_id,title,overview,hostIdStr,deadline,0,false);
            ProjectManager manager = new ProjectManager();
            manager.updateProject(project);
            int prj_id2 = manager.getProject(project);
            ParticipateManager party = new ParticipateManager();
            party.punishMenber(prj_id);
            Participate pt = new Participate(id,prj_id2);
            party.createParticipate(pt);
            for(int i=0;i<user.length;i++){
                pt = new Participate(user[i],prj_id2);
                party = new ParticipateManager();
                party.createParticipate(pt);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createProjectSuccess.jsp");
            dispatcher.forward(request, response);
        }else{
            String[] errorMessage = new String[3];
            errorMessage[0] = "タイトルは30文字以内で記載してください。";
            errorMessage[1] = "概要は300文字以内で記載してください。";
            errorMessage[2] = "期日が違います。";
            request.setAttribute("errorMessage",errorMessage);

            UserManager userManager = new UserManager();
            List<User> userList = userManager.userList();
            request.setAttribute("userList",userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createProject.jsp");
            dispatcher.forward(request, response);
        }
    }
        

}
