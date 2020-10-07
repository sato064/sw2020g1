package controller;

import model.Comment;
import service.CommentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistComment")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistComment extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // requestオブジェクトから登録情報の取り出し
            Comment comment = new Comment();
            comment.setProjectId(Integer.parseInt(request.getParameter("projectId")));
            comment.setCommentAuthorId(request.getParameter("commentAuthorId"));
            comment.setCommentDetail(request.getParameter("commentDetail"));
            CommentService commentService = new CommentService();
            commentService.registComment(comment);

            response.setContentType("text/html; charset=utf-8");
            response.sendRedirect("/TodePrj/DisplayProject");
        }catch (Exception e) {

        }
    }
}

