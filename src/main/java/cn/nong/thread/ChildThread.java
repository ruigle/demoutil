package cn.nong.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *@author lirui
 *@version 创建时间：2019-12-31 下午4:51:53
 *
 */
public class ChildThread implements Callable {

	public Object call() throws Exception {
		Thread.currentThread().sleep(2000);
		System.out.println("do something 1");
		exceptionMethod();
		System.out.println("do something 2");
		return "test result";
	}
	
	private void exceptionMethod() {
		throw new RuntimeException("ChildThread1 exception");
	}
	
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		Future future = executorService.submit(new ChildThread());
		System.out.println("thread run()");
		try {
			future.get();//会造成程序阻塞
		} catch (InterruptedException e) {
			System.out.println(String.format("handle exception in child thread. %s", e));
		} catch (ExecutionException e) {
			System.out.println(String.format("handle exception in child thread. %s", e));
		} finally {
			if (executorService != null) {
				executorService.shutdown();
			}
		}
		System.out.println("all done");
	}

}
