//　自分が格納されているフォルダ名
package service;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;

import model.Student;
import dao.StudentDao;

import javax.servlet.http.HttpServlet;

public class UserService extends HttpServlet {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public UserService() {
    }

    public void registStudent(Student student) {
        StudentDao studentDAO = new StudentDao();
        this.connection = studentDAO.createConnection();
        studentDAO.registStudent(student, this.connection);
        studentDAO.closeConnection(this.connection);
        this.connection = null;
    }

    // 検索
    public Student searchStudent(Student student) {
        StudentDao studentDAO = new StudentDao();
        this.connection = studentDAO.createConnection();
        student = studentDAO.searchStudent(student, this.connection);
        studentDAO.closeConnection(this.connection);
        this.connection = null;
        return student;
    }

    //ログイン
    public Student login(String mail, String pass) {
        StudentDao studentDAO = new StudentDao();
        this.connection = studentDAO.createConnection();
        Student student = studentDAO.login(mail, this.connection);
        studentDAO.closeConnection(this.connection);
        this.connection = null;
        if (pass.equals(student.getStudentPass())) {
            return student;
        } else {
            return null;
        }
    }


}
