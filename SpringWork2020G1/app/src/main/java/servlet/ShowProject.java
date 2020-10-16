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
import sun.java2d.pipe.PixelToParallelogramConverter;
import control.ProjectManager;
import control.ParticipateManager;


//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/ShowProject")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class ShowProject extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        String Prjid_str = request.getParameter("id");
        System.out.println(Prjid_str);
        int prjid = Integer.parseInt(Prjid_str);
        ProjectManager manager = new ProjectManager();
        Project project = manager.findProject(prjid);
        request.setAttribute("finded_project", project);
        //System.out.println(project);


        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/showProject.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        
    }
}