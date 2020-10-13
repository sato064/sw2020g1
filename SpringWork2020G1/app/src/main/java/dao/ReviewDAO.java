
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
import utility.DriverAccessor;
import beans.Review;

public class ReviewDAO extends DriverAccessor {

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

            stmt.executeUpdate();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
    }

    public List<Review> findAll(String s, Connection connection) { // Reviewのリストの全レコードの取得
        try {

            String sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id"; // SELECT文(クラス内での定数)

            System.out.println(s);

            if (s.equals("good_count")) {
                sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id ORDER BY good_count DESC"; // SELECT文(クラス内での定数)
            }

            if (s.equals("Morning")) {
                sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id WHERE scene = 'Morning'";
            }

            if (s.equals("Lunch")) {
                sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id WHERE scene = 'Lunch'";
            }

            if (s.equals("Dinner")) {
                sql = "SELECT * FROM (review INNER JOIN user ON review.user_id = user.id) INNER JOIN restaurant ON review.restaurant_id = restaurant.id WHERE scene = 'Dinner'";
            }

            System.out.println(sql);

            List<Review> list = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql); // SELECT文を実行し結果表を取得
            while (rs.next()) { // characterにレコードの値をいれてListのインスタンスであるlistに追加
                Review Review = new Review();
                Review.setId(rs.getInt("id"));
                User user = new User(rs.getString("user.id"), rs.getString("user.name"));
                Review.setUser(user);
                Review.setScene(rs.getString("scene"));
                Review.setInformation(rs.getString("information"));
                Review.setGoodCount(rs.getInt("good_count"));
                list.add(Review);
            }
            stmt.close(); // close
            rs.close(); // close
            return list;
        } catch (SQLException e) { // 接続やSQL処理の失敗時の処理
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
            // return Review;

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            // return null;

        } finally {
        }
    }

}