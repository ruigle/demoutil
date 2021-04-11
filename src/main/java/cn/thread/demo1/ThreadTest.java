package cn.thread.demo1;

import java.util.Iterator;

/**
 *@author lirui
 *@version 创建时间：2020-4-10 下午5:17:59
 *
 */
public class ThreadTest {
    
    public static void main(String[] args) throws InterruptedException {
    	Task task = new Task("售票任务", 100);
    	Thread t1 = new Thread(task);
    	Thread t2 = new Thread(task);
    	t1.start();
    	t2.start();
    }

    static class Task implements Runnable{
        String name;
        int num;
        
        public Task(String name) {
            this.name = name;
        }
        
        
        public Task(String name, int num) {
			super();
			this.name = name;
			this.num = num;
		}


		public void run() {
			
				while (num>1) {
					synchronized(this){
						System.out.println(Thread.currentThread().getName()+"出售了:"+num--+"票");
					}
				} 
			
			
			
//            System.out.println("first :" + Thread.interrupted());
//            System.out.println("first :" + Thread.currentThread().isInterrupted());
//            System.out.println("second:" + Thread.interrupted());
//            System.out.println("second :" + Thread.currentThread().isInterrupted());
//            System.out.println("task " + name + " is over");
        }
    }
}