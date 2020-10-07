package controller;

import model.Comment;
import model.Project;
import service.CommentService;
import service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteComment")
public class DeleteComment extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DeleteComment(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Comment comment = new Comment();
            comment.setCommentId(Integer.parseInt(request.getParameter("commentId")));
            CommentService commentService = new CommentService();
            commentService.deleteComment(comment);

            response.setContentType("text/html; charset=utf-8");
            response.sendRedirect("/TodePrj/DisplayProject");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}