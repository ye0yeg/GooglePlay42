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
    ThreadPoolExecutor executor;
    int corePoolSize;
    int maximumPoolSize;
    long keepAliveTime;

    //构造外部方法让他注入
    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    private ThreadPoolExecutor initThreadPoolExecutor() {
        if (executor == null) {
            TimeUnit unit = TimeUnit.MICROSECONDS;
            BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();//无界队列
            ThreadFactory threadFactory = Executors.defaultThreadFactory();//默认的线程工厂
            RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
            executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
//            return executor;
        };
        return executor;
    }

    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        executor.execute(task);
    }


    /**
     * 提交任务
     * Future 可以获得异常
     */
    public Future<?> submit(Runnable task) {
        initThreadPoolExecutor();
        return executor.submit(task);
    }

    /**
     * 移除任务
     */
    public void removeTask(Runnable task) {
        initThreadPoolExecutor();
        executor.remove(task);
    }


}
