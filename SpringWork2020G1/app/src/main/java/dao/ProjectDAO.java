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
import beans.Project;

public class ProjectDAO extends DriverAccessor{


    public static final String DISPLAY_PROJECT = "select * from projects";
    public static final String REGIST_PROJECT = "insert into projects (title, overview, host_id, deadline, status ,is_delayed) values(?, ?, ?, ?, ? ,?)";
    public static final String DELETE_PROJECT = "delete from projects where projectId = ?";

    public List<Project> projectList(Connection connection){
        try {
            List<Project> projectList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(DISPLAY_PROJECT);
            System.out.println(DISPLAY_PROJECT);
            ResultSet rs = statement.executeQuery();
            boolean Flag = rs.first();
            do {
                System.out.println("REGISTING");
                
                Project project = new Project();
                project.setProjectID(rs.getInt("id"));
                project.setProjectTITLE(rs.getString("title"));
                project.setOverview(rs.getString("overview"));
                project.setHostID(rs.getString("host_id"));
                project.setDeadline(rs.getString("deadline"));
                project.setProjectSTATUS(rs.getInt("status"));
                project.setIsDelayed(rs.getBoolean("is_delayed"));
                projectList.add(project);
                System.out.println(project);
                Flag = rs.next();
            } while (Flag);
            statement.close();
            rs.close();
            System.out.println(projectList);
            return projectList;
        } catch (SQLException e){
            return null;
        }
    }

    public void registProject(Project project,Connection connection) {
        try {
            PreparedStatement stmt = connection.prepareStatement(REGIST_PROJECT);
            stmt.setString(1, project.getProjectTITLE());
            stmt.setString(2, project.getOverview());
            stmt.setString(3, project.getHostID());
            stmt.setString(4, project.getDeadline());
            stmt.setInt(5, 0);
            stmt.setBoolean(6, false);
            stmt.executeUpdate();

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }
    public int getProject(Project project,Connection connection) {
        try {
            //読み込み用
            String sql = "select * from projects where id=(select MAX(id) from projects)";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.first();
            int prj_id = rs.getInt("id");
            statement.close();
            rs.close();
            return prj_id;

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return 0;
        } finally {
        }
    }
    public void deleteProject(Project project,Connection connection){
        try {

            PreparedStatement statement = connection.prepareStatement(DELETE_PROJECT);
            statement.setInt(1,project.getProjectID());
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        }
    }
}