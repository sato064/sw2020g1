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

import beans.User;
import control.UserManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistUser")


// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        manager.registUser(usr_name, usr_pass);


        // 認証したらホーム画面へ遷移
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        request.setAttribute("User", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);




    }
}
