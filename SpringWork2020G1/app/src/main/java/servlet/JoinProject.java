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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import beans.User;
import control.ParticipateManager;
import control.UserManager;
import sun.net.www.protocol.http.AuthCache;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/JoinProject")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class JoinProject extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String Prjid_str = request.getParameter("id");
        System.out.println(Prjid_str);
        int prjid = Integer.parseInt(Prjid_str);
        String id = (String)session.getAttribute("UserId");

        ParticipateManager manager = new ParticipateManager();
        manager.joinProject(id, prjid);
        response.sendRedirect("./Main");
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

    }
}