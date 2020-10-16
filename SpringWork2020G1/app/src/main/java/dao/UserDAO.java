package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; //ArrayListのインポート
import java.util.List; //Listのインポート
import javax.servlet.http.HttpSession;

import beans.User;
import utility.DriverAccessor;

public class UserDAO extends DriverAccessor {

    public static final String LIST_USER = "select * from users";
    public static final String REGIST_USER = "insert into users (id, name, password) values(?, ?, ?)";

    public List<User> userList(Connection connection) {
        try {
            List<User> userList = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(LIST_USER);
            ResultSet rs = stmt.executeQuery();
            boolean flag = rs.first();
            while (flag) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                userList.add(user);
                flag = rs.next();
            }
            stmt.close();
            rs.close();
            return userList;
        } catch (SQLException e) {
            return null;
        }
    }
    // 情報をデータベースに登録する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public void registUser(User user, Connection connection) {
        try {
            PreparedStatement stmt = connection.prepareStatement(REGIST_USER);
            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }

    public User login(String id, String pass, Connection connection) {
        try {
            String LOGIN_USER = "select * from users where id = '" + id + "' and password = '" + pass + "'";
            System.out.println(LOGIN_USER);
            PreparedStatement stmt = connection.prepareStatement(LOGIN_USER);
            ResultSet rs = stmt.executeQuery();
            rs.first();

            // if(rs.next()){
            User login_user = new User(rs.getString("id"), rs.getString("name"));
            stmt.close();
            rs.close();
            return login_user;
            // }
            // else{
            // return null;
            // }
        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return null;

        } finally {
        }
    }
}