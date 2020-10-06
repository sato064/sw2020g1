//　自分が格納されているフォルダ名
package control;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.List; //Listのインポート

import beans.Restaurant;
import dao.RestaurantDAO;

public class RestaurantManager {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public RestaurantManager() {
    }

    // 追加
    // 引数はStudentオブジェクト
    public void registRestaurant(Restaurant restaurant) {

        // StudentDAOオブジェクト生成
        RestaurantDAO restaurantDAO = new RestaurantDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = restaurantDAO.createConnection();

        // StudentオブジェクトをDataBaseに登録する
        restaurantDAO.registRestaurant(restaurant, this.connection);

        // DataBaseとの接続を切断する
        restaurantDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

    }

    public List<Restaurant> findAll(){ //Restaurant型のList
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        this.connection = restaurantDAO.createConnection();
        List<Restaurant> list = restaurantDAO.findAll(this.connection);
        restaurantDAO.closeConnection(this.connection);
        return list;
    }

}
