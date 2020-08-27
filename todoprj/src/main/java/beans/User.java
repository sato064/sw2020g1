//  自分が格納されているフォルダ名
package beans;

public class User {

    // 属性
    private String userName = null;
    private String userPass = null;

    // 初期値を引数に持ったコンストラクタ
    public User(String userName, String userPass) {

        this.userName = userName;
        this.userPass = userPass;

    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public User() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }


    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public String getUsertName() {
        return this.userName;
    }

    public String getUserPass() {
        return this.userPass;
    }



}
