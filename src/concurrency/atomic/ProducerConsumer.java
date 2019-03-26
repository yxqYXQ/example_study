package concurrency.atomic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 生产者消费者问题
 * 存在多种情况
 * 1个生产者1个消费者。
 * 1个生产者N个消费者。
 * M个生产者N个消费者。
 */
public class ProducerConsumer {
    //定义一个队列缓冲区，数据为Integer
    private Queue<Integer> queue = new LinkedList<>();
    //设置缓冲区最大容量
    private static final int MAX_SIZE = 100;

    /**
     * <p>生产者进行V原语操作</p>
     * <ul>
     * <li>如果缓冲区没有达到MAX_SIZE，则生产一个产品（n个也行）放入缓冲区，并唤醒所有线程</li>
     * <li>否则使自己进入缓冲区的等待池</li>
     * </ul>
     */
    class Producer implements Runnable{

        @Override
        public void run() {
            while(true){
                synchronized (queue){
                    if(queue.size()<MAX_SIZE){
                        int num = 1;
                        queue.offer(num);
                        queue.notifyAll();
                    }else{
                        try {
                            queue.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    /**
     * <p>消费者进行P原语操作</p>
     * <ul>
     * <li>如果缓冲区有数据，则从缓冲区取出一个产品（n个也行），并唤醒所有线程</li>
     * <li>否则使自己进入缓冲区的等待池</li>
     * </ul>
     *
     */
    class Consumer implements Runnable{

        @Override
        public void run() {
            while(true){
                synchronized (queue){
                    if(queue.size()>0){
                        int num = queue.poll();
                        queue.notifyAll();
                    }else{
                        try {
                            queue.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String args[]){
        ProducerConsumer pc = new ProducerConsumer();

        //Thread构造函数需要一个Runnable对象即可构造一个新的线程，Runnable对象可以重复利用，不必new多个
        //一个消费者，一个生产者
        Consumer c = pc.new Consumer();
        Producer p = pc.new Producer();

        //生产者和消费者谁先start都一样
        new Thread(c).start();
        new Thread(c).start();
        new Thread(c).start();
        new Thread(c).start();
        new Thread(c).start();

        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();

    }
}
