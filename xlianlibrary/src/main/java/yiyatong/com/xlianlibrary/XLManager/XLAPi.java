package yiyatong.com.xlianlibrary.XLManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.Date;
import java.util.List;

import yiyatong.com.xlianlibrary.model.Login;
import yiyatong.com.xlianlibrary.model.Order;

;


public class XLAPi {
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     *
     * @author xuzhaohu
     *
     */
    private static class XLAPiHolder {
        private static XLAPi instance = new XLAPi();
    }

    /**
     * 私有的构造函数
     */
    private XLAPi() {

    }

    public static XLAPi getInstance() {
        return XLAPiHolder.instance;
    }

    /**
     * 打开星链支付
     *
     * @param adroidActivity  当前activity
     * @param order           提交的订单
     */
    public void sendXLPayReq(Activity adroidActivity,Order order){
        Intent intent = new Intent();
        // 这里如果intent为空，就说名没有安装要跳转的应用嘛
        ComponentName componentName = new ComponentName("com.eawallet","io.dcloud.PandoraEntryActivity");
        if (hasApplication(adroidActivity,"com.eawallet")) {
            // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
            intent.setComponent(componentName);
            intent.putExtra("tradeType", XLKeys.TRADE_ORDER);
            intent.putExtra("accessToken", order.getAccessToken());
            intent.putExtra("merNo", order.getMerNo());
            intent.putExtra("tradeId", order.getTradeId());
            intent.putExtra("userName", order.getUserName());
            intent.putExtra("time", time(order.getUserName()));
            intent.putExtra("packageName",order.getPackageName());
            adroidActivity.startActivity(intent);
        } else {
            String mUrl =  "http://172.30.2.123/wallet-wap/toPay.do?accessToken="
                    +order.getAccessToken()+"&tradeId="+order.getTradeId()+"&userName="
                    +order.getUserName()+"&merNo="+order.getMerNo();
            intent.putExtra("url",mUrl);
            adroidActivity.startActivity(intent);

            //链接网址为"http://172.30.2.123/wallet-wap/toPay.do?accessToken="
//                    +order.getAccessToken()+"&tradeId="+order.getTradeId()+"&userName="
//                    +order.getUserName()+"&merNo="+order.getMerNo()

//            Uri uri = Uri.parse();
//            intent = new Intent(Intent.ACTION_VIEW, uri);
//            intent.addCategory(Intent.CATEGORY_DEFAULT);
//            intent.putExtra("url", "http://172.30.2.123/wallet-wap/toPay.do?accessToken="
//                    +order.getAccessToken()+"&tradeId="+order.getTradeId()+"&userName="
//                    +order.getUserName()+"&merNo="+order.getMerNo());
//            PackageManager pm = adroidActivity.getPackageManager();
//            List<ResolveInfo> resolveList = pm.queryIntentActivities(intent, PackageManager.MATCH_ALL);
//
//
//            if(resolveList.size() > 0) {
//                String title = "choose application";
//                Intent intentChooser = Intent.createChooser(intent, title);
//                adroidActivity.startActivity(intentChooser);
//            }else {
////                Toast.makeText(adroidActivity.this, "no match activity to start!", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    /**
     * 时间挫提取
     *
     * @param userName            用户名
     */
    public String time(String userName){
        Date dt= new Date();
        Long time= dt.getTime();
        return userName + time;
    }


    /**
     * 打开星链登陆
     *
     * @param adroidActivity    当前activity
     * @param login             登陆模型
     */
    public void sendXLLoginReq(Activity adroidActivity,Login login){
        Intent intent = new Intent();
        // 这里如果intent为空，就说名没有安装要跳转的应用嘛 （参数说明：包名 ，固定acitivity）
        ComponentName componentName = new ComponentName("com.eawallet","io.dcloud.PandoraEntryActivity");

        //参数 当前App的跳转activity，包名
        if (hasApplication(adroidActivity,"com.eawallet")) {
            // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
            intent.setComponent(componentName);
            intent.putExtra("tradeType", XLKeys.TRADE_LOGIN);
            intent.putExtra("accessToken", login.getAccessToken());
            intent.putExtra("userName", login.getUserName());
            intent.putExtra("time", time(login.getUserName()));
            adroidActivity.startActivity(intent);
        } else {
            // 没有安装要跳转的app应用，提醒一下

            showAlert(adroidActivity,this);
        }
    }

    /**
     * 打开星链登陆
     *
     * @param adroidActivity    当前activity
     * @param xlaPi             当前单例对象
     */
    public void showAlert(final Activity adroidActivity, final XLAPi xlaPi){

        new AlertDialog.Builder(adroidActivity).setTitle("温馨提示")//设置对话框标题

                .setMessage("系统检测到您未安装星链钱包，是否要下载？")//设置显示的内容

                .setPositiveButton("去下载",new DialogInterface.OnClickListener() {//添加确定按钮


                    @Override

                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                        //跳转下载地址
                        xlaPi.download(adroidActivity);

                    }

                }).setNegativeButton("不了",new DialogInterface.OnClickListener() {//添加返回按钮

            @Override

            public void onClick(DialogInterface dialog, int which) {//响应事件


            }

        }).show();//在按键响应事件中显示此对话框


    }

    /**
     * 跳转到下载网页
     *
     * @param adroidActivity  当前acitivity
     */
    public void download(Activity adroidActivity){
        Uri uri = Uri.parse(XLKeys.DOWNLOAD_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        adroidActivity.startActivity(intent);
    }

    /**
     * 检查客户机有没有安装星链
     *
     * @param context       当前acitivity
     * @param packageName   检查的包名
     */
    public boolean hasApplication(Context context, String packageName){
        PackageManager packageManager = context.getPackageManager();

        //获取系统中安装的应用包的信息
        List<PackageInfo> listPackageInfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < listPackageInfo.size(); i++) {
            if(listPackageInfo.get(i).packageName.equalsIgnoreCase(packageName)){
                return true;
            }
        }
        return false;

    }

}
