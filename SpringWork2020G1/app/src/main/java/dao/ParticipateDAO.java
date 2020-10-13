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
import beans.Participate;

public class ParticipateDAO extends DriverAccessor{

    public static final String CREATE_PROJECT = "insert into participates(user_id,prj_id,is_prj_own) values(?,?,?)";
    public static final String DELETE_PROJECT = "delete from participates where user_id = ? and prj_id = ?";

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

            PreparedStatement stmt = connection.prepareStatement(CREATE_PROJECT);
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

            PreparedStatement stmt = connection.prepareStatement(DELETE_PROJECT);
            stmt.setString(1,participate.getUserId());
            stmt.setInt(2,participate.getPrjId());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        }
    }
}