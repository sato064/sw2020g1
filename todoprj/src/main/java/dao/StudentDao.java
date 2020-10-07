//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Project;
import utility.DriverAccessor;
import model.Student;

public class StudentDao extends DriverAccessor{
    public static final String REGIST_STUDENT = "insert into student_info values(?, ?, ?)";
    public static final String SEARCH_STUDENT =  "select * from student_info where student_id = ?";
    public static final String LOGIN = "select * from student_info where student_id = ?";

    // 情報をデータベースに登録する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public void registStudent(Student student, Connection connection) {
        try {
            // SQLコマンドの実行
            PreparedStatement statement = connection.prepareStatement(REGIST_STUDENT);

            // SQLコマンドのクエッションマークに値を、1番目から代入する
            statement.setString(1, student.getStudentID());
            statement.setString(2, student.getStudentName());
            statement.setString(3, student.getStudentPass());
            statement.executeUpdate();

        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力する
            e.printStackTrace();
        } finally {
        }
    }

    // 検索する
    // 引数はStudentオブジェクトと、Connectionオブジェクト
    public Student searchStudent(Student student, Connection connection) {
        try {
            // 実行結果はrsに格納される
            PreparedStatement statement = connection.prepareStatement(SEARCH_STUDENT);
            statement.setString(1, student.getStudentID());
            ResultSet rs = statement.executeQuery();


            rs.first();
            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
            student.setStudentName(rs.getString("student_name"));
            student.setStudentPass(rs.getString("student_pass"));
            // 終了処理
            statement.close();
            rs.close();
            // Studentオブジェクトを返す
            return student;
        } catch (SQLException e) {

            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;

        } finally {
        }
    }

    //ログイン
    public Student login(String mail, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(LOGIN);
            statement.setString(1, mail);
            ResultSet rs = statement.executeQuery();

            rs.first();
            Student student = new Student();
            // rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
            if (rs.first()) {
                student.setStudentName(rs.getString("student_name"));
                student.setStudentPass(rs.getString("student_pass"));
                student.setStudentID(rs.getString("student_id"));
            } else {
            }
            statement.close();
            rs.close();
            return student;
        } catch (SQLException e) {
            // エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
            e.printStackTrace();
            return null;
        } finally {
        }
    }

}
