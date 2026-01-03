package MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool implements Runnable {
    private final int taskId;

    public ThreadPool(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is processing task: " + taskId);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " finished task: " + taskId);
    }

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 20; i++) {
            ex.submit(new ThreadPool(i));
        }
        ex.shutdown();
    }
}
