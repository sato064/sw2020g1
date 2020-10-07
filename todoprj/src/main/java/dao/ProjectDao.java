package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.Student;
import utility.DriverAccessor;
import model.Project;

public class ShopDao extends DriverAccessor{
    public static final String DISPLAY_PROJECT = "select * from projects inner join student_info on projects.authorId = student_info.student_id";
    public static final String REGIST_PROJECT = "insert into projects(projectName,projectInfo,authorId,detailedInfo) values(?,?,?,?)";
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
                project.setProjectNAME(rs.getString("projectName"));
                project.setProjectINFO(rs.getString("projectInfo"));
                project.setAuthorID(rs.getString("authorId"));
                project.setDetailedINFO(rs.getString("detailedInfo"));
                project.setAuthorNAME(rs.getString("student_name"));
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
            PreparedStatement statement = connection.prepareStatement(REGIST_PROJECT);
            statement.setString(1, project.getProjectNAME("projectNAME"));
            statement.setString(2, project.getProjectINFO("projectINFO"));
            statement.setString(3, project.getAuthorID("authorID"));
            statement.setString(4, project.getDetailedINFO("detailedINFO"));
            statement.executeUpdate();

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }

    public void deleteProject(Project project,Connection connection){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_SHOP);
            statement.setInt(1,project.getProjectID(1));
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        }
    }
}
