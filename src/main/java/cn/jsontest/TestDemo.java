package cn.jsontest;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

import cn.nong.test.ST;

public class TestDemo {
	
	public static void main(String[] args) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("key", "11");
		hashMap.put("val","1");
		ST st = new ST();
		st.setName("ss");
		System.err.println(JSON.toJSONString(st));
		
	}
	
}

