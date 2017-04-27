package gp.ye0yeg.googleplay4.utils;

import android.content.Context;
import android.content.res.Resources;

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

    public static String getPackageName() {
        return getContext().getPackageName();

    }
}
