package cn.nong.thread;
/**
 *@author lirui
 *@version 创建时间：2019-12-31 下午3:39:59
 *
 */
public class MyUncaughtExceptionHandle implements Thread.UncaughtExceptionHandler{

	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + e);
	}

}
