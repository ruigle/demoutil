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

import org.junit.Test;

import cn.nong.util.baijingwei.BaiduMapUtils;
import cn.nong.util.loadxls.ExcelInfo;


/**
 *
 */
public class Demo {
	
	
	@Test
	public void test(){
		ExcelInfo excelInfo = new ExcelInfo();
		try {
			excelInfo.doExcel("C:/Users/admin/Desktop/ceshizhuanyong/jingxian.xls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Test
	public void test01(){
		String str="<p>  55555    2</p>";
		System.out.println(str);
		String replaceAll = str.replaceAll("kk", "");
		System.out.println(replaceAll);
	}
	

	@Test
	public void test2(){
		BaiduMapUtils.getLngAndLat("天津市武清区下伍旗镇柴庄");
	} 

	
	@Test
	public void test3(){
		System.out.println(UUID.randomUUID().toString().toUpperCase());
	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		// 获取当前年份、月份、日期  
        Calendar cale = Calendar.getInstance();  
        int year = cale.get(Calendar.YEAR);  
        int month = cale.get(Calendar.MONTH) + 1;  
        int day = cale.get(Calendar.DATE);  
        int hour = cale.get(Calendar.HOUR_OF_DAY);  
        int minute = cale.get(Calendar.MINUTE);  
        int second = cale.get(Calendar.SECOND);  
        int dow = cale.get(Calendar.DAY_OF_WEEK);  
        int dom = cale.get(Calendar.DAY_OF_MONTH); 
        int doy = cale.get(Calendar.DAY_OF_YEAR);  
        //本周第一天日期
        Calendar calendar = Calendar.getInstance();
        int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); //获取周开始基准
        int current = calendar.get(Calendar.DAY_OF_WEEK); //获取当天周内天数
        System.out.println("current"+current);
        calendar.add(Calendar.DAY_OF_MONTH, min-current); //当天-基准，获取周开始日期
        Date start = calendar.getTime();
        System.out.println("本周第一天："+format.format(start));
        
        System.out.println("Current Date: " + cale.getTime());  
        System.out.println("Year: " + year);  
        System.out.println("Month: " + month);  
        System.out.println("Day: " + day);  
        System.out.println("Hour: " + hour);  
        System.out.println("Minute: " + minute);  
        System.out.println("Second: " + second);  
        System.out.println("Day of Week: " + dow);  
        System.out.println("Day of Month: " + dom);  
        System.out.println("Day of Year: " + doy);  
  
        // 获取当月第一天和最后一天  
        String firstday, lastday;  
        // 获取前月的第一天  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        firstday = format.format(cale.getTime());  
        // 获取前月的最后一天  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 4);  
//        cale.set(Calendar.DAY_OF_MONTH, 0);  
        lastday = format.format(cale.getTime());  
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);  
  
        // 获取当前日期字符串  
        Date d = new Date();  
        System.out.println("当前日期字符串1：" + format.format(d));  
        System.out.println("当前日期字符串2：" + year + "/" + month + "/" + day + " "  
                + hour + ":" + minute + ":" + second);  
	}
	
//	 public static void main(String[] args) {
//	        Map<String, String> map = new TreeMap<String, String>(
//	                new Comparator<String>() {
//	                    public int compare(String obj1, String obj2) {
//	                        // 降序排序
//	                        return obj1.compareTo(obj2);
//	                    }
//	                });
//	        map.put("c", "ccccc");
//	        map.put("a", "aaaaa");
//	        map.put("b", "bbbbb");
//	        map.put("d", "ddddd");
//
//	        Set<String> keySet = map.keySet();
//	        Iterator<String> iter = keySet.iterator();
//	        while (iter.hasNext()) {
//	            String key = iter.next();
//	            System.out.println(key + ":" + map.get(key));
//	        }
//	    }
	
	
	

}
