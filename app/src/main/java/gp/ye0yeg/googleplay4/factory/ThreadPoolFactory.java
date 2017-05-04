package gp.ye0yeg.googleplay4.factory;

import gp.ye0yeg.googleplay4.manager.ThreadPoolProxy;

/**
 * Created by Administrator on 5/4/2017.
 */

public class ThreadPoolFactory {
    static ThreadPoolProxy normalPool;
    static ThreadPoolProxy downLoadPool;

    //普通线程池
    public static ThreadPoolProxy getNormalPool(){
        if(normalPool == null){
            synchronized (ThreadPoolProxy.class){
                if(normalPool == null){
                    normalPool = new ThreadPoolProxy(5,5 ,3000);
                }
            }
        }
        return normalPool;
    }
    //下载线程池
    public static ThreadPoolProxy getDownLoadPool(){
        if(downLoadPool == null){
            synchronized (ThreadPoolProxy.class){
                if(downLoadPool == null){
                    downLoadPool = new ThreadPoolProxy(3,3 ,3000);
                }
            }
        }
        return downLoadPool;
    }

}
