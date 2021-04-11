package cn.nong.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *@author lirui
 *@version 创建时间：2019-8-5 下午4:29:54
 *
 */
public class TimerDemo {
	
	public static void main(String[] args) {
//		timer1();
//      timer2();
//      timer3();
      timer4();
    }
	
	
	 /**
     * 设定2000毫秒后执行
     */
    public static void timer1(){
        Timer nTimer = new Timer();
        final long startTime=System.currentTimeMillis();
        System.out.println("startTime"+startTime+"ms");
        nTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("----设定要指定任务-----");
                long endTime=System.currentTimeMillis();
                System.out.println("endTime"+endTime+"ms");
                System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
            }
        },2000);
     
    }
    /**
    * 延迟5000毫秒，每1000毫秒执行一次
    */
   public static void timer2() {
       Timer timer = new Timer();
       timer.schedule(new TimerTask() {
           public void run() {
               System.out.println("-------延迟5000毫秒，每1000毫秒执行一次--------");
           }
       }, 5000, 1000);
   }
   
   /**
    * 延迟5000毫秒，每1000毫秒执行一次
    */
   public static void timer3() {
       Timer timer = new Timer();
       timer.scheduleAtFixedRate(new TimerTask() {
           public void run() {
               System.err.println("-------延迟5000毫秒，每1000毫秒执行一次--------");
           }
       }, 5000, 1000);
   }
   
   /**
    * 设置17：56执行任务
    * java.util.Timer.scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
    */
   public static void timer4() {
       Calendar calendar = Calendar.getInstance();
       calendar.set(Calendar.HOUR_OF_DAY, 16);
       calendar.set(Calendar.MINUTE, 45);
       calendar.set(Calendar.SECOND, 0);

       Date time = calendar.getTime();

       Timer timer = new Timer();
       timer.scheduleAtFixedRate(new TimerTask() {
           public void run() {
               System.out.println("-------设定要指定任务--------");
           }
       }, time, 1000*60);// 这里设定将延时每天固定执行
   }
   
   
}
