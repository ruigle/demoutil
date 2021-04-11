package cn.queue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.swing.Timer;

/**
 *@author lirui
 *@version 创建时间：2020-4-17 下午2:10:47
 *
 */
public class QueueTest {
	public static void main(String[] args) {
		ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<String>();
		concurrentLinkedDeque.offer("aaa");
		concurrentLinkedDeque.offer("bbb");
		concurrentLinkedDeque.offer("ccc");
		concurrentLinkedDeque.offer("ddd");
		concurrentLinkedDeque.offerFirst("eee");
		concurrentLinkedDeque.offerLast("fff");
		concurrentLinkedDeque.offer("aaa");
		System.out.println(concurrentLinkedDeque.size());
		System.out.println(concurrentLinkedDeque.peek());
		System.out.println(concurrentLinkedDeque.poll());
		System.out.println(concurrentLinkedDeque.pollLast());
		System.out.println(concurrentLinkedDeque.size());
		
	}

}
