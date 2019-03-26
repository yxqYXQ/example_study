package concurrency.atomic;

public class UnresponsedUI {

    private volatile double d = 1;

    public UnresponsedUI() throws  Exception{

    }

}

class ResponsedUI extends Thread{

    private static volatile double d = 1;

    public ResponsedUI() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while(d > 0)
            d = d + ( Math.PI + Math.E)/d;
    }

    public static void main(String args[]) throws Exception {
          new UnresponsedUI();
//        new concurrency.atomic.ResponsedUI();
//        System.in.read();
        System.out.print(d);
    }
}
