package cn.time;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.Date;
import java.util.TimerTask;

import javax.swing.Timer;

/**
 *@author lirui
 *@version 创建时间：2020-4-17 下午2:10:47
 *
 */
public class TimeTest {

	public static void main(String[] args) throws AWTException {
//		Timer timer = new Timer(1, new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				System.err.println("延时任务");
//				
//			}
//		});
//		timer.start();
		
		
//		 Robot r=new Robot();
//		 System.out.println( "延时前:"+new Date().toString());
//		 r.delay(2000);
//		 System.out.println( "延时前:"+new Date().toString());
		java.util.Timer timer = new java.util.Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("延时任务");
			}
		}, 2000);
		System.out.println(1111);
	}
}
