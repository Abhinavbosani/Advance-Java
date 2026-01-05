package MultiThreading;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return "Hello";
        }).thenApplyAsync(s->s+" World");

        String s = completableFuture.join();
        System.out.println(s);
        System.out.println("How are you?");

    }

}
