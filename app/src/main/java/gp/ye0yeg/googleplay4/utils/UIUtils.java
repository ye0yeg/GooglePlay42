package gp.ye0yeg.googleplay4.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;

import gp.ye0yeg.googleplay4.base.BaseApplication;

/**
 * Created by Administrator on 4/26/2017.
 */

public class UIUtils {

    public static Context getContext(){
        return BaseApplication.getContext();
    }

    //获得Resource对象
    public static Resources getResources(){
        return getContext().getResources();
    }

    //获得String.xml的字符串
    public static String getString(int resId){
        return getResources().getString(resId);
    }

    //得到String.xml中的字符串数组
    public static String[] getStringArray(int resId){
        return getResources().getStringArray(resId);
    }
    //得到color.xml的颜色
    public static int getColor(int colorId){
        return getResources().getColor(colorId);
    }

    //得到应用程序包名
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    //得到主线程的ID
    public static long getMainThread(){
        return BaseApplication.getMainThredId();
    }

    //得到主线程的Hanlder
    public static Handler getMainThreadHandler(){
        return BaseApplication.getHandler();
    }

    //安全执行任务的方法
    public static void postTaskSafely(Runnable task){
        int curThreadId = Process.myTid();
        //如果当前线程是主线程
        if(curThreadId == getMainThread()){
            task.run();
        }else
        {//如果当前线程不是主线程，那么将当前线程发到主线程
            getMainThreadHandler().post(task);
        }
    }
}
