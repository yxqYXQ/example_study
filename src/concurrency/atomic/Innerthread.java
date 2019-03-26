package concurrency.atomic;

public class Innerthread {
    private int countDown = 5;
    private Inner inner;
    private class Inner extends  Thread{
        Inner(String name){
            super(name);
            start();
        }
        public void run(){
            while(true){
                System.out.println(this);
                if(countDown==0){
                    return;
                }
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public String toString() {
            return getName()+":"+countDown;
        }
    }

    public Innerthread(String name) {
        inner = new Inner(name);
    }
}
