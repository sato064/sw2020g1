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
import java.util.List;

import beans.Review;
import control.ReviewManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/SortReview")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class SortReview extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String sort = request.getParameter("sort");

        // コンソールに確認するために出力
        System.out.println("取得したソートは" + sort + "です！");

        ReviewManager manager = new ReviewManager();
        List<Review> list = manager.findAll(sort); //Reviewのリストの全レコードの取得
        request.setAttribute("list", list); //Reviewのリストをlistという名前でリクエストスコープに保存
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/showReview.jsp");
        dispatcher.forward(request, response);

    }
}
