package cn.thread.demo1;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.log4j.chainsaw.Main;

/**
 *@author lirui
 *@version 创建时间：2020-4-9 下午5:27:41
 *
 */
public class RunableTest implements Runnable{

	public void run() {
		while (true) {
            System.out.println("good time");
            int a=1/0;
        }
	}
	public static void main(String[] args) {
		RunableTest runableTest1 = new RunableTest();
        RunableTest runableTest2 = new RunableTest();
        new Thread(runableTest1).start();
        new Thread(runableTest1).start();
        new Thread(runableTest2).start();
	}

}
