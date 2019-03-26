package concurrency.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS线程安全计算
 */
public class Counter {
    AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;
    public static void main(String args[]){
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t:ts) {
            t.start();
        }
        for (Thread t:ts) {
            try {
                t.join();
            }catch (Exception e){

            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.print(System.currentTimeMillis()-start);
    }

    /**
     * 非线程安全计数
     */
    private void count(){
        i++;
    }
    /***
     * 使用CAS实现线程的安全计数
     */
    private void safeCount(){
        for (;;) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc)
                break;
        }
    }

}
