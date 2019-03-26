package concurrency.atomic;

public class Demo1 {
    private static final long count = 100001;

    public static void main(String args[]){
        try {
            concurrency();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < count; i++) {
                    a+=5;
                }

            }
        });
        thread.run();

        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency"+time+"ms,b="+b);
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a=0;
        for (int i = 0; i <count ; i++) {
            a+=5;
        }
        int b =0;
        for (int i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("serial"+time+"ms,b="+b);
    }
}
