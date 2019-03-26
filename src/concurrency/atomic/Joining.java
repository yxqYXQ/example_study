package concurrency.atomic;

class Sleeper extends Thread{
    private int duration;
    public Sleeper(String name,int sleepTime){
        super(name);
        duration = sleepTime;
        start();
    }
    public void run(){
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName()+"has awakened");
    }
}

class Joiner extends Thread{
    private Sleeper sleeper;
    public Joiner(String name,Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }
    public void run(){
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        System.out.println(getName()+"join completed");
    }
}

public class Joining {
    public static void main(String args[]){
       Sleeper sleeper = new Sleeper("concurrency.atomic.Sleeper",1500);
       Sleeper grump = new Sleeper("Grumpy",1500);
       Joiner joiner = new Joiner("concurrency.atomic.Joiner",sleeper);
       Joiner joiner1 = new Joiner("Joiner1",grump);
//       joiner1.interrupt();



    }
}
