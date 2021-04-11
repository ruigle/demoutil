package cn.nong.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import cn.nong.util.baijingwei.BaiduMapUtils;
import cn.nong.util.loadxls.ExcelInfo;


/**
 *
 */
public class Demo3 {
	
	public static void main(String[] args) throws InterruptedException  {
		
		ReentrantLock lock = new ReentrantLock();
		final ST st = new ST();
		st.setName("初始");
		st.setLock(lock);
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("线程一 开始执行。。。");
				try {
					st.update("张三");
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName()+"被中断(锁释放)。。。");
				}
				System.out.println("线程一 结束执行。。。");
			}
		}, "线程一");
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				System.out.println("线程二 开始执行。。。");
				try {
					st.update("李瑞");
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName()+"被中断(锁释放)。。。");
				}
				System.out.println("线程二 结束执行。。。");
			}
		}, "线程二");
		
		
		t1.start();
		TimeUnit.SECONDS.sleep(1);
        t2.start();
        System.out.println(st.getName());
//        t1.interrupt();
//        try {
//        	t1.join();
//			t2.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}
	
	

}
