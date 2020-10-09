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
@WebServlet("/PlusGood")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class PlusGood extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        response.sendRedirect("/TodoPrj/SearchInfo");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        // her = request.getRequestDispatcher("/WEB-INF/registReview.jsp");
        // dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        int id =Integer.parseInt(request.getParameter("id"));

        // コンソールに確認するために出力
        System.out.println("取得した数字は" + id + "です！");
        Review review = new Review(id);
        
        ReviewManager reviewManager = new ReviewManager();
        reviewManager.countGood(review);

        // 成功画面を表示する
        response.sendRedirect("/TodoPrj/PlusGood");
    }
}