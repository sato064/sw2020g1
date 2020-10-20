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
import control.UserManager;
import sun.net.www.protocol.http.AuthCache;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateUser")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        String[] errorMessage = {"null","null","null","null"};
        request.setAttribute("errorMessage",errorMessage);
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("UserId");
        System.out.println("UserID");
        System.out.println(id);

        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("UserId");
        String name = request.getParameter("name");
        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");
        String new_password_con = request.getParameter("new_password_con");

        System.out.println("-----------------------------------");
        System.out.println(name);
        System.out.println(old_password);
        System.out.println(new_password);
        System.out.println(new_password_con);

        UserManager uManager = new UserManager();
        String a_pass = uManager.getPass(id);

        System.out.println(id);
        System.out.println(a_pass);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean auth = encoder.matches(old_password,a_pass);

        System.out.println(auth);

        if(name.length()<=20 && (8<=new_password.length() && new_password.length()<=16) && (new_password.equals(new_password_con) == true) && auth){
            //パスワード不一致警告
        //     RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateError.jsp");
        //     dispatcher.forward(request, response);

        // }else{

        //認証
        // UserManager uManager = new UserManager();
        // String a_pass = uManager.getPass(id);

        // System.out.println(id);
        // System.out.println(a_pass);

        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // boolean auth = encoder.matches(old_password,a_pass);

        // System.out.println(auth);


        // if(!auth){
        //     //パスワード違い警告
        //     System.out.println("why");
        //     RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateError.jsp");
        //     dispatcher.forward(request, response);
        // }
        // else{
            //変更処理

            String pwd_hash = encoder.encode(new_password);
            User user = new User(id,name,pwd_hash);
            uManager.updateUser(user);
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUserSuccess.jsp");
            dispatcher.forward(request, response);
            // response.sendRedirect("/WEB-INF/updateUserSuccess.jsp");

        }else if(name.length()<=20 && (8<=new_password.length() && new_password.length()<=16) && (new_password.equals(new_password_con) == true) && (!auth)){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "null";
            errorMessage[1] = "旧パスワードが間違っています。";
            errorMessage[2] = "null";
            errorMessage[3] = "null";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (8<=new_password.length() && new_password.length()<=16) && (new_password.equals(new_password_con) == false) && auth){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "null";
            errorMessage[1] = "null";
            errorMessage[2] = "null";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (new_password.equals(new_password_con) == true) && auth){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "null";
            errorMessage[1] = "null";
            errorMessage[2] = "パスワードは全角半角8文字以上16文字以下で記載してください。";
            errorMessage[3] = "null";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=new_password.length() && new_password.length()<=16) && (new_password.equals(new_password_con) == true) && auth){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "null";
            errorMessage[2] = "null";
            errorMessage[3] = "null";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (8<=new_password.length() && new_password.length()<=16)){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "null";
            errorMessage[1] = "旧パスワードが間違っています。";
            errorMessage[2] = "null";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (new_password.equals(new_password_con) == true) && !(auth)){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "null";
            errorMessage[1] = "旧パスワードが間違っています。";
            errorMessage[2] = "パスワードは全角半角8文字以上16文字以下で記載してください。";
            errorMessage[3] = "null";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (new_password.equals(new_password_con) == false) && auth){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "null";
            errorMessage[1] = "null";
            errorMessage[2] = "パスワードは全角半角8文字以上16文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=new_password.length() && new_password.length()<=16) && (new_password.equals(new_password_con) == true) && !(auth)){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "旧パスワードが間違っています。";
            errorMessage[2] = "null";
            errorMessage[3] = "null";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=new_password.length() && new_password.length()<=16) && (new_password.equals(new_password_con) == false) && auth){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "null";
            errorMessage[2] = "null";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if((new_password.equals(new_password_con) == true) && auth){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "null";
            errorMessage[2] = "パスワードは全角半角8文字以上16文字以下で記載してください。";
            errorMessage[3] = "null";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (new_password.equals(new_password_con) == false) && !(auth)){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "null";
            errorMessage[1] = "旧パスワードが間違っています。";
            errorMessage[2] = "パスワードは全角半角8文字以上16文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=new_password.length() && new_password.length()<=16) && (new_password.equals(new_password_con) == false) && !(auth)){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "旧パスワードが間違っています。";
            errorMessage[2] = "null";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if((new_password.equals(new_password_con) == true) && !(auth)){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "旧パスワードが間違っています。";
            errorMessage[2] = "パスワードは全角半角8文字以上16文字以下で記載してください。";
            errorMessage[3] = "null";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if((new_password.equals(new_password_con) == false) && auth){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "null";
            errorMessage[2] = "パスワードは全角半角8文字以上16文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }else if((new_password.equals(new_password_con) == false) && !(auth)){
            System.out.println("エラーエラー");
            String[] errorMessage = new String[4];
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "旧パスワードが間違っています。";
            errorMessage[2] = "パスワードは全角半角8文字以上16文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            dispatcher.forward(request, response);
        }
    

    }
}