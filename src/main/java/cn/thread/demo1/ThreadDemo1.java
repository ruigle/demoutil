package cn.thread.demo1;
/**
 *@author lirui
 *@version 创建时间：2020-4-9 下午5:53:45
 *
 */
public class ThreadDemo1 {
	
	public static void main(String[] args) {
        MyThread mt = new MyThread();
 
        //推荐
        MyRunnable mr = new MyRunnable();
        Thread t2 = new Thread(mr);
 
        mt.start();//启动线程
        t2.start();
 
 
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
 
    }

}
