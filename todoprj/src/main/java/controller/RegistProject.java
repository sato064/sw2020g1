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
import controller.SessionFilter;

@WebServlet("/RegistProject")

public class RegistProject extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public RegistProject(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html; charset=utf-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registProject.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Project project = new Project();
            project.setProjectNAME(request.getParameter("projectName"));
            project.setProjectINFO(request.getParameter("projectInfo"));
            project.setDetailedINFO(request.getParameter("detailedInfo"));
            project.setAuthorID(request.getParameter("authorId"));

            ProjectService projectService = new ProjectService();
            projectService.registProject(project);

            ProjectService projectService1 = new ProjectService();
            List<Project> projectList = projectService1.projectList();

            request.setAttribute("projectList",projectList);

            response.setContentType("text/html; charset=utf-8");
            response.sendRedirect("/TodoPrj/DisplayProject");

        } catch (Exception e) {
        }
    }
}
