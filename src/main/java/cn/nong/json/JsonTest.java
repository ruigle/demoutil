package cn.nong.json;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import cn.nong.model.Zhlj;

/**
 *@author lirui
 *@version 创建时间：2019-10-23 上午9:19:37
 *
 */
public class JsonTest {
	
	
	@Test
	public void jsonObj(){
		Zhlj zhlj = new Zhlj();
		zhlj.setId("1");
		zhlj.setTitle("zhangyang1");
		zhlj.setType("1");
		zhlj.setUrl("https://www.cnblogs.com/sharpest/p/7844533.html");
		Zhlj zhlj1 = new Zhlj();
		zhlj1.setId("1");
		zhlj1.setTitle("zhangyang2");
		zhlj1.setType("2");
		zhlj1.setUrl("https://www.cnblogs.com/sharpest/p/7844536.html");
		ArrayList<Zhlj> list = new ArrayList<Zhlj>();
		list.add(zhlj);
		list.add(zhlj1);
		String[] sd={"阿萨德","搜索"};
		JSONObject jo = new JSONObject();
		
		jo.put("name", "censhen");
		jo.put(1, "15"); 
		jo.put(2, 16); 
		jo.put("array", sd); 
		JSONObject jsonObject = JSONObject.fromObject(zhlj);

		
		System.out.println(jsonObject.toString());
//		System.out.println(jo.toString());
//		System.out.println(jo.getInt("1"));
//		boolean isArray = jo.isArray();
//        boolean isEmpty = jo.isEmpty();
//        boolean isNullObject = jo.isNullObject();
//        System.out.println("3：" + "是否是数组：" + isArray +" 是否是空：" + isEmpty + " 是否是空对象：" + isNullObject);
		
	}
	
	@Test
	public void jsonArray(){
		 //创建JSONArray
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0,"aa");
        jsonArray.add("BB");
        jsonArray.add(1,"AB");
        jsonArray.add("cc");
        
        System.out.println(jsonArray.toString());
        
        
		
	}
	

}
