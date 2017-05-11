package gp.ye0yeg.googleplay4.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程池，执行任务，提交任务
 * 使用单例模型，有且只有一个对象
 * Created by Administrator on 5/3/2017.
 */


/**
 * corePoolSize： 线程池维护线程的最少数量
 * <p>
 * maximumPoolSize：线程池维护线程的最大数量
 * <p>
 * keepAliveTime： 线程池维护线程所允许的空闲时间
 * <p>
 * unit： 线程池维护线程所允许的空闲时间的单位
 * <p>
 * workQueue： 线程池所使用的缓冲队列
 * <p>
 * handler： 线程池对拒绝任务的处理策略
 */
public class ThreadPoolProxy {
    ThreadPoolExecutor	mExecutor;			// 只需创建一次
    int					mCorePoolSize;
    int					mMaximumPoolSize;
    long				mKeepAliveTime;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        super();
        mCorePoolSize = corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
        mKeepAliveTime = keepAliveTime;
    }

    private ThreadPoolExecutor initThreadPoolExecutor() {//双重检查加锁
        if (mExecutor == null) {
            synchronized (ThreadPoolProxy.class) {
                if (mExecutor == null) {
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();// 无界队列
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();// 丢弃任务并抛出RejectedExecutionException异常。
                    mExecutor = new ThreadPoolExecutor(//
                            mCorePoolSize,// 核心的线程数
                            mMaximumPoolSize,// 最大的线程数
                            mKeepAliveTime, // 保持时间
                            unit, // 保持时间对应的单位
                            workQueue,// 缓存队列/阻塞队列
                            threadFactory, // 线程工厂
                            handler// 异常捕获器
                    );
                }
            }
        }
        return mExecutor;
    }

    /**执行任务*/
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**提交任务*/
    public Future<?> submit(Runnable task) {
        initThreadPoolExecutor();
        return mExecutor.submit(task);
    }

    /**移除任务*/
    public void removeTask(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }
}
