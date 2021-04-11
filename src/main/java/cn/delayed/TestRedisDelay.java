package cn.delayed;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;

public class Redisson {

    public static void main(String[] args) throws InterruptedException {

        //设置每个格子是 100ms, 总共 256 个格子
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 256);

        //加入三个任务，依次设置超时时间是 10s 5s 20s

        
        System.out.println("加入一个任务，ID = 1, time= " + LocalDateTime.now());
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("执行一个任务，ID = 1, time= " + LocalDateTime.now());
        }, 10, TimeUnit.SECONDS);

        System.out.println("加入一个任务，ID = 2, time= " + LocalDateTime.now());
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("执行一个任务，ID = 2, time= " + LocalDateTime.now());
        }, 5, TimeUnit.SECONDS);

        System.out.println("加入一个任务，ID = 3, time= " + LocalDateTime.now());
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("执行一个任务，ID = 3, time= " + LocalDateTime.now());
        }, 20, TimeUnit.SECONDS);

        System.out.println("等待任务执行===========");
        Thread.sleep(2000l);
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("执行一个任务，ID = 4, time= " + LocalDateTime.now());
        }, 25, TimeUnit.SECONDS);
    }
}