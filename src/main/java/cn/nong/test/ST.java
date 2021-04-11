package cn.nong.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 *@author lirui
 *@version 创建时间：2019-12-17 下午3:56:11
 *
 */
public class ST {
	private String name;
	private int age;
	private Integer age1;
	private Object obj;
	private Lock lock=null;

	public Integer getAge1() {
		return age1;
	}

	public void setAge1(Integer age1) {
		this.age1 = age1;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}
	public void update(String name) throws InterruptedException{
//		   lock.lock();
		   boolean tryLock = lock.tryLock(3000, TimeUnit.SECONDS);//尝试获取锁
		   //中断只是在当前线程获取锁之前，或者当前线程获取锁的时候被阻塞
//		   lock.lockInterruptibly();
//	       lock.tryLock(3000, TimeUnit.SECONDS);
		   try{
			   if(tryLock){
				   setName(name);
				   System.out.println(Thread.currentThread().getName()+" 变换后的姓名为"+this.name);
				   TimeUnit.SECONDS.sleep(3);
				   System.out.println(Thread.currentThread().getName()+" 变换后的姓名为"+this.name);
			   }
		   }finally{
			   lock.unlock();
		   }
	   }

	

}
