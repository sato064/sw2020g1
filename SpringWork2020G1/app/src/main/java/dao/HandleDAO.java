package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import beans.User;
import jdk.nashorn.internal.scripts.JO;
import beans.Handle;
import utility.DriverAccessor;
import beans.Task;

public class HandleDAO extends DriverAccessor{

    public static final String DISPLAY_HANDLE = "select * from handles";
    public static final String DELETE_HANDLE = "delete from handles where task_id = ?";
    public static final String FIND_HANDLE = "select * from handles where task_id = ?";
    public static final String JOIN_HANDLE = "insert into handles (task_id, user_id,prj_id) values(?, ? ,1)";

    public List<Handle> handleList(Connection connection){
        try {
            List<Handle> handleList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(DISPLAY_HANDLE);
            ResultSet rs = statement.executeQuery();
            boolean Flag = rs.first();

            do {
                Handle handle = new Handle();
                handle.setTaskId(rs.getInt("task_id"));
                handle.setUserId(rs.getString("user_id"));
                handleList.add(handle);
                Flag = rs.next();
            } while (Flag);
            statement.close();
            rs.close();
            System.out.println(handleList);
            return handleList;
        } catch (SQLException e){
            return null;
        }
    }
    public void deleteHandle(Integer taskid,Connection connection){
        try {

            PreparedStatement stmt = connection.prepareStatement(DELETE_HANDLE);
            stmt.setInt(1,taskid);
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        }
    }
    public List<Handle> findJojner(Integer taskid,Connection connection){
        try {

            PreparedStatement stmt = connection.prepareStatement(FIND_HANDLE);
            List<Handle> handleList = new ArrayList<>();
            stmt.setInt(1, taskid);
            ResultSet rs = stmt.executeQuery();
            boolean flag = rs.first();
            while (flag) {
                Handle handle = new Handle();
                handle.setTaskId(taskid);
                handle.setUserId(rs.getString("user_id"));
                handleList.add(handle);
                flag = rs.next();
            }
            stmt.close();
            rs.close();
            return handleList;
        } catch (SQLException e) {
            return null;
        }
    }public void registHandle(Integer taskid,String userid,Connection connection){
        try{
            PreparedStatement stmt = connection.prepareStatement(JOIN_HANDLE);
            stmt.setInt(1, taskid);
            stmt.setString(2, userid);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
        }
    }
}