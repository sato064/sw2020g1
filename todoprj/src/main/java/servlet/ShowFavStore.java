//--------------------------------
// SearchInfo.java
//--------------------------------
//　自分が格納されているフォルダ名
package servlet;

//自分が格納されているフォルダの外にある必要なクラス

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Store;
import control.StoreListManager;

@WebServlet("/ShowFavStore")
// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class ShowFavStore extends HttpServlet {

    private static final long serialVersionUID = 1L;

    List<Store> stores = new ArrayList<Store>();
    List<String> storenames = new ArrayList<String>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/SearchInfo.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        // requestオブジェクトから登録情報の取り出し
        String usr_name = (String) session.getAttribute("UserName");


        // StoreListManagerオブジェクトの生成
        StoreListManager manager = new StoreListManager();

        // 学生の検索
        storenames = manager.findFavStores(usr_name);
        // requestオブジェクトにオブジェクトを登録
        request.setAttribute("StoreList", storenames);
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        System.out.println(stores);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/FindedFavStores.jsp");

        dispatcher.forward(request, response);
    }
}
