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
import beans.Task;
import beans.Project;
import beans.Participate;
import control.UserManager;
import control.ProjectManager;
import control.TaskManager;
import control.HandleManager;
import control.ParticipateManager;


//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateTask")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateTask extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        int task_id = Integer.parseInt(request.getParameter("id"));
        TaskManager tManager = new TaskManager();
        Task task = tManager.findTaskById(task_id);
        int prj_id = Integer.parseInt(request.getParameter("prj_id"));
        String prj_id_str = request.getParameter("prj_id");


        request.setAttribute("task", task);
        request.setAttribute("prj_id",prj_id_str);
        
        UserManager manager2 = new UserManager();
        List<User> userList = manager2.userList();
        ParticipateManager pManager = new ParticipateManager();
        List<Participate> participatelist = pManager.findPart(prj_id);

        List<User> joiner = new ArrayList<User>();

        //プロジェクトに参加しているUser型Listのjoinerの作成

        for(int i = 0; i < userList.size(); i++){
            for(int i2 = 0; i2 < participatelist.size(); i2++){
                if(userList.get(i).getId().equals(participatelist.get(i2).getUserId())){
                    joiner.add(userList.get(i));
                }
            }
            
        }

        request.setAttribute("userList",participatelist);

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");

        request.setAttribute("userList",userList);
        String[] errorMessage = {"null"};
        request.setAttribute("errorMessage",errorMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateTask.jsp");
        

        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String title = request.getParameter("title");
        String overview = request.getParameter("overview");
        String deadline = request.getParameter("deadline");
        String user = request.getParameter("user");
        HttpSession session = request.getSession();
        String id = request.getParameter("prj_id");
        String tid = request.getParameter("task_id");
        String str_stu =request.getParameter("status");

        int status = Integer.parseInt(str_stu);
        int prjid = Integer.parseInt(id);
        int task_id = Integer.parseInt(tid);



        String joiners[] = request.getParameterValues("user");


        HandleManager hManager = new HandleManager();



        /*
        ここでdeadlineの型変換をする

        */

        // コンソールに確認するために出力
        System.out.println("取得した文字列は" + title + "です！");
        System.out.println("取得した文字列は" + overview + "です！");
        System.out.println("取得した文字列は" + id + "です！");
        // projectオブジェクトに情報を格納
        Task task = new Task(task_id,prjid,title,overview,deadline,status,false);
    
        // StudentManagerオブジェクトの生成
        TaskManager manager = new TaskManager();
    
        // 登録
        manager.updateTask(task);

        int taskid = task_id;
        hManager.deleteHandle(taskid);
        


        for(int i=0;i<joiners.length;i++){
            hManager.registHandle(taskid,joiners[i]);
            
        }



        
        //int tsk_id = manager.getTask(task);

        //particiateじゃないです
        //Participate pt = new Participate(id,tsk_id);
        //ParticipateManager party = new ParticipateManager();
        //party.createParticipate(pt);

    
        // 成功画面を表示する
        //response.sendRedirect("/SpringWork2020G1/Main");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createTaskSuccess.jsp");
        dispatcher.forward(request, response);
        
    }
}