//  自分が格納されているフォルダ名
package beans;

public class User {

    // 属性
    private int id_int; //id
    private String id = null; // メールアドレス
    private String name=null; //名前
    private String password; //パスワード

    // 初期値を引数に持ったコンストラクタ
    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public User() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPassword(String password) {this.name = password;}
    
    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public String getId() {return this.id;}
    public String getName() {return this.name;}
    public String getPassword() {return this.password;}

}
