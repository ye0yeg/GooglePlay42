package gp.ye0yeg.googleplay4.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;

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

        handler = new Handler();

        super.onCreate();
    }
}
