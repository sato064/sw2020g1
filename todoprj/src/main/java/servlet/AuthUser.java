//--------------------------------
//	AuthUser.java
//--------------------------------
//　自分が格納されているフォルダ名
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

import beans.User;
import control.UserManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/AuthUser")


// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class AuthUser extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private boolean check = false;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String usr_name = request.getParameter("usr_name");
        String usr_pass = request.getParameter("usr_pass");

        if (usr_name == "" || usr_pass == "") {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/false.jsp");
            dispatcher.forward(request, response);
        }


        // userのオブジェクトに情報を格納
        User user = new User(usr_name, usr_name);

        // UserManagerオブジェクト
        UserManager manager = new UserManager();

        // ユーザ存在の検索
        check = manager.authUser(usr_name,usr_pass);

        System.out.println(check);

        // 認証したらホーム画面へ遷移
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        if (check) {
            HttpSession session = request.getSession(true);
            session.setAttribute("UserName", usr_name);
            session.setAttribute("User", user);
            request.setAttribute("User", user);
            request.setAttribute("username", user.getUsertName());
            System.out.println(user.getUsertName());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/false.jsp");
            dispatcher.forward(request, response);

        }


    }
}
