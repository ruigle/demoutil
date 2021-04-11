package cn.thread.demo3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *@author lirui
 *@version 创建时间：2020-4-9 下午5:29:03
 *
 */
public class Task implements Runnable {

	public static void main(String[] args){
//		ExecutorService executorService = Executors.newFixedThreadPool(1);
//		Runnable runnable = new Runnable() {
//			public void run() {
//				int a=1/0;
//				System.out.println("zxiiancheng");
//			}
//		};
//		Thread thread = new Thread(new  Runnable() {
//			public void run() {
//				System.out.println("zxiiancheng11");
//			}
//		});
//		executorService.execute(thread);
//		System.out.println("111");
		
//		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
//		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//			public void run() {
//				System.out.println(11);
//			}
//		}, 1, 1, TimeUnit.SECONDS);
//		scheduledThreadPool.shutdown();
//		System.out.println(Thread.currentThread().getName()+"线程结束了");
		
		ExecutorService ex = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                System.out.println("创建线程"+t);
                return  t;
            }
        });
		ex.execute(new Task());
		
    }
	public void run() {

		System.out.println("子线程");
	}

}
