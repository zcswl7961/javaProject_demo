package com.common.jdk.threadlocal;

/**
 * Created by zhoucg on 2019-02-15.
 *
 * ThreadLocal 实例
 */
public class ThreadLocalPoolExample {


    public static class MyRunnable implements Runnable {





        private ThreadLocal<Integer> threadLocal =
               new ThreadLocal<>();
        private ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        @Override
        public void run() {
            threadLocal.set( (int) (Math.random() * 100D) );
            // 这个时候触发一次GC 操作
            System.gc();
            stringThreadLocal.set("zhoucg");
            threadLocal.set( 102);
            stringThreadLocal.set("wl");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
        }
    }

    public  static void main(String[] args) throws Exception {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        //thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        //thread2.join(); //wait for thread 2 to terminate

    }

}
