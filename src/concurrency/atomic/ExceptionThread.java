package concurrency.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable {
    public ExceptionThread() {
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String args[]){
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
            exec.shutdown();
        }catch (Exception e){

        }
    }
}
