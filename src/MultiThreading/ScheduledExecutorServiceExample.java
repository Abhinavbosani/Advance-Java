package MultiThreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
//        for (int i=0; i<10; i++) {
            scheduler.scheduleAtFixedRate(() -> {
                System.out.println("Running every 2 seconds"+"Task: "+ Thread.currentThread().getName());
            }, 0, 2, TimeUnit.SECONDS);
//        }
    }
}
