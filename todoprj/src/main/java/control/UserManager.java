//　自分が格納されているフォルダ名
package control;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;

import beans.User;
import dao.UserDAO;

public class UserManager {

    // 属性
    private Connection connection = null;

    private boolean isCheck = false;

    // 引数を持たないコンストラクタ
    public UserManager() {
    }




    public Boolean authUser(String usr_name, String usr_pass) {

        // UserDAOオブジェクト生成
        UserDAO userDAO = new UserDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = userDAO.createConnection();

        //ユーザを検索し結果を代入する
        System.out.println(usr_name);
        System.out.println(usr_pass);
        isCheck = userDAO.authUser(usr_name, usr_pass, this.connection);

        // DataBaseとの接続を切断する
        userDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return isCheck;
    }
    public void registUser(String usr_name,String usr_pass) {

        // UserDAOオブジェクト生成
        UserDAO userDAO = new UserDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = userDAO.createConnection();

        //ユーザを検索し結果を代入する
        System.out.println(usr_name);
        System.out.println(usr_pass);
        userDAO.registUser(usr_name, usr_pass, this.connection);

        // DataBaseとの接続を切断する
        userDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

    }

}
