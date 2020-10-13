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
    public int getProject(Project project){
        ProjectDAO projectDAO = new ProjectDAO();
        this.connection = projectDAO.createConnection();
        int prj_id = projectDAO.getProject(project,this.connection);
        projectDAO.closeConnection(this.connection);
        this.connection = null;
        return prj_id;
    }

    public List findAllProject(){
        ProjectDAO projectDAO = new ProjectDAO();
        this.connection = projectDAO.createConnection();
        List<Project> projectList = projectDAO.findAll(this.connection);
        projectDAO.closeConnection(this.connection);
        this.connection = null;
        return projectList;
    }

    public void deleteProject(Project project){
        ProjectDAO projectDAO = new ProjectDAO();
        this.connection = projectDAO.createConnection();
        projectDAO.deleteProject(project,this.connection);
        projectDAO.closeConnection(this.connection);
        this.connection = null;
    }
}