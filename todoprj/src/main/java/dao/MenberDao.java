
//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    // 属性

    // データベースの接続先アドレスを静的変数として記述
    private final static String DRIVER_URL = "jdbc:mysql://localhost:3306/mst_todo?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";

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



    // 認証する
    // 引数はStringオブジェクトと、Connectionオブジェクト
    public Boolean authUser(String name, String pass, Connection connection) {
        try {

            // SQLコマンド
            String sql = "select * from users where name ='" + name + "' && pass = '" + pass + "'";
            System.out.println(sql);
            // SQLのコマンドを実行する  実行結果はrsに格納される
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Boolean Tmp_final = rs.next();

            rs.first();
            rs.close();

            // 終了処理
            stmt.close();

            if (Tmp_final) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;

        } finally {
        }
    }

    public void registUser(String name, String pass, Connection connection) {
        
        try {

            // SQLコマンド
            String sql = "insert into users values('" + name + "','" + pass + "')";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println(sql);
            
        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();

        } finally {
        }
    }

}
