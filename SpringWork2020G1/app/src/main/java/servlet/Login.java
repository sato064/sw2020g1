//--------------------------------
//	RegistInfo.java
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
import java.util.List;

import beans.User;
import beans.Restaurant;
import control.UserManager;
import control.RestaurantManager;


//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/Login")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        
        //RestaurantManager manager = new RestaurantManager();
        //List<Restaurant> list = manager.findAll(); //Restaurantのリストの全レコードの取得
        //request.setAttribute("list", list); //Restaurantのリストをlistという名前でリクエストスコープに保存
        
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
        
        // Userオブジェクトに情報を格納
        //User user = new User(loginid, null, password);
    
        // StudentManagerオブジェクトの生成
        UserManager manager = new UserManager();
    
        // ログイン
        User login_user = manager.login(loginid,password);

        if(login_user != null){
            HttpSession session = request.getSession(true);
            session.setAttribute("login_user", login_user);
            System.out.println(login_user.getName());
            session.setAttribute("UserName", login_user.getName());
            System.out.println((String)session.getAttribute("UserName"));
            System.out.println(session.getId());

            response.sendRedirect("./Main");
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/loginError.jsp");
            dispatcher.forward(request, response);
        }

    }
}
