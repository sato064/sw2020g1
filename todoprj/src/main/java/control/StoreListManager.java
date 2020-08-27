//　自分が格納されているフォルダ名
package control;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import beans.Store;
import dao.StoreListDAO;


public class StoreListManager {

    // 属性
    private Connection connection = null;

    List<Store> stores = new ArrayList<Store>();
    List<String> storenames = new ArrayList<String>();


    // 引数を持たないコンストラクタ
    public StoreListManager() {
    }


    // 検索
    public List<Store> findStores(String str_name) {

        // StudentDAOオブジェクト生成
        StoreListDAO storeListDAO = new StoreListDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = storeListDAO.createConnection();

        //検索する
        System.out.println(str_name);
        stores = storeListDAO.findStores(str_name, this.connection);

        // DataBaseとの接続を切断する
        storeListDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return stores;
    }
    public List<String> findFavStores(String usr_name) {



        // StudentDAOオブジェクト生成
        StoreListDAO storeListDAO = new StoreListDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = storeListDAO.createConnection();

        //検索する
        storenames = storeListDAO.findFavStores(usr_name, this.connection);

        // DataBaseとの接続を切断する
        storeListDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return storenames;
    }



}
