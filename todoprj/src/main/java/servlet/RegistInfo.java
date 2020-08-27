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

import beans.Store;
import control.StoreManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistInfo")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistInfo extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private boolean isExist = false;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/RegistInfo.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String str_name = request.getParameter("str_name");
        String str_info = request.getParameter("str_info");




        // storeオブジェクトに情報を格納
        Store store = new Store(str_name, str_info);

        // StoreManagerオブジェクトの生成
        StoreManager manager = new StoreManager();

        isExist = manager.checkStore(str_name);

        if (!isExist) {

            // 登録
            manager.registStore(store);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/QueryFin.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/StoreError.jsp");
            dispatcher.forward(request, response);


        }
        
       
        

    }
}
