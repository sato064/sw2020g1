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
    public static final String REGIST_TASK = "insert into tasks ( title, overview, deadline, status ,is_delayed) values(?, ?, ?, ? ,?)";
    public static final String DELETE_PROJECT = "delete from tasks where taskId = ?";

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
                task.setProjectSTATUS(rs.getInt("status"));
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

    public void registTask(Task task,Connection connection) {
        try {
            PreparedStatement stmt = connection.prepareStatement(REGIST_TASK);
            stmt.setString(1, task.getTaskTITLE());
            stmt.setString(2, task.getOverview());
            stmt.setString(3, task.getDeadline());
            stmt.setInt(4, 0);
            stmt.setBoolean(5, false);
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

    public void deleteProject(Task task,Connection connection){
        try {

            PreparedStatement statement = connection.prepareStatement(DELETE_TASK);
            statement.setInt(1,task.getTaskID());
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        }
    }
}