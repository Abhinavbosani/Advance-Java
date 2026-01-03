package MultiThreading;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample implements Callable<Integer> {
    private int num;
    public ThreadPoolExecutorExample(int num) {
        this.num = num;
    }
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        System.out.println("Task started for: "+Thread.currentThread().getName());
        Thread.sleep(1000);
        for(int i=0;i<num;i++){
            sum+=i;
        }
        Thread.sleep(1000);
        return sum;

    }

    public static void main(String[] args) {
        ThreadPoolExecutor ex = new ThreadPoolExecutor(2, 4, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new ThreadPoolExecutor.CallerRunsPolicy());
        Future<Integer> f1=ex.submit(new ThreadPoolExecutorExample(3));
        Future<Integer> f2=ex.submit(new ThreadPoolExecutorExample(4));
        Future<Integer> f3=ex.submit(new ThreadPoolExecutorExample(2));
        Future<Integer> f4=ex.submit(new ThreadPoolExecutorExample(8));
        Future<Integer> f5=ex.submit(new ThreadPoolExecutorExample(5));
        Future<Integer> f6=ex.submit(new ThreadPoolExecutorExample(12));
        Future<Integer> f7=ex.submit(new ThreadPoolExecutorExample(6));
        Future<Integer> f8=ex.submit(new ThreadPoolExecutorExample(9));



        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
            System.out.println(f4.get());
            System.out.println(f5.get());
            System.out.println(f6.get());
            System.out.println(f7.get());
            System.out.println(f8.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        finally {
            ex.shutdown();
        }
    }
}
