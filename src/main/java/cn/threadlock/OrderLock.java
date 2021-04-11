package cn.threadlock;

import java.util.HashMap;

/**
 * 订单锁，当两个线程同时操作同一个oderNo订单时，进入临界区，不同的oderNo不涉及临界区操作.<br>
 * <p>Long order=1234l;<br>
 * try{<br>
 * 	OrderLock.lock(oderNo);<br>
 *  同步代码执行……<br>
 * }finally{<br>
 * 	OrderLock.unlock();<br>
 * }</p>
 * <br>
 *
 */
public class OrderLock  {
	
	private final static HashMap<Long,ObjectLocker<String>> LockerStore=new HashMap<Long,ObjectLocker<String>>();
	
	private final static Object lock=new Object();
	
	/**
	 * 尝试进入订单号临界区，其它线程若在其后调用此订单号进入，则等待此线程释放锁后才能进入临界区
	 * @param oderNo 订单号，若已有其它线程使用相同的订单号调用此方法，则需要等待其它线程调用过unlock方法后才能进入临界区
	 */
	public static void lock(String oderNo){
		ObjectLocker<String> objLoc=null;
		synchronized (lock) {
			for(Long id : LockerStore.keySet()){
				if(LockerStore.get(id).equals(oderNo)){
					objLoc=LockerStore.get(id);
				}
			}
			if(objLoc==null){
				objLoc=new ObjectLocker<String>(oderNo);
			}
			LockerStore.put(Thread.currentThread().getId(), objLoc);
		}
		objLoc.lock();
	}
	
	/**
	 * 解除锁，允许其它线程进入订单临界区
	 */
	public static void unlock(){
		ObjectLocker<String> objLoc=null;
		synchronized (lock) {
			objLoc=LockerStore.remove(Thread.currentThread().getId());
		}
		if(objLoc!=null){
			objLoc.unlock();
		}
	}
	
	/**
	 * 对象锁
	 *
	 * @param <T>
	 */
	static class ObjectLocker<T> {
 
		private final Object locker = new Object();
 
		private final static long nullThread = -1;
 
		private long threadId = nullThread;
		
		private int requestCount=0;
 
		private T target;
 
		public ObjectLocker(T target) {
			super();
			this.target = target;
		}
 
		@Override
		public boolean equals(Object obj) {
			if(target==null){
				return super.equals(obj);
			}else{
				return target.equals(obj);
			}
			
		}
		public void lock() {
			synchronized (locker) {
				requestCount++;
				do {
					long currThread=Thread.currentThread().getId();
					if (currThread == threadId || threadId == nullThread) {
						threadId = currThread;
						break;
					}
					try {
						locker.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (true);
 
			}
		}
 
		public void unlock() {
			synchronized (locker) {
				requestCount--;
				if (Thread.currentThread().getId() == threadId) {
					threadId = nullThread;
					locker.notifyAll();
				}
			}
		}
		
		public int getReqeustCount(){
			return requestCount;
		}
 
	}
	
}
