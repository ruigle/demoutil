package cn.nong.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import cn.nong.util.baijingwei.BaiduMapUtils;
import cn.nong.util.loadxls.ExcelInfo;


/**
 *
 */
public class Demo4 {
	
	public static void main(String[] args) throws InterruptedException  {
		Thread.currentThread().interrupt();
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " 当前线程是否已停止：=" + Thread.currentThread().isInterrupted());
        System.out.println(threadName + " 当前线程是否已停止：=" + Thread.currentThread().isInterrupted());

	}
	
	

}
