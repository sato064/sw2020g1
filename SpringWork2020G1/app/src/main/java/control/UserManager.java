//　自分が格納されているフォルダ名
package control;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.List; //Listのインポート

import beans.User;
import dao.UserDAO;

public class UserManager {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public UserManager() {
    }

    // 追加
    // 引数はUserオブジェクト
    public void registUser(User user) {

        // UserDAOオブジェクト生成
        UserDAO userDAO = new UserDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = userDAO.createConnection();

        // UserオブジェクトをDataBaseに登録する
        userDAO.registUser(user, this.connection);

        // DataBaseとの接続を切断する
        userDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

    }

    public User login(User user) {

        // UserDAOオブジェクト生成
        UserDAO userDAO = new UserDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = userDAO.createConnection();

        //
        User login_user = userDAO.login(user, this.connection);

        // DataBaseとの接続を切断する
        userDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return login_user;

    }

}
