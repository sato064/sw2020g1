//  自分が格納されているフォルダ名
package beans;

public class Restaurant {

    // 属性
    private int id; //id
    private String name=null; //名前

    // 初期値を引数に持ったコンストラクタ
    public Restaurant(String name) {
      this.name = name;
    }

    public Restaurant(int id,String name) {
      this.id = id;
      this.name = name;
    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public Restaurant() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    
    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public int getId() {return this.id;}
    public String getName() {return this.name;}

}