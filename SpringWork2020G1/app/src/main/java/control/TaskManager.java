package control;

import java.sql.Connection;
import java.util.List;
import beans.Project;
import beans.Task;
import dao.TaskDAO;

import javax.servlet.http.HttpServlet;

public class TaskManager extends HttpServlet{
    private Connection connection = null;

    public TaskManager(){

    }
    public List<Task> taskList() {
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        List<Task> taskList = taskDAO.taskList(this.connection);
        taskDAO.closeConnection(this.connection);
        this.connection = null;
        return taskList;
    }

    public void registTask(Task task){
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        taskDAO.registTask(task,this.connection);
        taskDAO.closeConnection(this.connection);
        this.connection = null;
    }
    public int getTask(Task task){
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        int tsk_id = taskDAO.getTask(task,this.connection);
        taskDAO.closeConnection(this.connection);
        this.connection = null;
        return tsk_id;
    }

    public List findAllTask(){
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        List<Task> taskList = taskDAO.findAll(this.connection);
        taskDAO.closeConnection(this.connection);
        this.connection = null;
        return taskList;
    }

    public void deleteTask(Task task){
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        //taskDAO.deleteTask(task,this.connection);
        taskDAO.closeConnection(this.connection);
        this.connection = null;
    }
}