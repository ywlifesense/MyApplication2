package yiyatong.com.xlianlibrary.model;

/**
 * Created by alihaiseyao on 17/3/13.
 */
public class Login {

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

    private String accessToken;//校验token
    private String userName;//用户名
}
