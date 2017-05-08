package yiyatong.com.xlianlibrary.BaseClass;

import android.os.Bundle;

/**
 * Created by alihaiseyao on 17/3/15.
 */
public class BaseResp {

    public String merOrderId;
    public String respCode;

    public BaseResp() {
    }


    public void toBundle(Bundle var1) {
        var1.putString("merOrderId", this.merOrderId);
        var1.putString("respCode", this.respCode);
    }

    public void fromBundle(Bundle var1) {
        this.merOrderId = var1.getString("merOrderId");
        this.respCode = var1.getString("respCode");
    }
}
