package concurrency.atomic;

public class LiftOff1 implements Runnable {
//    protected int countDown =10;
//    private static int taskCount = 0;
//    private final int id = taskCount++;
//    public concurrency.atomic.LiftOff() {
//    }
//    public concurrency.atomic.LiftOff(int countDown) {
//        this.countDown = countDown;
//    }
//    public String status(){
//        return "#" +id +"("+(countDown>0?countDown:"CiftOff!")+"),";
//    }
//    @Override
//    public void run() {
//        while (countDown-->0){
//           System.out.println(status());
//           Thread.yield();//让出线程
//        }
//    }

    public LiftOff1() {
        System.out.println("start");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("hhah ");
            Thread.yield();
        }
        System.out.println("close");
        return;
    }
}
