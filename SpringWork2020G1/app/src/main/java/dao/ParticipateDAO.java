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
import beans.Participate;

public class ParticipateDAO extends DriverAccessor{

    public static final String DISPLAY_PARTICIPATE = "select * from participates";
    public static final String FIND_PART = "select * from participates where prj_id = ?";
    public static final String CREATE_PARTICIPATE = "insert into participates(user_id,prj_id,is_prj_own) values(?,?,?)";
    public static final String DELETE_PARTICIPATE = "delete from participates where user_id = ? and prj_id = ?";

    public List<Participate> participateList(Connection connection){
        try {
            List<Participate> participateList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(DISPLAY_PARTICIPATE);
            ResultSet rs = statement.executeQuery();
            boolean Flag = rs.first();
            while (Flag){
                Participate participate = new Participate();
                participate.setUserId(rs.getString("user_id"));
                participate.setPrjId(rs.getInt("prj_id"));
                participate.setIsPrjOwn(rs.getBoolean("is_prj_own"));
                participateList.add(participate);
                Flag = rs.next();
            }
            statement.close();
            rs.close();
            return participateList;
        } catch (SQLException e){
            return null;
        }
    }
    public List<Participate> findPart(int prj_id,Connection connection){
        try {
            List<Participate> participateList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(FIND_PART);
            statement.setInt(1, prj_id);
            ResultSet rs = statement.executeQuery();
            boolean Flag = rs.first();
            while (Flag){
                Participate participate = new Participate();
                participate.setUserId(rs.getString("user_id"));
                participate.setPrjId(prj_id);
                participate.setIsPrjOwn(rs.getBoolean("is_prj_own"));
                participateList.add(participate);
                Flag = rs.next();
            }
            statement.close();
            rs.close();
            return participateList;

        } catch (SQLException e){
            return null;
        }
    }
    public void createParticipate(Participate participate,Connection connection) {
        try {
            System.out.println("CREATE_PARTY");
            System.out.println(participate.getPrjId());
            String sql = "select * from projects where id = '" + participate.getPrjId() + "' ";
            PreparedStatement stmt1 = connection.prepareStatement(sql);
            ResultSet rs = stmt1.executeQuery();
            rs.first();
            String hostID = rs.getString("host_id");
            stmt1.close();
            rs.close();

            PreparedStatement stmt = connection.prepareStatement(CREATE_PARTICIPATE);
            stmt.setString(1, participate.getUserId());
            stmt.setInt(2, participate.getPrjId());
            if(participate.getUserId().equals(hostID)){
                stmt.setBoolean(3, true);
            }else{
                stmt.setBoolean(3, false);
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }
    public void deleteParticipate(Participate participate,Connection connection){
        try {

            PreparedStatement stmt = connection.prepareStatement(DELETE_PARTICIPATE);
            stmt.setString(1,participate.getUserId());
            stmt.setInt(2,participate.getPrjId());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        }
    }
    public void joinProject(String userid,int prjid,Connection connection){
        try {
            String joinsql = "insert into participates(user_id,prj_id,is_prj_own) values(?,?,false)";
            PreparedStatement stmt = connection.prepareStatement(joinsql);
            stmt.setString(1,userid);
            stmt.setInt(2,prjid);
            System.out.println(stmt);
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        }
    }
    public void punishMenber(int prjid,Connection connection){
        try {
            String punishsql = "delete from participates where prj_id = ?";
            PreparedStatement stmt = connection.prepareStatement(punishsql);
            stmt.setInt(1,prjid);
            System.out.println(stmt);
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        }
    }
}