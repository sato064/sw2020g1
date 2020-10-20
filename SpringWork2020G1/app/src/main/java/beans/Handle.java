//  自分が格納されているフォルダ名
package beans;

public class Handle {

    private Integer task_id = null; // メールアドレス
    private Integer prj_id = null; //名前
    private String user_id = null; //パスワード

    // 初期値を引数に持ったコンストラクタ
    public Handle(Integer task_id, Integer prj_id,String user_id) {
        this.user_id = user_id;
        this.prj_id = prj_id;
        this.task_id = task_id ;
    }



    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public Handle() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setUserId(String user_id) {this.user_id = user_id;}
    public void setTaskId(int task_id) {this.task_id = task_id;}

    
    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public String getUserId() {return this.user_id;}
    public Integer getTaskId() {return this.task_id;}

}