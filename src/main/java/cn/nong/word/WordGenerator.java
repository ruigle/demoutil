package cn.nong.word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import sun.misc.BASE64Encoder;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 *@author lirui
 *@version 创建时间：2019-8-27 下午9:32:22
 *
 */
public class WordGenerator {
	@Test
	public void wordGenerator(){
		try {
			 Map<String, Object> data = new HashMap<String, Object>();
	            data.put("name", "段然涛");
	            data.put("serial", "001");
	            data.put("sex", "男");
	            data.put("phone", "17737138812");
	            data.put("age", "17");
	            data.put("job", "开发");
	            data.put("cjfwzNum", "dhkajshdkashd");
	            data.put("company", "农信通");
	            data.put("id_front",getImageBase("D:/sq/tu/font.png"));
	            data.put("id_back",getImageBase("D:/sq/tu/back.png"));
            
	            Map<String, Object> data1 = new HashMap<String, Object>();
	            data1.put("name", "段然涛");
	            data1.put("serial", "001");
	            data1.put("sex", "男");
	            data1.put("phone", "17737138812");
	            data1.put("age", "17");
	            data1.put("job", "开发");
	            data1.put("cjfwzNum", "dhkajshdkashd");
	            data1.put("company", "农信通");
	            data1.put("id_front",getImageBase("D:/sq/tu/font.png"));
	            data1.put("id_back",getImageBase("D:/sq/tu/back.png"));
	            List<Map<String, Object>> candidates = new ArrayList<Map<String, Object>>();    
	            candidates.add(data);
	            candidates.add(data1);
	            
	            Map<String, Object> data2 = new HashMap<String, Object>();  
	            data2.put("candidates", candidates);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            //指定模板路径的第二种方式,我的路径是D:/      还有其他方式
            configuration.setDirectoryForTemplateLoading(new File("D:/sq/"));

            // 输出文档路径及名称
            File outFile = new File("D:/sq/test2.doc");
            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate("baomingF.ftl","utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(data2, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		
	}
	
	//获得图片的base64码
    public static String getImageBase(String src) throws Exception {
        if (src == null || src == "") {
            return "";
        }
        File file = new File(src);
        if (!file.exists()) {
            return "";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
	

}
