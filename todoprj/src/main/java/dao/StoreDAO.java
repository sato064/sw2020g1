
//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Store;

public class StoreDAO {

    // 属性

    // データベースの接続先アドレスを静的変数として記述
    private final static String DRIVER_URL = "jdbc:mysql://localhost:3306/SE?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";

    // データベース接続ドライバの名前を静的変数として記述
    // Mysql5.系
    // private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    // Mysql8.系
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    // データベースのユーザー名 （デフォルトではroot）
    private final static String USER_NAME = "root";

    // データベースのユーザーのパスワード (デフォルトでは設定なし)
    private final static String PASSWORD = "";

    // データベースとの接続を行う
    // データベースの接続情報を持ったConnectionオブジェクトを返す
    public Connection createConnection() {
        try {
            Class.forName(DRIVER_NAME);
            Connection con = DriverManager.getConnection(DRIVER_URL, USER_NAME, PASSWORD);
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Can't Find JDBC Driver.\n");
        } catch (SQLException e) {
            System.out.println("Connect Error.\n");
        }
        return null;
    }

    // Connectionオブジェクトを使って、データベースとの接続を切断する
    // 引数Connectionオブジェクト
    public void closeConnection(Connection con) {

        try {
            con.close();
        } catch (Exception ex) {
        }
    }

    // 情報をデータベースに登録する
    // 引数はStoreオブジェクトと、Connectionオブジェクト
    public void registStore(Store store, Connection connection) {

        try {

            // SQLコマンド
            String sql = "insert into stores values(0, ?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, store.getStoreName());
            stmt.setString(2, store.getStoreInfo());
            System.out.println(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }
    //削除する
    //引数はStringとConnection
    public void deleteStore(String str_name, Connection connection) {

        try {

            // SQLコマンド
            String sql = "delete from stores where name = '" + str_name + "'";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println(sql);

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    public Boolean checkStore(String str_name, Connection connection) {

        try {

            // SQLコマンド
            String sql = "select * from stores where name = '" + str_name + "'";
            System.out.println(sql);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Boolean tmp = rs.next();
            System.out.println(tmp);
            rs.first();
            rs.close();
            stmt.close();

            if (tmp) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
            return null;

        } finally {
        }
    }
    public void favStore(String str_name, String usr_name,  Connection connection) {

        try {

            // SQLコマンド
            String sql = "insert into usrfavs values(?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, usr_name);
            stmt.setString(2, str_name);
            System.out.println(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    

}
