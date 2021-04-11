package cn.thread.demo2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *@author lirui
 *@version 创建时间：2020-4-9 下午5:29:03
 *
 */
public interface AbstractStorage  {

	void procuct(int num);
	void consum(int num);

}
