package cn.nong.httpurl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.Map.Entry;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 开放银行网关加签验签、加解密、报文发送解析的测试代码
 * 加签验签、加密解密方法适用于API、H5接入方式
 * 报文发送、解析只适用于API接入方式, H5使用前台页面的POST请求。
 * @author renbobo
 */
public class OpenBankGateWay {
	private static final String CHARSET_ENCODING = "UTF-8";
    /**
	  * 加密算法
	  */
	private static final String KEY_ALGORITHM = "AES";

	/**
	 * 加密/解密算法 / 工作模式 / 填充方式
	 */
	private static final String CIPHER_ALGORITHM_PKCS5 = "AES/CBC/PKCS5Padding";
	
	/**
	  * 加签算法
	  */
	private static final String ALGORITHM = "SHA256withRsa"; 
	
    // 第三方证书私钥
    private static PrivateKey privateKey;
    
    // 第三方证书公钥
    private static PublicKey publicKey;
    
    // 农行网关公钥证书
    private static PublicKey abcPublicKey;
    
	//请求报文字段：
    private String appid;
	private String requestUrl;
	private String signType; 
	private boolean isEncrypt;
	private String bizData;
	private String encryptData;
	private String encryptType;
	
	//加密字符串
	private String keyStore;
	
	public static void main(String[] args) throws Exception {
		//初始化签名参数
		OpenBankGateWay openBankGateWay=new OpenBankGateWay();
		openBankGateWay.initRawSignService("C:\\Users\\admin\\Desktop\\H5开户测试\\H5开户测试/24501a5d-e841-4827-b2b3-d14a9ea3fba4.pfx",
				"111111", 
				"C:\\Users\\admin\\Desktop\\H5开户测试\\H5开户测试/example.cer");
		//请求报文字段初始化
		openBankGateWay.appid="24501a5d-e841-4827-b2b3-d14a9ea3fba4";
//		openBankGateWay.requestUrl="http://10.230.165.59:8088/AraratGateWay/openabc/api/eaccount/autheaccount/v1";
//		openBankGateWay.requestUrl="http://10.230.165.59:8088/AraratGateWay/openabc/api/ket/userinfo/v1";
//		openBankGateWay.requestUrl="http://wxpay.test.abchina.com/AraratGateWay/openabc/h5/h5eaccount/EAccOpen/v1";
		openBankGateWay.requestUrl="http://wxpay.test.abchina.com/AraratGateWay/openabc/api/h5eaccount/queryresult/v1";
//		openBankGateWay.requestUrl="http://wxpay.test.abchina.com/AraratGateWay/openabc/api/h5eaccount/queryresultbyid/v1";
//		openBankGateWay.bizData="{\"client_id\":\"24501a5d-e841-4827-b2b3-d14a9ea3fba4\",\"redirect_uri\":\"http://www.tjsyns.com/\",\"acq_trace\":\"ysn25545\"}";
//		openBankGateWay.bizData="{\"AppId\":\"24501a5d-e841-4827-b2b3-d14a9ea3fba4\",\"AcqTrace\":\"ysn25545\"}";
		openBankGateWay.bizData="{\"Code\":\"24501a5d-e841-4827-b2b3-d14a9ea3fba4\"}";
		//支持两种签名方式：SHA256 和 HASHANDSHA256
		openBankGateWay.signType="SHA256";
		openBankGateWay.isEncrypt=false;
		openBankGateWay.keyStore="cff054d1d7c84036a3dc160f9457e5f423704b15";

		System.out.println("业务报文:"+openBankGateWay.bizData);
		
		//根据需要对业务报文进行加密
		if(openBankGateWay.isEncrypt) {
			openBankGateWay.encryptType=KEY_ALGORITHM;
    		String key = openBankGateWay.keyStore.substring(0, 24);
    		String iv  = openBankGateWay.keyStore.substring(24);
    		openBankGateWay.encryptData=openBankGateWay.enAESCrypt(openBankGateWay.bizData, key,iv); 
    		openBankGateWay.bizData="";
    		System.out.println("加密业务报文:"+openBankGateWay.encryptData);
		}else{
			openBankGateWay.encryptData="";
			openBankGateWay.encryptType="";
		}
		
		//报文拼装并补充字段信息
    	JSONObject paraJson = openBankGateWay.dataBind();
    	String plainTxt="";
        if(openBankGateWay.signType.equals("SHA256")) {
        	 plainTxt = openBankGateWay.getSignPlainText(paraJson);
        }else {
        	try {
				plainTxt = openBankGateWay.computeSHA256(openBankGateWay.getSignPlainText(paraJson));
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
        }

        //对拼装明文进行签名
        String sign = openBankGateWay.signData(plainTxt);
        paraJson.put("sign", sign);

		System.out.println("明文:"+plainTxt);
		System.out.println("加签:"+sign);
		System.out.println("验签:"+openBankGateWay.verifyRequestData(plainTxt,sign));
        System.out.println("请求报文:"+paraJson);
        
        //发送Http请求
        String json ="";
		try {
			int timeOut=6000;
			//配置请求参数
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(timeOut)
					.setConnectTimeout(timeOut)
					.setSocketTimeout(timeOut)
					.build();
			//创建可关闭的HttpClient
			CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
	        HttpPost httpPost = new HttpPost(openBankGateWay.requestUrl);
	        httpPost.setHeader("Content-type","application/x-www-form-urlencoded");
//	        httpPost.setHeader("Authorization","Bearer AAEAAB4uqp8NqLqz1Cc8sHI0glPg8L7IHnscXFdZUNXpgY7NtsKqaFh0svOIC8qJsoE3_PHoxJn6C3pv9u9W6AghH2rwyXk0UctnC5jyDNhNdPZPkxTusSMtu_nJ5apm8CMxTTGhBIJZKaBN9CHgzhl_Oni89bihLDYTBd8BWelohHUoeQ0fLKsn6kCeYr7-wk0Mzjfpw_NpEBgf-zSGeiHrTbfJkQCRbw-HZkJvdeqrjYhHGvU0wmHHOkGsWVc0mFeEjPw8tj6cZN_F6r4q-VTEomcth6WaKF6r4hhqbUZcqHk6smGopTIfsr3wE_98jAQefzAg87mzS9nHhNNJYDHfgJ20AQAAAAEAAF6hSdpzs89OxR35vPeWydrlvNR3AiZyJFvhWaR0yzVgAQ5my-OM-AXDwOlkC37BxBK9yLKBNu5MKDXkAudvGJnpqUK4eAOd37Bck_QwlOBGjKTZC2zUnlFbSIdOOVZmr9H6rc_OYdVFL6fQzxpiV48AYcftoP4sGFmsnliICgIlrAvymnxDu-eSpbQu8-fmclmsiRbaNyIApwUcHudM_A01QVvt8-HEJiV24R8p61uyyp1VPyo4equMy5IsCfAAqkyCffzG6n_V0ukeBLjcuTAWmpY9Kn-hLvdmOnzPNwsolINviJmQUnEwEBn7UC06IGnebGhkfISrcnlhqS53yLzkgxfgE4MqZqjhWHxJ6KWZ_TqVgM8033s71XoJV7nyeZ-kmbjE-m-dyE5PXiO_uCvjXyOCiIGHV0YjbBzmzc5zpAvBfobKvhf9lRPeawjuGNHfx0OnhunI4TyV6dteaWoV9MbM6a9niiX7T4_EjOEFO470RyeFvBp6z6wI6knxqwM4Q1DEX-U-d0_ma6oFWGyZDWFqcv6oDXPqKltExCHLg48NcR32zL158ErUIklZhw");
	        httpPost.setEntity(new StringEntity(paraJson.toJSONString(), CHARSET_ENCODING));
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
	        HttpEntity entity = httpResponse.getEntity();
			//转换为Json对象
			json = EntityUtils.toString(entity);
			httpResponse.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("response:"+json);
		
		//解析响应报文
		if(json==null||json.isEmpty()) {
			return;
		}
		ResponseDomain response = openBankGateWay.getResponse(json);
		
		//响应报文的验签
		String responsePlainTxt = openBankGateWay.getResponsePlainText(response);
		String signedTxt = response.getSign();

		System.out.println("响应报文:"+responsePlainTxt);
		System.out.println("响应报文签名:"+signedTxt);
        boolean flag=false;
        if(responsePlainTxt!=null&&!responsePlainTxt.isEmpty()&&signedTxt!=null&&!signedTxt.isEmpty()) {
        	flag=openBankGateWay.verifyResponseData(responsePlainTxt,signedTxt);
			System.out.println("响应报文验签:"+flag);
        }
        
        //解密响应报文
        if(openBankGateWay.isEncrypt) {
    		String key = openBankGateWay.keyStore.substring(0, 24);
    		String iv  = openBankGateWay.keyStore.substring(24);
        	String decryptTxt=openBankGateWay.deAESCrypt(response.getBiz_encrypt(), key,iv);
			System.out.println("解密key:"+key+"||"+iv);
			System.out.println("解密响应报文:"+decryptTxt);
		}
        
	}
	
	/**
	 * 初始化服务
	 * 
	 * @param myCertPath 第三方自身证书路径
	 * @param myCertPwd 第三方自身证书保护密码
	 * @param abcPubCertPath 农行公钥证书路径
	 */
	public void initRawSignService(String myCertPath, String myCertPwd, String abcPubCertPath) 
    {
		// 第三方证书私钥
		String keyAlis="";
		try {
			KeyStore keyStore=KeyStore.getInstance("PKCS12");
			FileInputStream fileInputStream =new FileInputStream(myCertPath);
			char[] nPassword =null;
			if(myCertPwd!=null&&!myCertPwd.isEmpty()) {
				nPassword=myCertPwd.toCharArray();
			}
			keyStore.load(fileInputStream, nPassword);
			fileInputStream.close();
			
			Enumeration<String> enumeration = keyStore.aliases();
			if(enumeration.hasMoreElements()) {
				keyAlis=(String)enumeration.nextElement();
			}
			privateKey=(PrivateKey)keyStore.getKey(keyAlis, nPassword);
			
			// 第三方证书公钥
			X509Certificate certificate =null;
			String keyAlias = "";
			enumeration = keyStore.aliases();
			while (enumeration.hasMoreElements()) {
				keyAlias = enumeration.nextElement();
				if (keyStore.isKeyEntry(keyAlias)) {
					certificate = (X509Certificate) keyStore.getCertificate(keyAlias);
				}
			}
			publicKey=certificate.getPublicKey();
			
			// 农行网关公钥证书
			CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
			String pfxPath=abcPubCertPath;
			FileInputStream bais = new FileInputStream(pfxPath);
			X509Certificate cert = (X509Certificate)certificatefactory.generateCertificate(bais);
			abcPublicKey = cert.getPublicKey();
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 签名方法
	 * @param plainTxt 明文
	 * @author renbobo
	 */
    public String signData(String plainTxt)
    {
        if (plainTxt==null||plainTxt.isEmpty())
        {
            return "";
        }
		try {
			byte[] plainTxtBytes = plainTxt.getBytes(CHARSET_ENCODING);
			
            // 实例化Signature
            Signature signature = Signature.getInstance(ALGORITHM);
            // 初始化Signature
            signature.initSign(privateKey);
            // 更新
            signature.update(plainTxtBytes);
            byte[] result = signature.sign();
			return Base64.encodeBase64String(result);
		} catch (UnsupportedEncodingException | SignatureException | NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
		}
		return "";
    }
	
    /**
	 * 验签方法 
	 * @param plainTxt 明文
	 * @param signedTxt 密文
	 * @author renbobo
	 */
    public boolean verifyRequestData(String plainTxt,String signedTxt)
    {
        if (plainTxt==null||plainTxt.isEmpty())
        {
        	return false;
        }
        if (signedTxt==null||signedTxt.isEmpty())
        {
        	return false;
        }
        
        try {
        	Signature sign = Signature.getInstance(ALGORITHM);
        	sign.initVerify(publicKey);
        	sign.update(plainTxt.getBytes(CHARSET_ENCODING));
        	return sign.verify(Base64.decodeBase64(signedTxt));
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return false;
    }
    
    /**
	 * 验签方法 
	 * @param plainTxt 明文
	 * @param signedTxt 密文
	 * @author renbobo
	 */
    public boolean verifyResponseData(String plainTxt,String signedTxt)
    {
        if (plainTxt==null||plainTxt.isEmpty())
        {
        	return false;
        }
        if (signedTxt==null||signedTxt.isEmpty())
        {
        	return false;
        }
        
        try {
        	Signature sign = Signature.getInstance(ALGORITHM);
        	sign.initVerify(abcPublicKey);
        	sign.update(plainTxt.getBytes(CHARSET_ENCODING));
        	return sign.verify(Base64.decodeBase64(signedTxt));
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return false;
    }
    
    /**
	  * AES加密 - 填充方式：PKCS5Padding 
	  * @param sSrc		 加密后的密文
	  * @param sKey				 密钥
	  * @param ivParameter 		偏移量
	  * @return		 			加密后的密文
	  */
	public String enAESCrypt(String sSrc, String sKey, String ivParameter) {
		String rtnStr = "";
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_PKCS5);
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes(CHARSET_ENCODING));
			// 此处使用BASE64做转码。
			rtnStr = Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return rtnStr;
	}
	
	/**
	  * AES解密 - 填充方式：PKCS5Padding 
	  * @param sSrc		 加密后的密文
	  * @param sKey				 密钥
	  * @param ivParameter 		偏移量
	  * @return		 			源字符串
	  */
	public String deAESCrypt(String sSrc, String sKey, String ivParameter) {
		String rtnStr = "";
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_PKCS5);
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted = Base64.decodeBase64(sSrc);
			// 先用base64解密
			byte[] original = cipher.doFinal(encrypted);
			rtnStr = new String(original, CHARSET_ENCODING);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		return rtnStr;
	}
	
	
	/**
     * dataBind-将报文字段拼装城Json串，并补充字段信息
     * @return
     */
    private JSONObject dataBind()
    {
    	JSONObject req = new JSONObject();
    	Date date=new Date();
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String timestamp = format.format(date);
//    	System.out.println(timestamp);
        String uuidstr=UUID.randomUUID().toString().replace("-", "");
        req.put("appid", this.appid);
        req.put("biz_data", this.bizData);
        req.put("sign_type", this.signType);
        req.put("timestamp", timestamp);
        req.put("encrypt_data", this.encryptData);
        req.put("encrypt_type", this.encryptType);
        req.put("nonce", uuidstr);
        return req;

    }
	
    /**
	 * getSignPlainText 拼接报文明文-用于请求报文
	 * @param msg
	 * @return String
	 */
	public String getSignPlainText(JSONObject msg)
    {
        Map<String,String> map = new HashMap<String,String>();
        StringBuilder signPlainText = new StringBuilder();
        for(Entry<String, Object> pi : msg.entrySet()){
            if (pi!=null && pi.getValue()!=null && !pi.getValue().equals("")  && !pi.getKey().trim().toLowerCase().equals("sign") &&
                !pi.getKey().trim().toLowerCase().equals("code")&&!pi.getKey().trim().toLowerCase().equals("msg"))
            {
            	map.put(pi.getKey(), pi.getValue().toString()); 
            }
        }
        TreeSet<String> treeSet=new TreeSet<String>();
        for (String key : map.keySet()) {
        	treeSet.add(key);
        }
        
        for (String key : treeSet)
        {
            signPlainText.append(map.get(key) + "@");
        }

        if (signPlainText.length() - 1 >= 0)
        {
            signPlainText.deleteCharAt(signPlainText.length() - 1);
        }
        return signPlainText.toString();
    }

	/**
	 * getSignPlainText<T> 拼接报文明文-用于响应报文
	 * @param msg
	 * @return String
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public <T> String getResponsePlainText(T msg)
    {
    	Class c=msg.getClass();
    	Field[] fields =c.getDeclaredFields();
    	
    	Map<String,String> map = new HashMap<String,String>();
        StringBuilder signPlainText = new StringBuilder();
        for(int i =0; i<fields.length; i++) {
        	int mdf=fields[i].getModifiers();
    		//遍历成员变量
        	if(Modifier.isPrivate(mdf)) {
        		Object value=null;
				try {
					fields[i].setAccessible(true);
					value = fields[i].get(msg); 
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
        		if(value!=null&&!value.equals("") && !fields[i].getName().trim().toLowerCase().equals("sign")
        				&& !fields[i].getName().trim().toLowerCase().equals("code")
        				&& !fields[i].getName().trim().toLowerCase().equals("msg")
        				&& !fields[i].getName().trim().toLowerCase().equals("sign_type")
        				&& !fields[i].getName().trim().toLowerCase().equals("encrypt_type")
        				&& !fields[i].getName().trim().toLowerCase().equals("gateWayResponse")) {
        			map.put(fields[i].getName(), value.toString());
        		}
        	}
        	
        }
        TreeSet<String> treeSet=new TreeSet<String>();
        for (String key : map.keySet()) {
        	treeSet.add(key);
        }
        
        for (String key : treeSet)
        {
            signPlainText.append(map.get(key) + "@");
        }

        if (signPlainText.length() - 1 >= 0)
        {
            signPlainText.deleteCharAt(signPlainText.length() - 1);
        }
        return signPlainText.toString();

    }

	/**
	 * 计算SHA256字符串
	 * @param signPlainText
	 * @return String
	 * @throws Exception 
	 */
	public String computeSHA256(String signPlainText) throws Exception {
		// 返回值
		byte byteBuffer[] = null;
		// 是否是有效字符串
		if (signPlainText != null && signPlainText.length() > 0){
			try{
				// SHA 加密开始
				// 创建加密对象 并传入加密类型
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				// 传入要加密的字符串
				messageDigest.update(signPlainText.getBytes());
				// 得到 byte类型结果
	        byteBuffer = messageDigest.digest();
	      }catch (NoSuchAlgorithmException e){
	        e.printStackTrace();
	      }
	  }
        return Base64.encodeBase64String(byteBuffer);
	}
	

	/**
	 * 解析响应报文
	 * @param json
	 * @return
	 */
	public ResponseDomain getResponse(String json) {
		ResponseDomain response = new  ResponseDomain();
		if(json==null||json.isEmpty()) {
			return response;
		}
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructType(ResponseDomain.class);
		try {
			response = mapper.readValue(json, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * 报文响应类-静态内部类
	 * 用于解析响应报文
	 */
	public static class ResponseDomain {

		private String code;
		private String msg;
		private String biz_encrypt;
		private String biz_content;
		private String sign;
		private String responseid;
		private String sign_type;
		private String encrypt_type;

	    
		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}
		/**
		 * @param code the code to set
		 */
		public void setCode(String code) {
			this.code = code;
		}
		/**
		 * @return the msg
		 */
		public String getMsg() {
			return msg;
		}
		/**
		 * @param msg the msg to set
		 */
		public void setMsg(String msg) {
			this.msg = msg;
		}
		/**
		 * @return the sign
		 */
		public String getSign() {
			return sign;
		}
		/**
		 * @param sign the sign to set
		 */
		public void setSign(String sign) {
			this.sign = sign;
		}
		/**
		 * @return the sign_type
		 */
		public String getSign_type() {
			return sign_type;
		}
		/**
		 * @param sign_type the sign_type to set
		 */
		public void setSign_type(String sign_type) {
			this.sign_type = sign_type;
		}
		/**
		 * @return the encrypt_type
		 */
		public String getEncrypt_type() {
			return encrypt_type;
		}
		/**
		 * @param encrypt_type the encrypt_type to set
		 */
		public void setEncrypt_type(String encrypt_type) {
			this.encrypt_type = encrypt_type;
		}
		/**
		 * @return the biz_encrypt
		 */
		public String getBiz_encrypt() {
			return biz_encrypt;
		}
		/**
		 * @param biz_encrypt the biz_encrypt to set
		 */
		public void setBiz_encrypt(String biz_encrypt) {
			this.biz_encrypt = biz_encrypt;
		}
		/**
		 * @return the biz_content
		 */
		public String getBiz_content() {
			return biz_content;
		}
		/**
		 * @param biz_content the biz_content to set
		 */
		public void setBiz_content(String biz_content) {
			this.biz_content = biz_content;
		}
		/**
		 * @return the responseid
		 */
		public String getResponseid() {
			return responseid;
		}
		/**
		 * @param responseid the responseid to set
		 */
		public void setResponseid(String responseid) {
			this.responseid = responseid;
		}
	}
	
	
}