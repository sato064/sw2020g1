//　自分が格納されているフォルダ名
package control;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;

import beans.Store;
import dao.StoreDAO;

public class StoreManager {

    // 属性
    private Connection connection = null;

    private boolean isExist = false;


    // 引数を持たないコンストラクタ
    public StoreManager() {
    }


    // 検索
    public void registStore(Store store) {

        // StoreDAOオブジェクト生成
        StoreDAO storeDAO = new StoreDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = storeDAO.createConnection();

        //登録する
        storeDAO.registStore(store, this.connection);

        // DataBaseとの接続を切断する
        storeDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;
    }

    public void deleteStore(String str_name) {

        // StoreDAOオブジェクト生成
        StoreDAO storeDAO = new StoreDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = storeDAO.createConnection();

        //削除する
        storeDAO.deleteStore(str_name, this.connection);

        // DataBaseとの接続を切断する
        storeDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;
    }

    public boolean checkStore(String str_name) {

        // StoreDAOオブジェクト生成
        StoreDAO storeDAO = new StoreDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = storeDAO.createConnection();

        //チェックする
        isExist = storeDAO.checkStore(str_name, this.connection);

        // DataBaseとの接続を切断する
        storeDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return isExist;
    }

    public void favStore(String usr_name, String str_name) {

        // StoreDAOオブジェクト生成
        StoreDAO storeDAO = new StoreDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = storeDAO.createConnection();

        //削除する
        storeDAO.favStore(usr_name, str_name, this.connection);

        // DataBaseとの接続を切断する
        storeDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;
    }



}
