package concurrency.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable {
    @Override
    public void run() {
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread()+" "+this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]){
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonHthreadFactory());
        for (int i = 0; i <10; i++) {
            exec.execute(new DaemonFromFactory());

        }
        exec.shutdown();
        System.out.println("All concurrency.atomic.Daemon start");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        ExecutorService exec = Executors.newCachedThreadPool();
//        for (int i = 0; i <10 ; i++) {
//            exec.execute(new concurrency.atomic.DaemonFromFactory());
//        }
//        exec.shutdown();
    }
}
