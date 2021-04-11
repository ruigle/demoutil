package cn.thread.demo1;
/**
 * 实现线程的第二种方式：实现Runnable接口
 */
class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}