package cn.nong.test;

import java.util.concurrent.TimeUnit;

/**
 *@author lirui
 *@version 创建时间：2019-12-17 下午1:10:49
 *
 */
public class MyRunableTask implements Runnable {

	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println( "延时1秒，完成了");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
