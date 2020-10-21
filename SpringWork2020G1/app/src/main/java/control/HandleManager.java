package control;

import java.sql.Connection;
import java.util.List;

import beans.Handle;
import beans.Project;
import beans.Task;
import dao.TaskDAO;
import dao.HandleDAO;
import beans.Handle;

import javax.servlet.http.HttpServlet;

public class HandleManager extends HttpServlet{
    private Connection connection = null;

    public HandleManager(){

    }
    public List<Handle> findHandle() {
        HandleDAO handleDAO = new HandleDAO();
        this.connection = handleDAO.createConnection();
        List<Handle> handleList = handleDAO.handleList(this.connection);
        handleDAO.closeConnection(this.connection);
        this.connection = null;
        return handleList;
    }
    public void deleteHandle(int taskid) {
        HandleDAO handleDAO = new HandleDAO();
        this.connection = handleDAO.createConnection();
        handleDAO.deleteHandle(taskid, this.connection);
        handleDAO.closeConnection(this.connection);
        this.connection = null;

    }
    public List<Handle> handleList(int taskid) {
        HandleDAO handleDAO = new HandleDAO();
        this.connection = handleDAO.createConnection();
        List<Handle> handleList = handleDAO.findJojner(taskid, this.connection);
        handleDAO.closeConnection(this.connection);
        this.connection = null;
        return handleList;


    }
    public void registHandle(int taskid,String userid) {
        HandleDAO handleDAO = new HandleDAO();
        this.connection = handleDAO.createConnection();
        handleDAO.registHandle(taskid, userid, this.connection);
        handleDAO.closeConnection(this.connection);
        this.connection = null;

    }
    
    
}