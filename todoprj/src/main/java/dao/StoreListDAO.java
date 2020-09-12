
//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Store;

public class StoreListDAO {

    List<Store> Stores = new ArrayList<Store>();
    List<String> Storenames = new ArrayList<String>();

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

    // 情報を検索する
    // 引数はStringオブジェクトと、Connectionオブジェクト
    public List<Store> findStores(String str_name, Connection connection) {

        try {

            // SQLコマンド
            String sql = "select * from stores where name ='" + str_name + "'";
            System.out.println(sql);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println(rs.next());
            System.out.println("LOOP START");

            do {
                System.out.println("REGISTING");
                //オートインクリメントの情報も拡張性を込めて入手する
                String id = rs.getString("id");
                String name = rs.getString("name");
                System.out.println(name);
                String info = rs.getString("info");
                System.out.println(info);

                Store findedstore = new Store(name, info);

                Stores.add(findedstore);
            } while (rs.next());

            rs.first();
            rs.close();
            stmt.close();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
        return Stores;
    }
    public List<String> findFavStores(String usr_name, Connection connection) {

        try {


            // SQLコマンド
            String sql = "select * from usrfavs where us_name ='"+ usr_name +"'";
            System.out.println(sql);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            
            System.out.println(rs.next());
            System.out.println("LOOP START");

            do {
                System.out.println("REGISTING");
                //オートインクリメントの情報も拡張性を込めて入手する
                String name = rs.getString("us_name");
                String info = rs.getString("st_name");
                System.out.println(info);


				Storenames.add(info);
            }while (rs.next());

            rs.first();
            rs.close();
            stmt.close();

        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();

        } finally {
        }
		return Storenames;
    }





    }

