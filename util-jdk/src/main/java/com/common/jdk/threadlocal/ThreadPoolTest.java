package com.common.jdk.threadlocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 *
 * @author zhoucg
 * @date 2018-12-05
 * 线程池中使用ThreadLocal 导致内存泄漏的问题
 */
public class ThreadPoolTest {

    static class LocalVariable{
        private Long[] a = new Long[1024 * 1024];
    }
    /**
     *  (1) 线程池的创建
     */
    static ThreadFactory testThreadFactory = new ThreadFactoryBuilder().setNameFormat("test-pool-%d").build();
    static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,5,
            1L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(1024),testThreadFactory,new ThreadPoolExecutor.DiscardPolicy());

    /**
     * (2) 创建一个ThreadLocal,无初始化值得
     */
    final static ThreadLocal<LocalVariable> LOCAL_THREADLOCAL = new ThreadLocal<>();

    public static void main(String[] args) throws Exception{
        /**
         * 如果我想这么设计，同一个线程池得结果具有传递性，
         */
        for(int i=0;i<50 ;i++) {
            poolExecutor.execute(() ->{
                LOCAL_THREADLOCAL.set(new LocalVariable());
                System.out.println(Thread.currentThread().getName()+":"+"use local variable");
                LOCAL_THREADLOCAL.remove();

                //去掉对应的线程引用
                LocalVariable localVariable = LOCAL_THREADLOCAL.get();

                System.out.println("localVariable="+localVariable);
            });
        }

        // (6)
        System.out.println("pool execute over");

    }


    /**
     * 初始化一个ThreadLocal，有初始化值得
     */
    // private ThreadLocal myThreadLocalPool = ThreadLocal.withInitial(() -> 1);

}
