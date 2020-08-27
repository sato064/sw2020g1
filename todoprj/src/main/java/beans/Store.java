//  自分が格納されているフォルダ名
package beans;

public class Store {

    // 属性
    private String storeName = null;
    private String storeInfo = null;

    // 初期値を引数に持ったコンストラクタ
    public Store(String storeName, String storeInfo) {

        this.storeName = storeName;
        this.storeInfo = storeInfo;

    }

    // 初期値を引数に持たないコンストラクタ
    // Java beansは初期値を持たないコンストラクタが必ず必要
    public Store() {
    }

    // setメソッド
    // Java beansのsetメソッドはsetの後ろに続く文字列が必ず大文字であること
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreInfo(String storeInfo) {
        this.storeInfo = storeInfo;
    }


    // getメソッド
    // Java beansのgetメソッドはgetの後ろに続く文字列が必ず大文字であること
    public String getStoreName() {
        return this.storeName;
    }

    public String getStoreInfo() {
        return this.storeInfo;
    }



}
