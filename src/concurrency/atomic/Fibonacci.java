package concurrency.atomic;

public class Fibonacci implements  Runnable{
    private int n= 0;
    public Fibonacci(int n) {
        this.n = n;
    }

    public int fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }

    @Override
    public void run() {
        for(int i = 0; i < n; i++)
            System.out.println(fibonacci(i)+ " ");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Fibonacci(10));
        thread.start();
    }
}
