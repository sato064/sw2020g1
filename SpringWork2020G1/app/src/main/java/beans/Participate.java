//  自分が格納されているフォルダ名
package beans;

public class Participate {

    private String user_id = null; // メールアドレス
    private Integer prj_id = null; //名前
    private Boolean is_prj_own; //パスワード

    // 初期値を引数に持ったコンストラクタ
    public Participate(String user_id, int prj_id, Boolean is_prj_own) {
        this.user_id = user_id;
        this.prj_id = prj_id;
        this.is_prj_own = is_prj_own;
    }

    public Participate(String user_id, int prj_id) {
        this.user_id = user_id;
        this.prj_id = prj_id;
    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public Participate() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setUserId(String user_id) {this.user_id = user_id;}
    public void setPrjId(int prj_id) {this.prj_id = prj_id;}
    public void setIsPrjOwn(Boolean is_prj_own) {this.is_prj_own = is_prj_own;}
    
    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public String getUserId() {return this.user_id;}
    public int getPrjId() {return this.prj_id;}
    public Boolean getIsPrjOwn() {return this.is_prj_own;}

}