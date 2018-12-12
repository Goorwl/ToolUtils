import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Description : toast的封装
 * Copyright   : (c) 2016
 * Author      : Goorwl
 * Email       : goorwl@163.com
 * Date        : 2017/1/15 21:50
 */

public class ToastUtils {

    //获取主线程的handler
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void showToast(final Context ctx, final String msg){
        //判断当前是否在主线程
        if (Looper.myLooper() == Looper.getMainLooper()){
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
        }else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
