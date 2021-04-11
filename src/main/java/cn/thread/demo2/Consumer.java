package cn.thread.demo2;

import java.util.Iterator;

/**
 *@author lirui
 *@version 创建时间：2020-4-10 下午5:17:59
 *
 */
public class Consumer extends Thread {
	private int num;
    public AbstractStorage abstractStorage;

    public Consumer(AbstractStorage abstractStorage){
        this.abstractStorage = abstractStorage;
    }
    public void setNum(int num) {
        this.num = num;
    }
 
    public void consume(int num){
        abstractStorage.consum(num);
    }
 
    @Override
    public void run() {
    	consume(num);
    }
    
	
}