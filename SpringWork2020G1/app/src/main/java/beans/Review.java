//  自分が格納されているフォルダ名
package beans;

public class Review {

    // 属性
    private int id; //id
    private String information = null; // グルメ情報
    private int goodCount; //役に立ったポイント
    private String scene=null; //Morning,Lunch,Dinner
    private User user;
    private int user_id;
    private int restaurant_id;

    // 初期値を引数に持ったコンストラクタ
    public Review(String information, int goodCount, String scene, int user_id, int restaurant_id) {
        //this.id = id;
        this.information = information;
        this.goodCount = goodCount;
        this.scene = scene;
        this.user_id = user_id;
        this.restaurant_id = restaurant_id;
    }

    public Review(int id, String information, int goodCount, String scene, User user) {
        this.id = id;
        this.information = information;
        this.goodCount = goodCount;
        this.scene = scene;
        this.user = user;

    }

    public Review(int id) {
        this.id = id;
    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public Review() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setId(int id) {this.id = id;}
    public void setInformation(String information) {this.information = information;}
    public void setGoodCount(int goodCount) {this.goodCount = goodCount;}
    public void setScene(String scene) {this.scene = scene;}
    public void setUser(User user) {this.user = user;}
    public void setUserId(int user_id) {this.user_id = user_id;}
    
    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public int getId() {return this.id;}
    public String getInformation() {return this.information;}
    public int getGoodCount() {return this.goodCount;}
    public String getScene() {return this.scene;}
    public User getUser() {return this.user;}
    public int getUserId() {return this.user_id;}


}
