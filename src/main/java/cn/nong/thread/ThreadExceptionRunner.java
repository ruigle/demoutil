package cn.nong.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *@author lirui
 *@version 创建时间：2019-12-31 下午3:32:04
 *
 */
public class ThreadExceptionRunner   implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		throw new RuntimeException("error !!!!");
	}

	
	public static void main(String[] args) {
		try {
            Thread thread = new Thread(new ThreadExceptionRunner());
            thread.start();
        } catch (Exception e) {
            System.out.println("========");
            e.printStackTrace();
        } finally {
        }
        System.out.println(123);
        ExecutorService exec = Executors.newCachedThreadPool(new HandleThreadFactory());
        exec.execute(new ThreadExceptionRunner());
        exec.shutdown();
	}


	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		
	}
}
