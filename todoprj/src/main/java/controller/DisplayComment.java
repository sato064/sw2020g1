package controller;

import model.Comment;
import model.Project;
import service.CommentService;
import service.ProjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet("/DisplayComment")
public class DisplayComment extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DisplayComment(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html; charset=utf-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/showPosted.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Project project = new Project();
            project.setAuthorNAME(request.getParameter("authorName"));
            project.setProjectID(Integer.parseInt(request.getParameter("projectId")));
            project.setProjectNAME(request.getParameter("projectName"));
            project.setProjectINFO(request.getParameter("projectInfo"));
            project.setDetailedINFO(request.getParameter("detailedInfo"));
            request.setAttribute("project",project);

            Comment comment = new Comment();
            CommentService commentService = new CommentService();
            List<Comment> commentList = commentService.commentList();
            Collections.reverse(commentList);
            request.setAttribute("commentList",commentList);

            response.setContentType("text/html; charset=utf-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/showComment.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){
        }
    }
}
