package concurrency.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {
//    public static void main(String args[]){
//        concurrency.atomic.LiftOff off = new concurrency.atomic.LiftOff();
//        off.run();
//        for (int i = 0; i < 5; i++) {
//            Thread thread = new Thread(new concurrency.atomic.LiftOff());
//            thread.start();
//            System.out.println("wait");
//        }
//
//    }
public static void main(String args[]){
//        concurrency.atomic.LiftOff off = new concurrency.atomic.LiftOff();
//        off.run();
   //所有线程都是main线性创建的，所以所有的线程的构造函数也是main线程执行的
//    Thread thread = new Thread(new concurrency.atomic.LiftOff());
//    thread.start();
//    Thread thread1 = new Thread(new concurrency.atomic.LiftOff());
//    thread1.start();
//    Thread thread2 = new Thread(new concurrency.atomic.LiftOff());
//    thread2.start();
//    ExecutorService exec = Executors.newCachedThreadPool();//线程的数量是不固定的，根据现有资源确定
//    exec.execute(new concurrency.atomic.LiftOff());
      ExecutorService exec = Executors.newFixedThreadPool(4);//线程池，即只有5个线程
//    ExecutorService exec = Executors.newSingleThreadExecutor();//只有一个线程，一个任务执行完以后，才会执行另一个任务
    //相当于5个任务
    for (int i = 0; i <5 ; i++) {
        exec.execute(new LiftOff());
    }
    exec.shutdown();
  }
}
