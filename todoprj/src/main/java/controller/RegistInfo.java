//　自分が格納されているフォルダ名
package controller;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import service.UserService;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistInfo")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistInfo extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/regist.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String stu_name = request.getParameter("name");
        String stu_id = request.getParameter("mail");
        String stu_pass = request.getParameter("pass");

        // コンソールに確認するために出力
        System.out.println("取得した文字列は" + stu_id + "です！");
        System.out.println("取得した文字列は" + stu_name + "です！");
        System.out.println("取得した文字列は" + stu_pass + "です！");

        // studentオブジェクトに情報を格納
        Student student = new Student(stu_id, stu_name, stu_pass);

        // StudentManagerオブジェクトの生成
        UserService userService = new UserService();

        // 登録
        userService.registStudent(student);

        // 成功画面を表示する
        // System.out.println("OK牧場");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registStudentSuccess.jsp");
        dispatcher.forward(request, response);
    }
}
