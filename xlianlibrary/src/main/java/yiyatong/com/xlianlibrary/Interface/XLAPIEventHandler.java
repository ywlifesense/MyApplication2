package yiyatong.com.xlianlibrary.Interface;

import yiyatong.com.xlianlibrary.BaseClass.BaseReq;
import yiyatong.com.xlianlibrary.BaseClass.BaseResp;



/**
 * Created by alihaiseyao on 17/3/15.
 */
public interface XLAPIEventHandler {
    void onReq(BaseReq var1);

    void onResp(BaseResp var1);
}
