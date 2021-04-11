package cn.thread.demo2;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *@author lirui
 *@version 创建时间：2020-4-9 下午5:29:03
 *
 */
public class Storage  implements AbstractStorage  {
	private Object product=new Object();//生产线程锁对象
	private Object consum=new Object();//消费线程锁对象

	int maxStorage;//库存 
	
	private LinkedList list = new LinkedList();
	
	public Storage(int maxStorage) {
		super();
		this.maxStorage = maxStorage;
	}
	
    public void consum(int num) {
        synchronized (list){
            while (list.size()<num){
                System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:"+ list.size() + "\t暂时不能执行消费任务!");
                try {
                    list.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
 
            for(int i=0;i<num;i++){
                list.remove();
            }
            System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());
 
            list.notifyAll();
        }
    }
 
    public void procuct(int num) {
        synchronized (list){
            while(list.size()+num > maxStorage){
                System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:" + list.size() + "\t暂时不能执行生成任务!");
                try {
                    list.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
 
            }
            for(int i=0;i<num;i++){
                list.add(new Object());
            }
 
            System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }


}
