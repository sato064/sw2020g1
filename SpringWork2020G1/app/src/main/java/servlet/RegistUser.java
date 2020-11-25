package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        response.setContentType("text/html; charset=utf-8");

        String[] errorMessage = {"null","null","null","null"};
        request.setAttribute("errorMessage",errorMessage);
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String password_con = request.getParameter("password_con");

        // コンソールに確認するために出力
        System.out.println("取得した文字列は" + id + "です！");
        System.out.println("取得した文字列は" + name + "です！");
        System.out.println("取得した文字列は" + password + "です！");
        System.out.println("idの長さは" + id.length() + "です！");

        String[] errorMessage = {"null","null","null","null"};
        if(name.length()<=20 && (8<=id.length() && id.length()<=16) && (8<=password.length() && password.length()<=16) && password.equals(password_con)){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            
            String pwd_hash = encoder.encode(password);
            System.out.println(pwd_hash);
            
            // Userオブジェクトに情報を格納
            User user = new User(id, name, pwd_hash);
            
            // StudentManagerオブジェクトの生成
            UserManager manager = new UserManager();
            
            // 登録
            int count=manager.registUser(user);
            
            if(count==1){
                // response.sendRedirect("/WEB-INF/registUserSuccess.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
                dispatcher.forward(request, response);
            }else{
                errorMessage[1] = "ユーザIDが重複しています";
                request.setAttribute("errorMessage",errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
                dispatcher.forward(request, response);
            }
            
        }else if(name.length()<=20 && (8<=id.length() && id.length()<=16) && (8<=password.length() && password.length()<=16)){
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (8<=id.length() && id.length()<=16) && password.equals(password_con)){
            errorMessage[2] = "パスワードは全角半角8文字以上6文字以下で記載してください。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (8<=password.length() && password.length()<=16) && password.equals(password_con)){
            errorMessage[1] = "ユーザIDは全角半角8文字以上6文字以下で記載してください。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=id.length() && id.length()<=16) && (8<=password.length() && password.length()<=16) && password.equals(password_con)){
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (8<=id.length() && id.length()<=16)){
            errorMessage[2] = "パスワードは全角半角8文字以上6文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && (8<=password.length() && password.length()<=16)){
            errorMessage[1] = "ユーザIDは全角半角8文字以上6文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20 && password.equals(password_con)){
            errorMessage[1] = "ユーザIDは全角半角8文字以上6文字以下で記載してください。";
            errorMessage[2] = "パスワードは全角半角8文字以上6文字以下で記載してください。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=id.length() && id.length()<=16) && (8<=password.length() && password.length()<=16)){
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=id.length() && id.length()<=16) && password.equals(password_con)){
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[2] = "パスワードは全角半角8文字以上6文字以下で記載してください。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=password.length() && password.length()<=16) && password.equals(password_con)){
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "ユーザIDは全角半角8文字以上6文字以下で記載してください。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if(name.length()<=20){
            errorMessage[1] = "ユーザIDは全角半角8文字以上6文字以下で記載してください。";
            errorMessage[2] = "パスワードは全角半角8文字以上6文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=id.length() && id.length()<=16)){
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[2] = "パスワードは全角半角8文字以上6文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if((8<=password.length() && password.length()<=16)){
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "ユーザIDは全角半角8文字以上6文字以下で記載してください。";
            errorMessage[3] = "確認用パスワードが一致しません。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else if(password.equals(password_con)){
            errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
            errorMessage[1] = "ユーザIDは全角半角8文字以上6文字以下で記載してください。";
            errorMessage[2] = "パスワードは全角半角8文字以上6文字以下で記載してください。";
            request.setAttribute("errorMessage",errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
            dispatcher.forward(request, response);
        }else {
        errorMessage[0] = "氏名は全角1文字以上20文字以下で記載してください。";
        errorMessage[1] = "ユーザIDは全角半角8文字以上6文字以下で記載してください。";
        errorMessage[2] = "パスワードは全角半角8文字以上6文字以下で記載してください。";
        errorMessage[3] = "確認用パスワードが一致しません。";
        request.setAttribute("errorMessage",errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registUser.jsp");
        dispatcher.forward(request, response);
        }
    }
}