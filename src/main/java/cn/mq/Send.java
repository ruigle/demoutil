package cn.mq;

import java.io.IOException;
import java.io.Serializable;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *@author lirui
 *@version 创建时间：2020-4-21 下午4:15:22
 *
 */
public class Send implements Serializable{
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private final static String QUEUE_NAME = "qq";  

    public static void main(String[] args) throws IOException {  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("127.0.0.1");
        factory.setUsername("yns");
        factory.setPassword("123456");
        factory.setPort(5672);
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        String message = "Hello World!";  
        Send send = new Send();
        send.setName(message);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  
        System.out.println(" [x] Sent '" + message + "'");  

        channel.close();  
        connection.close();  
    }  
}
