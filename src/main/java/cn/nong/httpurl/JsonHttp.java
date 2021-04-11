package cn.nong.httpurl;

import java.net.URLEncoder;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

/**
 *@author lirui
 *@version 创建时间：2019-12-5 上午11:00:23
 *
 */
public class JsonHttp {
	
	private static final String APPLICATION_JSON = "application/json";
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";	

    public static void httpPostWithJSON(String url, String json) throws Exception {
        // 将JSON进行UTF-8编码,以便传输中文
        String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
        
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        
        StringEntity se = new StringEntity(json,"utf-8");
        se.setContentType(APPLICATION_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(se);
        httpClient.execute(httpPost);
    }
    
    
    public static void main(String[] args) throws Exception {
    	// TODO Auto-generated method stub
    	
    	String json="{\"cid\":\"TEST_23937390\",\"create_date\":1541553169888,\"customer\":\"赵一九\",\"customer_status\":1,\"fn\":\"林\",\"has_photos\":false,\"id\":\"5be23c1908b\",\"position\":{\"latitude\":26.058703,\"longitude\":119.242465,\"address\":\"闽侯县西三环路\",\"accuracy\":228},\"purpose\":\"展业\",\"uid\":\"TEST_starnetlzx\",\"update_date\":1541553201601,\"way\":\"当面拜访\",\"subject\":\"11\",\"remark\":\"linzx,12222\",\"comment\":{\"openid\":\"TEST_oEO\",\"stars\":5,\"content\":\"22\",\"date\":1541553201580},\"photo_urls\":[\"https://pub.helen.yzlcdn.dmbcdn.com/pub10_b056f1b3-5b88-451c-bda7-d93837f94dcf?imageView2/0/h/500/interlace/1\",\"https://pub.helen.yzlcdn.dmbcdn.com/pub10_62711125-b8b0-438e-bfe6-931ad74c298b?imageView2/0/h/500/interlace/1\"]}";
    	httpPostWithJSON("http://localhost:8080/chinaLifePAC!listenChinaLifePac.action", json);
		
	}
    
    
}
