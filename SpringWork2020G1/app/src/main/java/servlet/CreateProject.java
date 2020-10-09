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

import beans.Restaurant;
import control.RestaurantManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/CreateProject")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class CreateProject extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createRestaurant.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String name = request.getParameter("name");

        // コンソールに確認するために出力
        System.out.println("取得した文字列は" + name + "です！");
        
        // projectオブジェクトに情報を格納
        Project project = new Project(name);
    
        // StudentManagerオブジェクトの生成
        ProjectManager manager = new ProjectManager();
    
        // 登録
        manager.createProject(project);
    
        // 成功画面を表示する
        response.sendRedirect("/TodoPrj/CreateProjectSuccess");

    }
}
