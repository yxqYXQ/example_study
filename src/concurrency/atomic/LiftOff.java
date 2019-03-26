package concurrency.atomic;

public class LiftOff implements Runnable {
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
//
//        while (countDown-->0){
//            System.out.print(Thread.currentThread().getName());
//           System.out.println(status());
//           Thread.yield();
////            double d = Math.random();
////            int i = (int)(d*10);
////            try {
////
////                Thread.sleep(i);//让出线程
////                System.out.println(" i sleep:"+i);
////            }catch (Exception e){
////
////            }
//
//        }
//    }
//
    public LiftOff() {
        System.out.println("start");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("hhah ");
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"close");
        return;
    }
}
