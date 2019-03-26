package process;

import concurrency.atomic.SimpleDaemons;

import javax.naming.SizeLimitExceededException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class light_process {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String args[]){

    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while(flag){

                    try {
                        System.out.println(Thread.currentThread()+"flag is true.wait "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread()+"flag is false.running "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread()+"flag is true.wait "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notify();
                flag = false;
            }
        }
    }
}
