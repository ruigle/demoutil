package cn.thread.demo2;

import java.util.Iterator;

/**
 *@author lirui
 *@version 创建时间：2020-4-10 下午5:17:59
 *
 */
public class Producer extends Thread {
	private int num;
    public AbstractStorage abstractStorage;
 
    public Producer(AbstractStorage abstractStorage){
        this.abstractStorage = abstractStorage;
    }
 
    public void setNum(int num) {
        this.num = num;
    }
 
    public void produce(int num){
        abstractStorage.procuct(num);
    }
 
    @Override
    public void run() {
        produce(num);
    }
    
    public static void main(String[] args) {
		
    	AbstractStorage  abstractStorage = new Storage(100);
    	// 生产者对象
        Producer p1 = new Producer(abstractStorage);
        Producer p2 = new Producer(abstractStorage);
        Producer p3 = new Producer(abstractStorage);
        Producer p4 = new Producer(abstractStorage);
        
        // 消费者对象
        Consumer c1 = new Consumer(abstractStorage);
        Consumer c2 = new Consumer(abstractStorage);
        Consumer c3 = new Consumer(abstractStorage);
        
     // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(20);
        p3.setNum(70);
        p4.setNum(120);
 
        // 设置消费者产品消费数量
        c1.setNum(10);
        c2.setNum(20);
        c3.setNum(40);
 
        c1.start();
        c2.start();
        c3.start();
 
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    	
	}
    
	
}