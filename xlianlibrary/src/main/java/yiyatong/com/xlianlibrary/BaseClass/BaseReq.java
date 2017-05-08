package yiyatong.com.xlianlibrary.BaseClass;

import android.os.Bundle;

/**
 * Created by alihaiseyao on 17/3/15.
 */
public abstract class BaseReq {
    public String transaction;
    public String openId;

    public BaseReq() {
    }

    public abstract int getType();

    public void toBundle(Bundle var1) {
        var1.putInt("_xlapi_command_type", this.getType());
        var1.putString("_xlapi_basereq_transaction", this.transaction);
        var1.putString("_xlapi_basereq_openid", this.openId);
    }

    public void fromBundle(Bundle var1) {
        this.transaction = var1.getString("_xlapi_basereq_transaction");
        this.openId = var1.getString("_xlapi_basereq_openid");
    }

    public abstract boolean checkArgs();
}
