package concurrency.atomic;

import java.util.ArrayList;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result"+id;
    }
}
public class CallableDemo{
    public static void main(String args[]){
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(exec.submit(new TaskWithResult(i)));
        }

        try {
            for (Future<String> future:list) {
                System.out.println(future.get());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
