package MultiThreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return "Hello";
        }).thenApplyAsync(s->s+" World");

        CompletableFuture<Integer> completableFuture2=CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            return 10;
        }).thenApply(x->x+1);

        CompletableFuture<Integer> completableFuture3=CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            return 10;
        }).thenApply(x->x*x);

        CompletableFuture<Integer> combined=completableFuture2.thenCombine(completableFuture3,(x, y)->x+y);
        System.out.println("Combined result: "+combined.join());

        CompletableFuture<Integer> a =completableFuture2.completeOnTimeout(100,1,TimeUnit.SECONDS);

        CompletableFuture<Void> allOf=CompletableFuture.allOf(completableFuture1,completableFuture2,completableFuture3);
        allOf.thenRun(()->System.out.println("All Done"));

        CompletableFuture<Object> anyOf=CompletableFuture.anyOf(completableFuture1,completableFuture2,completableFuture3);
        System.out.println("Any one of the task is completed: "+ anyOf.join());

        anyOf.exceptionally(RuntimeException::new);

        completableFuture1.orTimeout(2, TimeUnit.SECONDS);
        System.out.println("A complete on time: "+a.join());

        String s = completableFuture1.join();

        System.out.println(s);
        System.out.println("How are you?");

    }

}
