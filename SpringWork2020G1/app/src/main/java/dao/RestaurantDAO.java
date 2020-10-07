
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

import beans.Restaurant;

public class RestaurantDAO {

    // 属性

    // データベースの接続先アドレスを静的変数として記述
    private final static String DRIVER_URL = "jdbc:mysql://localhost:3306/mst_todo?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";;

    // データベース接続ドライバの名前を静的変数として記述
    // Mysql5.系
    // private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    // Mysql8.系
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    // データベースのユーザー名 （デフォルトではroot）
    private final static String Restaurant_NAME = "root";

    // データベースのユーザーのパスワード (デフォルトでは設定なし)
    private final static String PASSWORD = "";

    // データベースとの接続を行う
    // データベースの接続情報を持ったConnectionオブジェクトを返す
    public Connection createConnection() {
        try {
            Class.forName(DRIVER_NAME);
            Connection con = DriverManager.getConnection(DRIVER_URL, Restaurant_NAME, PASSWORD);
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
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public void registRestaurant(Restaurant restaurant, Connection connection) {

        try {

            // SQLコマンド
            String sql = "insert into restaurant (name) values(?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, restaurant.getName());

            stmt.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    public List<Restaurant> findAll(Connection connection){ //restaurantのリストの全レコードの取得
        try{

            String sql = "SELECT * FROM restaurant"; //SELECT文(クラス内での定数)

            List<Restaurant> list = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql); //SELECT文を実行し結果表を取得
            while(rs.next()){ //characterにレコードの値をいれてListのインスタンスであるlistに追加
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt("id"));
                restaurant.setName(rs.getString("name"));
                list.add(restaurant);
            }
            stmt.close(); //close
            rs.close(); //close
            return list;
        } catch (SQLException e) { //接続やSQL処理の失敗時の処理
            e.printStackTrace();
            return null;
        } finally {
        }
    }

}