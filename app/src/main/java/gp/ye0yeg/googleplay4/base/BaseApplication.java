package gp.ye0yeg.googleplay4.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;

import org.xutils.x;

/**
 * Created by Administrator on 4/26/2017.
 */

public class BaseApplication extends Application {

    private static Context context;
    private static Thread mainThread;
    private static long mainThredId;
    private static Looper mainLooper;
    private static Handler handler;

    public static Handler getHandler() {
        return handler;
    }

    public static Context getContext() {
        return context;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static long getMainThredId() {
        return mainThredId;
    }

    public static Looper getThreadMainLooper() {
        return mainLooper;
    }


    @Override
    public void onCreate() {
        context = getApplicationContext();
        mainThread = Thread.currentThread();
        mainThredId = Process.myTid();
        mainLooper = getMainLooper();
        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        handler = new Handler();

        super.onCreate();
    }
}
