package concurrency.atomic;

import java.util.*;
import java.util.concurrent.*;

abstract class NumRangeGenerator { // non-task, non-Runnable, can be canceled
    private volatile boolean canceled = false;
    public abstract int[] next();
    public void cancel() { canceled = true; }
    public boolean isCanceled() { return canceled; }
}

class NumRangeChecker11 implements Runnable { // task(s) that depend on & share concurrency.atomic.NumRangeGenerator
    private NumRangeGenerator generator;
    private final int id;
    public NumRangeChecker11(NumRangeGenerator g, int ident) {
        generator = g;
        id = ident;
    }
    public void run() {
        System.out.println("Testing..");
        while(!generator.isCanceled()) {
            int[] range = generator.next();
            if( range[0] > range[1]) {
                System.out.println("Error in test #" + id + ": min " + range[0]
                        + " > " + "max " + range[1] );
                generator.cancel();
            }
        }
    }
    public static void test(NumRangeGenerator gen, int count) {
        System.out.println("Press Ctrl-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < count; i++)
            exec.execute(new NumRangeChecker11(gen, i));
        exec.shutdown();
    }
    public static void test(NumRangeGenerator gen) {
        test(gen, 10);
    }
}

//public class NumRangeGenerator11 extends concurrency.atomic.NumRangeGenerator {
//    private int min = 0;
//    private int max = 0;
//    private int[] range = { min, max };
//    private Random rand = new Random();
//    public int[] next() { // oops, method should be synchronized
//        min = rand.nextInt(100);
//        max = rand.nextInt(100);
//        Thread.yield();
//        if(min > max) max = min;
//        int[] ia = { min, max };
//        return ia;
//    }
//    public static void main(String[] args) {
//        concurrency.atomic.NumRangeChecker11.test(new NumRangeGenerator11());
//    }
//}


class SynchronizedNumRangeGenerator11 extends NumRangeGenerator {
    private int min = 0;
    private int max = 0;
 	private int[] range = { min, max };
 	private Random rand = new Random();
 	public synchronized int[] next() { // synchronized!
 		min = rand.nextInt(100);
 		max = rand.nextInt(100);
 		Thread.yield();
 		if(min > max) max = min;
 		int[] ia = { min, max };
 		return ia;
 	}
 	public static void main(String[] args) {
 		NumRangeChecker11.test(new SynchronizedNumRangeGenerator11());
 	}
  }