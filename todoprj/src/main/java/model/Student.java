//  自分が格納されているフォルダ名
package model;

public class Student {

    // 属性
    private String studentID = null; // 書籍名
    private String studentName = null; // 著者名
    private String studentPass = null; // 出版社名

    // 初期値を引数に持ったコンストラクタ
    public Student(String studentID, String studentName, String studentPass) {

        this.studentID = studentID;
        this.studentName = studentName;
        this.studentPass = studentPass;

    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public Student() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentPass(String studentPass) {
        this.studentPass = studentPass;
    }

    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public String getStudentID() {
        return this.studentID;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public String getStudentPass() {
        return this.studentPass;
    }
}
