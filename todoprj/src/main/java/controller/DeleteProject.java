package controller;


import model.Project;
import model.Student;
import service.ProjectService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteProject")
public class DeleteProject extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DeleteProject(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Project project = new Project();
            project.setShopID(Integer.parseInt(request.getParameter("shopId")));
            ProjectService projectService = new ProjectService();
            projectService.deleteProject(project);

            response.setContentType("text/html; charset=utf-8");
            response.sendRedirect("/TodoPrj/DisplayProject");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
