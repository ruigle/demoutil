package cn.delayed.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

//延迟消息生产者
public class RedisPutInQueue {
    public static void main(String args[]) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RBlockingQueue<Employer> blockingFairQueue = redissonClient.getBlockingQueue("delay_queue");

        RDelayedQueue<Employer> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
        for (int i = 0; i < 10; i++) {
            try {
                //模拟间隔投递消息
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //一分钟以后将消息发送到指定队列
            //延迟队列包含callCdr 1分钟，然后将其传输到blockingFairQueue中
            //在1分钟后就可以在blockingFairQueue 中获取callCdr了
            Employer callCdr = new Employer();
//            callCdr.setSalary(345.6d);
            callCdr.setPutTime();
            delayedQueue.offer(callCdr, 1, TimeUnit.MINUTES);
            System.out.println("callCdr ==================> " + callCdr);
        }
    }
}
