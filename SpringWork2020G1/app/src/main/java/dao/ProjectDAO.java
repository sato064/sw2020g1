package dao;

import java.io.StreamTokenizer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import beans.User;
import utility.DriverAccessor;
import beans.Project;

public class ProjectDAO extends DriverAccessor{


    public static final String FIND_DELAY = "select * from projects where is_delayed = false ORDER BY deadline ASC";
    public static final String DISPLAY_PROJECT = "select * from projects where status = 0 or status = 1 ORDER BY deadline ASC";
    public static final String DISPLAY_PROJECT_FIN = "select * from projects where status = 2 ORDER BY deadline ASC";
    public static final String REGIST_PROJECT = "insert into projects (title, overview, host_id, deadline, status ,is_delayed) values(?, ?, ?, ?, ? ,?)";
    public static final String FIND_PROJECT = "select * from projects where id = ?";
    public static final String UPDATE_STATUS = "update projects set `is_delayed` = true WHERE id=?";
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
    public List<Project> finprojectList(Connection connection){
        try {
            List<Project> projectList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(DISPLAY_PROJECT_FIN);
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
    public int delayChecker(Connection connection){
        try {
            System.out.println("Checker done");
            PreparedStatement statement = connection.prepareStatement(FIND_DELAY);
            ResultSet rs = statement.executeQuery();
            boolean Flag = rs.first();
            LocalDateTime ltd_now = LocalDateTime.now();
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            int id;
            do {
                boolean delay_flag = false;
                LocalDateTime ltd = LocalDateTime.parse(rs.getString("deadline"),f);
                if(ltd.isBefore(ltd_now)){
                    delay_flag = true;
                    
                }
                System.out.println(delay_flag);
                if(delay_flag){
                    System.out.println("Updating");
                    id = rs.getInt("id");
                    break;

                }
                //System.out.println(rs.next());

                Flag = rs.next();
            } while (Flag);
            id = rs.getInt("id");
            statement.close();
            rs.close();
            return id;
            
        } catch (SQLException e){
            return 0;
        }
    }

    public Project findProject(int prjId ,Connection connection) {
        try{
        PreparedStatement statement = connection.prepareStatement(FIND_PROJECT);
        statement.setInt(1, prjId);
        //System.out.println(statement);
        ResultSet rs = statement.executeQuery();
        rs.first();
        Project project = new Project();
            project.setProjectID(rs.getInt("id"));
            project.setProjectTITLE(rs.getString("title"));
            project.setOverview(rs.getString("overview"));
            project.setHostID(rs.getString("host_id"));
            project.setDeadline(rs.getString("deadline"));
            project.setProjectSTATUS(rs.getInt("status"));
            project.setIsDelayed(rs.getBoolean("is_delayed"));
        statement.close();
        rs.close();
        return project;
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
    public void delayProject(int id,Connection connection) {
        try {
            //読み込み用
            String sql = "update projects set `is_delayed` = true WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }

    public void updateProject(Connection connection) {
        try {
            //読み込み用
            String sql = "select * from projects where id=(select MAX(id) from projects)";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.first();
            int prj_id = rs.getInt("id");
            statement.close();
            rs.close();

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }
    public void refProject(Project project,Connection connection) {
        try {
            //読み込み用
            String sql = "update projects set title = ?,overview = ?,host_id = ?,deadline = ?,status = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, project.getProjectTITLE());
            statement.setString(2, project.getOverview());
            statement.setString(3, project.getHostID());
            statement.setString(4, project.getDeadline());
            statement.setInt(5, project.getProjectSTATUS());
            statement.setInt(6, project.getProjectID());
            statement.executeUpdate();
            statement.close();

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