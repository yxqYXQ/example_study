package concurrency.atomic;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                System.out.println("hhahweeert");
            }
        }
    }
      public static void main(String args[]) throws InterruptedException {
        Thread.sleep(100);
          for (int i = 0; i < 10; i++) {
              Thread daemon = new Thread(new SimpleDaemons());
              daemon.setDaemon(true);
              daemon.start();
          }
          System.out.println("All daemon started");
          TimeUnit.MILLISECONDS.sleep(175);

    }
}
