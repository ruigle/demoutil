package cn.threadlock;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 *@author lirui
 *@version 创建时间：2020-4-17 下午2:10:47
 *
 */
public class LockTest  {
	
	private String name;
	private String age;
	
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		LockTest lockTest = new LockTest();
		Constructor<?>[] constructors = lockTest.getClass().getDeclaredConstructors();
		
	}
	
	
	static HashMap<Long,ArrayList<Long>> cache=new HashMap<Long,ArrayList<Long>>();
	 
	static class TestThread extends Thread{
		
		Long orderNo;
		
		public TestThread(Long orderNo) {
			super();
			this.orderNo = orderNo;
		}
 
		@Override
		public void run() {
			try {
				sleep(2000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=0;i<60;++i){
				try{
				//	System.out.println("线程"+getId()+"等待锁"+orderNo+"……");
					OrderLock.lock(orderNo.toString());
					cache.get(orderNo).add(getId());
						try {
							sleep(20l);
						} catch (InterruptedException e) {}
				
					System.out.println("线程"+getId()+"获得锁"+orderNo+"……");		
				} finally{
					cache.get(orderNo).add(getId());
					OrderLock.unlock();
				}
				System.out.println("线程"+getId()+"释放锁"+orderNo+"……");
				try {
					sleep(20l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static String list2String(ArrayList<Long> l){
		String tmp="";
		for(Long i : l){
			tmp+=i+",";
		}
		return tmp;
	}

}
