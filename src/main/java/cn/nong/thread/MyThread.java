package cn.nong.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *@author lirui
 *@version 创建时间：2019-12-27 下午4:28:53
 *
 */
public class MyThread extends Thread {
	private CountDownLatch countDownLatch;    
    
    public MyThread(CountDownLatch countDownLatch)    
    {    
        this.countDownLatch = countDownLatch;    
    }    
	
	public void run()  
    {  
		System.out.println(this.getName() + "子线程开始");    
        try    
        {    
            // 子线程休眠五秒    
            Thread.sleep(5000);    
        }    
        catch (InterruptedException e)    
        {    
            e.printStackTrace();    
        }  
  
        System.out.println(this.getName() + "子线程结束");  
            
        // 倒数器减1  
        countDownLatch.countDown();  
    }  
    
    public static void main(String[] args) {
    	 long start = System.currentTimeMillis();  
         
         // 创建一个初始值为5的倒数计数器  
         CountDownLatch countDownLatch = new CountDownLatch(5);  
         for(int i = 0; i < 5; i++)  
         {  
             Thread thread = new MyThread(countDownLatch);  
             thread.start();  
         }  
           
         try  
         {  
             // 阻塞当前线程，直到倒数计数器倒数到0  
             countDownLatch.await();  
         }  
         catch (InterruptedException e)  
         {  
             e.printStackTrace();  
         }  
           
         long end = System.currentTimeMillis();  
         System.out.println("子线程执行时长：" + (end - start));  
    
    }
}


