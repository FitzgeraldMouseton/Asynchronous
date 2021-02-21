package a_basics;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class AsyncDemo {

    public static void main(String[] args) {

        Supplier<String> task = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task is running in thread - " + Thread.currentThread().getName();
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(task);

        stringCompletableFuture.obtrudeValue("Too");

        String result = stringCompletableFuture.join();
        System.out.println(result);

        stringCompletableFuture.complete("Too long");

        result = stringCompletableFuture.join();
        System.out.println(result);

        executor.shutdown();
    }
}
