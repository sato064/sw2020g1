package service;

import java.sql.Connection;
import java.util.List;

import model.Project;
import dao.ProjectDao;

import javax.servlet.http.HttpServlet;

public class ShopService extends HttpServlet{
    private Connection connection = null;

    public ProjectService(){
        
    }
    public List<Project> projectList() {
        ProjectDao projectDao = new ProjectDao();
        this.connection = projectDao.createConnection();
        List<Project> projectList = projectDao.projectList(this.connection);
        projectDao.closeConnection(this.connection);
        this.connection = null;
        return projectList;
    }

    public void registProject(Project project){
        ProjectDao projectDao = new ProjectDao();
        this.connection = projectDao.createConnection();
        projectDao.registProject(project,this.connection);
        projectDao.closeConnection(this.connection);
        this.connection = null;
    }

    public void deleteProject(Project project){
        ProjectDao projectDao = new ProjectDao();
        this.connection = projectDao.createConnection();
        projectDao.deleteProject(project,this.connection);
        projectDao.closeConnection(this.connection);
        this.connection = null;
    }
}
