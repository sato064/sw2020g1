
//　自分が格納されているフォルダ名
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

    // 情報をデータベースに登録する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public void registUser(User user, Connection connection) {
        try {
            // SQLコマンド
            String sql = "insert into users (id, name, password) values(?, ?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);

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
            System.out.println("TOUTATu");
            // SQLコマンド
            String sql = "select * from users where id = '" + id + "' and password = '" + pass + "'";

            // SQLコマンドの実行
            System.out.println(sql);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
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

    public List<String> userList(Connection connection) {
        try {
            List<String> userList = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement("select * from users");
            ResultSet rs = stmt.executeQuery();
            boolean flag = rs.first();
            while (flag) {
                // User user = new User();
                // user.setId(rs.getString("id"));
                // user.setName(rs.getString("name"));
                // userList.add(user);
                userList.add(rs.getString("name"));
                flag = rs.next();
            }
            stmt.close();
            rs.close();
            return userList;
        } catch (SQLException e) {
            return null;
        }
    }
}