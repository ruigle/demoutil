package cn.thread.demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *@author lirui
 *@version 创建时间：2020-4-9 下午5:29:03
 *
 */
public class CallTest implements Callable {

	public Object call() throws Exception {
		int a=1/0;
		return "hello world";
	}
	public static void main(String[] args){
//        FutureTask<String> futureTask = new FutureTask<String>(new CallTest());
//        new Thread(futureTask).start();//任务型
//        try {
//            String result = futureTask.get();//当前主线程阻塞
//            System.out.println(result);
//        } catch (InterruptedException e) {
////            e.printStackTrace();
//            System.out.println(1111);
//        } catch (ExecutionException e) {
////            e.printStackTrace();
//            System.out.println(2222);
//        }
		Callable<String> callable = new Callable<String>() {
			public String call() throws Exception {
//				this.wait(1000);//抛出 java.lang.IllegalMonitorStateException
				return "info";
			}
		};
		FutureTask<String> task = new FutureTask<String>(callable);
		new Thread(task).start();
		try {
			String string = task.get();
			System.out.println(string);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("11");
		
    }

}
