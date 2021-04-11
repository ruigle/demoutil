package cn.thread.demo1;
/**
 *@author lirui
 *@version 创建时间：2020-4-9 下午5:54:47
 *
 */
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (this.isInterrupted()) {
            	System.out.println("22222**************");
                break;
            }
            System.out.println(Thread.currentThread().getName() + "MyThread-" + i);
            try {
                Thread.sleep(500);
                int a=1/0;
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.interrupt();
            }
 
        }
    }
}