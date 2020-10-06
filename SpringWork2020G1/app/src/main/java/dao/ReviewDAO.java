
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

import beans.User;
import beans.Review;
import beans.Restaurant;

public class ReviewDAO {

    // 属性

    // データベースの接続先アドレスを静的変数として記述
    private final static String DRIVER_URL = "jdbc:mysql://localhost:3306/gourmet?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";

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
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public void registReview(Review review, Connection connection) {

        try {

            // SQLコマンド
            String sql = "insert into review (information, good_count, scene, user_id, restaurant_id) values(?, ?, ?, ?, ?)";

            // SQLコマンドの実行
            PreparedStatement stmt = connection.prepareStatement(sql);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            stmt.setString(1, review.getInformation());
            stmt.setInt(2, review.getGoodCount());
            stmt.setString(3, review.getScene());
            stmt.setInt(4, review.getUserId());
            stmt.setInt(5, review.getRestaurantId());

            stmt.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    public List<Review> findAll(String s, Connection connection){ //Reviewのリストの全レコードの取得
        try{

            String sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id"; //SELECT文(クラス内での定数)
            
            System.out.println(s);

            if(s.equals("good_count")){
                sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id ORDER BY good_count DESC"; //SELECT文(クラス内での定数)
            }

            if(s.equals("Morning")){
                sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id WHERE scene = 'Morning'";
            }

            if(s.equals("Lunch")){
                sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id WHERE scene = 'Lunch'";
            }

            if(s.equals("Dinner")){
                sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id WHERE scene = 'Dinner'";
            }

            System.out.println(sql);

            List<Review> list = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql); //SELECT文を実行し結果表を取得
            while(rs.next()){ //characterにレコードの値をいれてListのインスタンスであるlistに追加
                Review Review = new Review();
                Review.setId(rs.getInt("id"));
                User user = new User(
                    rs.getInt("user.id"),
                    rs.getString("user.name")
                );
                Review.setUser(user);
                Restaurant restaurant = new Restaurant(
                    rs.getInt("restaurant.id"),
                    rs.getString("restaurant.name")
                );
                Review.setRestaurant(restaurant);
                Review.setScene(rs.getString("scene"));
                Review.setInformation(rs.getString("information"));
                Review.setGoodCount(rs.getInt("good_count"));
                list.add(Review);
            }
            stmt.close(); //close
            rs.close(); //close
            return list;
        } catch (SQLException e) { //接続やSQL処理の失敗時の処理
            e.printStackTrace();
            return null;
        }
    }

    public void countGood(Review review, Connection connection) {

        try {

            // SQLコマンド
            String sql = "UPDATE review SET good_count=good_count+1 WHERE id = ?";
            
            // SQLのコマンドを実行する
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, review.getId());

            // 終了処理
            stmt.executeUpdate();

            // オブジェクトを返す
            //return Review;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            //return null;

        } finally {
        }
    }

}