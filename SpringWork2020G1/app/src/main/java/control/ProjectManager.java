package control;

import java.sql.Connection;
import java.util.List;

import beans.Project;
import dao.ProjectDAO;

import javax.servlet.http.HttpServlet;

public class ProjectManager extends HttpServlet{
    private Connection connection = null;

    public ProjectManager(){

    }
    public List<Project> projectList() {
        ProjectDAO projectDAO = new ProjectDAO();
        this.connection = projectDAO.createConnection();
        System.out.println("QUERRY START");
        List<Project> projectList = projectDAO.projectList(this.connection);
        projectDAO.closeConnection(this.connection);
        this.connection = null;
        return projectList;
    }

    public void registProject(Project project){
        ProjectDAO projectDAO = new ProjectDAO();
        this.connection = projectDAO.createConnection();
        projectDAO.registProject(project,this.connection);
        projectDAO.closeConnection(this.connection);
        this.connection = null;
    }

    public Project findProject(int id){
        ProjectDAO projectDAO = new ProjectDAO();
        this.connection = projectDAO.createConnection();
        Project project = projectDAO.findProject(id, this.connection);
        projectDAO.closeConnection(this.connection);
        this.connection = null;
        return project;

    }

    public int getProject(Project project){
        ProjectDAO projectDAO = new ProjectDAO();
        this.connection = projectDAO.createConnection();
        int prj_id = projectDAO.getProject(project,this.connection);
        projectDAO.closeConnection(this.connection);
        this.connection = null;
        return prj_id;
    }

    public void deleteProject(Project project){
        ProjectDAO projectDAO = new ProjectDAO();
        this.connection = projectDAO.createConnection();
        projectDAO.deleteProject(project,this.connection);
        projectDAO.closeConnection(this.connection);
        this.connection = null;
    }
}