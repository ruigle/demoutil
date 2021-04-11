package cn.lock;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.Timer;

/**
 *@author lirui
 *@version 创建时间：2020-4-17 下午2:10:47
 *
 */
public class LockTest {

	public static void main(String[] args) throws AWTException {
		
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		
		try {
//			lock.tryLock(2000, TimeUnit.MILLISECONDS);
			Thread.sleep(2000);
			System.out.println(lock.isLocked());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			try {
				lock.unlock();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("1");
		
	}
}
