package a_basics;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class Basics {
    public static void main(String[] args) {

        Callable<String> callable = () -> "Sending json to database";
        Runnable runnable = () -> System.out.println("F-off!");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(callable);

        // 1 Метод с Runnable
        CompletableFuture<Void> runnableCompletableFuture = CompletableFuture.runAsync(runnable, executorService);

        // 2 Метод с Supplier
        Supplier<String> supplier = () -> "F-Off";
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(supplier);

        /*
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(callable);

        и

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(runnable, executorService);

        Фактически взаимозаменяемые паттерны
         */

        // =========== Методы CompletableFuture

        completableFuture.join(); // == future.get();
        completableFuture.getNow("A string to return if task was not completed");

        // Проверяет, закончено ли задание. Если да - не делает ничего, если нет - возвращает переданное
        // в качестве параметра значение и завершает задание
        completableFuture.complete("A string to return if task was not completed");

        // То же самое, но устанавливает переданное в виде параметра значение в любом случае
        completableFuture.obtrudeValue("A string to return in any case");

        // Если задание не закончено - бросает исключение
        completableFuture.completeExceptionally(new Throwable());

        // Бросает исключение в любом случае
        completableFuture.obtrudeException(new Throwable());
    }
}
