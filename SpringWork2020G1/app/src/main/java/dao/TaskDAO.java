package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import beans.User;
import utility.DriverAccessor;
import beans.Task;

public class TaskDAO extends DriverAccessor{


    public static final String DISPLAY_TASK = "select * from tasks";
    public static final String FIND_TASK = "select * from tasks where prj_id = ?";
    public static final String REGIST_TASK = "insert into tasks ( prj_id, title, overview, deadline, status ,is_delayed) values(? ,?, ?, ?, ? ,?)";
    public static final String UPDATE_TASK = "update tasks set prj_id = ?, title = ?, overview = ?, deadline = ?, status = ?, is_delayed = ? where id = ?";
    public static final String DELETE_TASK = "delete from tasks where id = ?";

    public List<Task> taskList(Connection connection){
        try {
            List<Task> taskList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(DISPLAY_TASK);
            ResultSet rs = statement.executeQuery();
            boolean Flag = rs.first();
            while (Flag){
                Task task = new Task();
                task.setTaskID(rs.getInt("taskId"));
                task.setProjectID(rs.getInt("projectId"));
                task.setTaskTITLE(rs.getString("taskTitle"));
                task.setOverview(rs.getString("overview"));
                task.setDeadline(rs.getString("deadline"));
                task.setTaskSTATUS(rs.getInt("status"));
                task.setIsDelayed(rs.getBoolean("isdelayed"));
                taskList.add(task);
                
                Flag = rs.next();
            }
            statement.close();
            rs.close();
            
            return taskList;
        } catch (SQLException e){
            return null;
        }
    }
    public List<Task> taskListByPrjId(int id,Connection connection){
        try {
            List<Task> taskList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(FIND_TASK);
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            System.out.println(rs);
            boolean Flag = rs.first();
            System.out.println(Flag);
            do{

                System.out.println("REGISTING");
                Task task = new Task();
                task.setTaskID(rs.getInt("id"));
                task.setProjectID(id);
                task.setTaskTITLE(rs.getString("title"));
                task.setOverview(rs.getString("overview"));
                task.setDeadline(rs.getString("deadline"));
                task.setTaskSTATUS(rs.getInt("status"));
                task.setIsDelayed(rs.getBoolean("is_delayed"));
                System.out.println(task);
                taskList.add(task);
                Flag = rs.next();
            }while(Flag);
            statement.close();
            rs.close();
            System.out.println(taskList);
            return taskList;
        } catch (SQLException e){
            return null;
        }
    }

    public void registTask(Task task,Connection connection) {
        try {
            PreparedStatement stmt = connection.prepareStatement(REGIST_TASK);
            stmt.setInt(1, task.getProjectID());
            stmt.setString(2, task.getTaskTITLE());
            stmt.setString(3, task.getOverview());
            stmt.setString(4, task.getDeadline());
            stmt.setInt(5, task.getTaskSTATUS());
            stmt.setBoolean(6, false);
            stmt.executeUpdate();

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }
    public void updateTask(Task task,Connection connection) {
        try {
            PreparedStatement stmt = connection.prepareStatement(UPDATE_TASK);
            stmt.setInt(1, task.getProjectID());
            stmt.setString(2, task.getTaskTITLE());
            stmt.setString(3, task.getOverview());
            stmt.setString(4, task.getDeadline());
            stmt.setInt(5, task.getTaskSTATUS());
            stmt.setBoolean(6, false);
            stmt.setInt(7, task.getTaskID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }
    public int getTask(Task task,Connection connection) {
        try {
            //読み込み用
            String sql = "select * from tasks where id=(select MAX(id) from tasks)";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.first();
            int tsk_id = rs.getInt("id");
            statement.close();
            rs.close();
            return tsk_id;

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return 0;
        } finally {
        }
    }
    public List<Task> findAll(Connection connection){ //Reviewのリストの全レコードの取得
        try{

            String sql = "SELECT * FROM tasks"; //SELECT文(クラス内での定数)

            List<Task> list = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql); //SELECT文を実行し結果表を取得
            while(rs.next()){ //characterにレコードの値をいれてListのインスタンスであるlistに追加
                Task task = new Task();
                task.setTaskID(rs.getInt("id"));
                list.add(task);
            }
            stmt.close(); //close
            rs.close(); //close
            return list;
        } catch (SQLException e) { //接続やSQL処理の失敗時の処理
            e.printStackTrace();
            return null;
        }
    }
    public Integer findTaskIdByName(String name,Connection connection){ //Reviewのリストの全レコードの取得
        try{
            System.out.println(name);

            String sql2 = "SELECT * FROM tasks WHERE title = '"+ name + "'"; //SELECT文(クラス内での定数)
            PreparedStatement stmt = connection.prepareStatement(sql2);
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery(sql2); //SELECT文を実行し結果表を取得
            Task task = new Task();
            rs.next();
            task.setTaskID(rs.getInt("id"));
            stmt.close();
            rs.close();
            return task.getTaskID();
        } catch (SQLException e) { //接続やSQL処理の失敗時の処理
            e.printStackTrace();
            return null;
        }
    }
    public Task findTaskIdById(Integer id,Connection connection){ //Reviewのリストの全レコードの取得
        try{

            String sql2 = "SELECT * FROM tasks WHERE id = '"+ id + "'"; //SELECT文(クラス内での定数)
            PreparedStatement stmt = connection.prepareStatement(sql2);
            
            ResultSet rs = stmt.executeQuery(sql2);
            rs.next(); //SELECT文を実行し結果表を取得
            Task task = new Task();
                task.setTaskID(id);
                task.setProjectID(rs.getInt("prj_id"));
                task.setTaskTITLE(rs.getString("title"));
                task.setOverview(rs.getString("overview"));
                task.setDeadline(rs.getString("deadline"));
                task.setTaskSTATUS(rs.getInt("status"));
                task.setIsDelayed(rs.getBoolean("is_delayed"));
            
            task.setTaskID(rs.getInt("id"));
            stmt.close();
            rs.close();
            return task;
        } catch (SQLException e) { //接続やSQL処理の失敗時の処理
            e.printStackTrace();
            return null;
        }
    }



    public void deleteTask(Integer taskid,Connection connection){

        try {

            PreparedStatement statement = connection.prepareStatement(DELETE_TASK);
            statement.setInt(1,taskid);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        }
    }


}