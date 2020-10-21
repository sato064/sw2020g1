package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Task;
import beans.User;
import beans.Participate;
import beans.Project;
import control.TaskManager;
import control.UserManager;
import dao.HandleDAO;
import control.HandleManager;
import jdk.internal.agent.resources.agent;
import jdk.internal.jshell.tool.resources.l10n;
import control.ParticipateManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/CreateTask")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class CreateTask extends HttpServlet {

    private static final long serialVersionUID = 1L;
    

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        String Prjid_str = request.getParameter("id");
        System.out.println(Prjid_str);
        int prj_id = Integer.parseInt(Prjid_str);
        request.setAttribute("PrjId",Prjid_str);
        ParticipateManager pManager = new ParticipateManager();
        List<Participate> participatelist = pManager.findPart(prj_id);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(date);
        System.out.println(today);

        request.setAttribute("today",today);

        

        UserManager manager2 = new UserManager();
        List<User> userList = manager2.userList();

        List<User> joiner = new ArrayList<User>();

        //プロジェクトに参加しているUser型Listのjoinerの作成

        for(int i = 0; i < userList.size(); i++){
            for(int i2 = 0; i2 < participatelist.size(); i2++){
                if(userList.get(i).getId().equals(participatelist.get(i2).getUserId())){
                    joiner.add(userList.get(i));
                }
            }
            
        }
        request.setAttribute("userList",joiner);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createTask.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        String Prjid_str = request.getParameter("id");


        // requestオブジェクトから登録情報の取り出し
        String title = request.getParameter("title");
        String overview = request.getParameter("overview");
        String deadline = request.getParameter("deadline");
        String user = request.getParameter("user");
        HttpSession session = request.getSession();
        String id = request.getParameter("prj_id");
        String str_stu =request.getParameter("status");
        int status = Integer.parseInt(str_stu);
        System.out.println(id);
        int prjid = Integer.parseInt(id);
        Object hostid = session.getAttribute("UserId");
        String hostIdStr = hostid.toString();
        String joiners[] = request.getParameterValues("user");


        HandleManager hManager = new HandleManager();



        /*
        ここでdeadlineの型変換をする

        */

        // コンソールに確認するために出力
        System.out.println("取得した文字列は" + title + "です！");
        System.out.println("取得した文字列は" + overview + "です！");
        System.out.println("取得した文字列は" + deadline + "です！");
        System.out.println("取得した文字列は" + hostIdStr + "です！");
        System.out.println("取得した文字列は" + id + "です！");
        // projectオブジェクトに情報を格納
        Task task = new Task(0,prjid,title,overview,deadline,status,false);
    
        // StudentManagerオブジェクトの生成
        TaskManager manager = new TaskManager();
    
        // 登録
        manager.registTask(task);

        int taskid = manager.findTaskByName(title);

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