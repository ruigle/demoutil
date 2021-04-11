package cn.thread.demo3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
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
public class ThreadPooltest {
	public static void main(String[] args) {
		
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0l, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1),new RejectedExecutionHandler() {
			
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println("222"+executor);
//				System.out.println(executor.getActiveCount());
				new Thread(r).start();
			}
		});//一个固定线程池
		Callable<String> call1 = new Callable<String>() {

			public String call() throws Exception {
				Thread.sleep(2000l);
				System.err.println(Thread.currentThread().getName()+":子线程执行了");
				return "success";
			}
		};
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000l);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println(Thread.currentThread().getName()+":子线程执行了");
			}
		};
		System.out.println("111"+threadPoolExecutor);
		Future<String> result = threadPoolExecutor.submit(call1);
		threadPoolExecutor.execute(runnable);
		threadPoolExecutor.execute(runnable);
		threadPoolExecutor.execute(runnable);
		threadPoolExecutor.execute(runnable);
		try {
			System.err.println(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Executors.newFixedThreadPool(1);
		Executors.newCachedThreadPool();
	}

}
