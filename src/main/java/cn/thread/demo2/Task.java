package cn.thread.demo2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *@author lirui
 *@version 创建时间：2020-4-15 下午3:52:38
 *
 */
public class Task implements Callable<String>{

	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("子线程开始了");
//		int a=1/0;
		Thread.sleep(2000);
//		int a=1;
//		while (a==1) {
//		}
		System.out.println("子线程休眠结束了");
		return "success";
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Task task = new Task();
		Callable<String> callable = new Callable<String>() {

			public String call() throws Exception {
				System.out.println("子线程");
				return "success";
			}
		};
		FutureTask<String> task2 = new FutureTask<String>(callable);
		
		Future<String> result = executorService.submit(task);
		executorService.shutdown();
		System.out.println(result.cancel(false));//子线程阻塞会导致 java.util.concurrent.CancellationException
//		System.out.println(result.isCancelled());
		System.out.println(result.isDone());
		
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
         
        System.out.println("主线程在执行任务");
         
        try {
            System.out.println("task运行结果"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException");
        }
         
        System.out.println("所有任务执行完毕");
	}

}
