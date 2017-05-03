package gp.ye0yeg.threadpooldemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 5/3/2017.
 */

public class ThreadPoolDemo {
    public static void main(String[] args){
        ExecutorService es = Executors.newSingleThreadExecutor();

        for(int i = 0 ; i<111 ;i++){
        }
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThread - task");
    }
}
