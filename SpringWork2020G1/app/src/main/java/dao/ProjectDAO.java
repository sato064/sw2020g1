package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import beans.User;
import utility.DriverAccessor;
import beans.Project;

public class ProjectDAO extends DriverAccessor{


    public static final String DISPLAY_PROJECT = "select * from projects inner join student_info on projects.authorId = student_info.student_id";
    public static final String REGIST_PROJECT = "insert into projects(projectTitle,projectInfo,authorId,detailedInfo) values(?,?,?,?)";
    public static final String DELETE_PROJECT = "delete from projects where projectId = ?";

    public List<Project> projectList(Connection connection){
        try {
            List<Project> projectList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(DISPLAY_PROJECT);
            ResultSet rs = statement.executeQuery();
            boolean Flag = rs.first();
            while (Flag){
                Project project = new Project();
                project.setProjectID(rs.getInt("projectId"));
                project.setProjectTITLE(rs.getString("projectTitle"));
                project.setOverview(rs.getString("overview"));
                project.setHostID(rs.getString("hostId"));
                project.setDeadline(rs.getString("deadline"));
                project.setProjectSTATUS(rs.getInt("status"));
                project.setIsDelayed(rs.getBoolean("isdelayed"));
                projectList.add(project);
                Flag = rs.next();
            }
            statement.close();
            rs.close();
            return projectList;
        } catch (SQLException e){
            return null;
        }
    }

    public void registProject(Project project,Connection connection) {
        try {
            String sql = "insert into projects (title, overview, host_id, deadline, status ,is_delayed) values(?, ?, ?, ?, ? ,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            System.out.println("TOUTATU/DAO");
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