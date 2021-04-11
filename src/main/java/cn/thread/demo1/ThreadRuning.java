package cn.thread.demo1;
/**
 *@author lirui
 *@version 创建时间：2020-4-9 下午5:24:31
 *
 */
public class ThreadRuning extends Thread{
	public ThreadRuning(String name){   //重写构造，可以对线程添加名字        
		super(name);     
	}

	@Override
	public void run() {
		while(true){             
			System.out.println("good time"); 
//			 int a=1/0;
			//在run方法里，this代表当前线程            
			System.out.println(this);  
			System.out.println(Thread.currentThread().isAlive());
		}
	}
	public static void main(String[] args) {
		ThreadRuning threadRuning = new ThreadRuning("1111"); 
		try {
			threadRuning.start();
//			threadRuning.interrupt();
		} catch (Exception e) {
			System.out.println("异常了");
		}
		
	}
	
	

}
