package cn.nong.thread;

import java.util.concurrent.ThreadFactory;

/**
 *@author lirui
 *@version 创建时间：2019-12-31 下午3:41:07
 *
 */
public class HandleThreadFactory implements ThreadFactory {

	public Thread newThread(Runnable r) {
		System.out.println("create thread t");
        Thread t = new Thread(r);
        System.out.println("set uncaughtException for t");
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandle());
        return t;

	}

}
