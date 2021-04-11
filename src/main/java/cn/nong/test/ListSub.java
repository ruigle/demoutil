package cn.nong.test;

import java.util.ArrayList;
import java.util.List;

/**
 *@author lirui
 *@version 创建时间：2019-11-1 上午9:31:32
 *
 */
public class ListSub {
	
	public static void main(String[] args) {
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("11");
		arrayList.add("22");
		arrayList.add("33");
		arrayList.add("44");
		
		arrayList = arrayList.subList(0, 4);
		System.out.println(arrayList);
	}

}
