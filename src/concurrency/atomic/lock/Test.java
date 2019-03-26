package concurrency.atomic.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
//public class SpinLock {
//    AtomicReference<Thread> atomicReference = new AtomicReference<>();
//    public void concurrency.atomic.lock(){
//        Thread current = Thread.currentThread();
//        System.out.println("hahh");
////        while(!atomicReference.compareAndSet(null,current)){
////
////        }
//    }
//
//    public void unlock(){
//        Thread current = Thread.currentThread();
////        atomicReference.compareAndSet(current,null);
//
//    }
//        public static void main(String args[]){
//        SpinLock sl = new SpinLock();
//            for (int i = 0; i < 5; i++) {
//                Thread t1 = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        sl.concurrency.atomic.lock();
//                        System.out.println(Thread.currentThread());
//                        sl.unlock();
//                        System.out.println("enen");
//                    }
//                });
//                t1.start();
//                try{
//                    t1.join();
//                }catch (Exception e){
//
//                }
//
//            }
//
//        }
//}

class SpinLock {
    AtomicReference<Thread> owner = new AtomicReference<Thread>();//用原子方式更新的对象引用
    private int count;
    public void lock() {
        Thread cur = Thread.currentThread();
        //lock函数将owner设置为当前线程，并且预测原来的值为空。unlock函数将owner设置为null，并且预测值为当前线程。当有第二个线程调用lock操作时由于owner值不为空，导致循环

        //一直被执行，直至第一个线程调用unlock函数将owner设置为null，第二个线程才能进入临界区。
        while (!owner.compareAndSet(null, cur)){
        }
    }
    public void unLock() {
        Thread cur = Thread.currentThread();
        owner.compareAndSet(cur, null);
    }
}
public class Test implements Runnable {
    static int sum;
    private SpinLock lock;

    public Test(SpinLock lock) {
        this.lock = lock;
    }
    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();
        for (int i = 0; i < 100; i++) {
            Test test = new Test(lock);
            Thread t = new Thread(test);
            t.start();
        }

        Thread.currentThread().sleep(1000);
        System.out.println(sum);
    }

    @Override
    public void run() {
        this.lock.lock();
        sum++;
        this.lock.unLock();
    }
}
