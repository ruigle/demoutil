package cn.nong.util.jsoup;

import java.io.IOException;


import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 *
 */
public class JsoupUtils {
	
	// 获取页面加载内容
	public static Document loadDocumentData(String url) {
		// 创建文档对象
		Document doc = null;
		// 根据url将某页信息转换成文档
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		// 返回文档
		return doc;
	}

	
	
	

}
