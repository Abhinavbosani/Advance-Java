package MultiThreading;

import java.util.concurrent.*;

public class MyCallable implements Callable<Integer> {
    private final int n;
    private int sum = 0;

    public MyCallable(int n) {
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 1; i <= n; i++) {
            sum += i;
            Thread.sleep(500);
        }
        return sum;
    }

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(2);

        Callable<Integer> c1 = new MyCallable(5);
        Callable<Integer> c2 = new MyCallable(10);
        Callable<Integer> c3 = new MyCallable(15);
        Callable<Integer> c4 = new MyCallable(20);
        Callable<Integer> c5 = new MyCallable(25);
        Callable<Integer> c6 = new MyCallable(30);
        try {
            Future<Integer> f1 = ex.submit(c1);
            Future<Integer> f2 = ex.submit(c2);
            Future<Integer> f3 = ex.submit(c3);
            Future<Integer> f4 = ex.submit(c4);
            Future<Integer> f5 = ex.submit(c5);
            Future<Integer> f6 = ex.submit(c6);

            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());

            System.out.println(f6.get()); // blocking
//            System.out.println(f3.get()+","+f4.get()+","+f5.get());
            System.out.println(f4.get());
            System.out.println(f5.get());
        } catch (InterruptedException | ExecutionException ie) {
            System.out.println(ie.getMessage());
        } finally {
            ex.shutdown();
        }

    }
}
