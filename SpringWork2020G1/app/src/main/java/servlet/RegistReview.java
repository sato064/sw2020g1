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

import beans.Review;
import control.ReviewManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistReview")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistReview extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registReviewSuccess.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int restaurant_id = Integer.parseInt(request.getParameter("restaurant_id"));
        String information = request.getParameter("information");
        String scene = request.getParameter("scene");

        // コンソールに確認するために出力
        System.out.println("取得した文字列は" + user_id + "です！");
        System.out.println("取得した文字列は" + restaurant_id + "です！");
        System.out.println("取得した文字列は" + information + "です！");
        System.out.println("取得した文字列は" + scene + "です！");
        
        // studentオブジェクトに情報を格納
        Review review = new Review(information, 0 , scene, user_id, restaurant_id);
    
        // StudentManagerオブジェクトの生成
        ReviewManager manager = new ReviewManager();
    
        // 登録
        manager.registReview(review);
    
        // 成功画面を表示する
        response.sendRedirect("/SpringWork2020G1/RegistReview");

    }
}
