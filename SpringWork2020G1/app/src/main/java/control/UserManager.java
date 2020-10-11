//　自分が格納されているフォルダ名
package control;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.util.List; //Listのインポート
import java.util.ArrayList;

import beans.User;
import dao.UserDAO;
import javax.servlet.http.HttpServlet;

public class UserManager extends HttpServlet{
    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public UserManager() {
    }

    public List<String> userList() {
        UserDAO userDAO = new UserDAO();
        this.connection = userDAO.createConnection();
        List<String> userList = userDAO.userList(this.connection);
        userDAO.closeConnection(this.connection);
        this.connection = null;
        return userList;
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

    public User login(String id,String pass) {

        // UserDAOオブジェクト生成
        UserDAO userDAO = new UserDAO();

        // DataBaseへ接続し、コネクションオブジェクトを生成する
        this.connection = userDAO.createConnection();

        //
        User login_user = userDAO.login(id,pass,this.connection);

        // DataBaseとの接続を切断する
        userDAO.closeConnection(this.connection);

        // コネクションオブジェクトを破棄する
        this.connection = null;

        return login_user;

    }

}