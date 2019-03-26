package concurrency.atomic;

import java.util.concurrent.TimeUnit;

class Daemon implements Runnable {
    private Thread[] t = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i <t.length ; i++) {
            t[i]= new Thread(new  DaemonSpawn());
            t[i].start();
            System.out.print("concurrency.atomic.DaemonSpawn"+i+"Started");
        }
        for (int i = 0; i < t.length; i++) {
            System.out.println("t["+i+"].isDaemon()="+t[i].isDaemon());
        }
        while (true){
            Thread.yield();
        }
    }
}

class DaemonSpawn implements Runnable{

    @Override
    public void run() {
       while(true){
           Thread.yield();
       }
    }
}
public class Daemons{
    public static void main(String args[]){
        Thread d = new Thread(new Daemon());
                  d.setDaemon(true);
        d.start();
        System.out.println("d.isDameon()"+d.isDaemon());
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.print("hahh");
        }
    }
}
