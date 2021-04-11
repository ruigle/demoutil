package cn.base64;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.Timer;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *@author lirui
 *@version 创建时间：2020-4-17 下午2:10:47
 *
 */
public class Test {

	@SuppressWarnings("restriction")
	public static void main(String[] args) throws AWTException, IOException {
		
		InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream("C:/Users/admin/Desktop/pc/banner.jpg");
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(data);
        System.out.println(base64);
        FileOutputStream out = new FileOutputStream("C:/Users/admin/Desktop/pc/1.jpg");
        BASE64Decoder decoder = new BASE64Decoder(); 
        byte[] decodeBuffer = decoder.decodeBuffer(base64);
        out.write(decodeBuffer);
        out.flush();
        out.close();
        
        
			
		
	}
}
