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
    public List<Task> findList(int id) {
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        List<Task> taskList = taskDAO.taskListByPrjId(id, this.connection);
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

    public void deleteTask(Integer taskid){
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        taskDAO.deleteTask(taskid,this.connection);
        taskDAO.closeConnection(this.connection);
        this.connection = null;
    }
    public Integer findTaskByName(String name){
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        int id = taskDAO.findTaskIdByName(name, this.connection);
        taskDAO.closeConnection(this.connection);
        return id;

    }
    public Task findTaskById(Integer id){
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        Task task = taskDAO.findTaskIdById(id, this.connection);
        taskDAO.closeConnection(this.connection);
        return task;

    }
    public void updateTask(Task task){
        TaskDAO taskDAO = new TaskDAO();
        this.connection = taskDAO.createConnection();
        taskDAO.updateTask(task, this.connection);
        taskDAO.closeConnection(this.connection);
        this.connection = null;

    }
}