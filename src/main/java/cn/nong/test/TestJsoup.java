package cn.nong.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.persistence.Temporal;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import cn.nong.util.jsoup.JsoupUtils;



/**
 *
 */
public class TestJsoup {
	private static final String url = "http://nync.tj.gov.cn/ztzl/jghq/";
    
	public static void main(String[] args) {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("d", "ddddd");
        map.put("b", "bbbbb");
        map.put("a", "aaaaa");
        map.put("c", "ccccc");

        //这里将map.entrySet()转换成list
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
        	//升序排序
        	public int compare(Entry<String, String> o1,
                    Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for(Map.Entry<String,String> mapping:list){                
        	System.out.println(mapping.getKey()+":"+mapping.getValue()); 
          } 
    }
    @Test
    public void tesr01(){
    	Document document = JsoupUtils.loadDocumentData("http://nync.tj.gov.cn/ztzl/nybddsc/201806/t20180622_25224.html");
    	Elements elements = document.getElementsByClass("TRS_Editor");
    	Element element = elements.get(0);
    	String html = element.html();
    	System.out.println(html);
    	String newhtml = html.replaceAll(" ", "");
    	System.out.println(newhtml);
    	
    }

}
