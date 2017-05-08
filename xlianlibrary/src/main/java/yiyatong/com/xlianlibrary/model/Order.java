package yiyatong.com.xlianlibrary.model;

/**
 * Created by alihaiseyao on 17/3/13.
 * 订单信息模型
 */
public class Order {

    private String tradeId;//交易流水号
    private String accessToken;//校验token
    private String userName;//用户名
    private String merNo;//发起商户的代码
    private String tradeType;//交易类型 00登陆 01支付
    private String packageName;//包名

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

}
