package b_pipelinesoftasks;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Задание
 * 1) Получить список id юзеров (Supplier)
 * 2) Получить для каждого id юзера из БД (mapping - Function)
 * 3) собрать юзеров в лист
 */

public class AsyncExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Метод, имитирующий получение id всех юзеров из базы
        Supplier<List<Long>> supplyIDs = () -> {
            sleep(200);
            return Arrays.asList(1L, 2L, 3L);
        };

        // Метод, имитирующий получение юзеров по id
        Function<List<Long>, List<User>> fetchUsers = ids -> {
            sleep(300);
            return ids.stream().map(User::new).collect(Collectors.toList());
        };

        // Метод, выводящий юзеров на экран
        Consumer<List<User>> displayer = users -> {
            System.out.println("Running in - " +  Thread.currentThread().getName());
            users.forEach(System.out::println);
        };


        CompletableFuture<List<Long>> cf = CompletableFuture.supplyAsync(supplyIDs);
        cf.thenApply(fetchUsers).thenAccept(displayer);
        sleep(1000);

        /*
        Если вызвать тот же метод со словом Async на конце, то можно передать executor, в котором выполнится это задание
         */
        CompletableFuture<List<Long>> cf1 = CompletableFuture.supplyAsync(supplyIDs);
        cf1.thenApply(fetchUsers).thenAcceptAsync(displayer, executorService);
        sleep(1000);
        executorService.shutdown();

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        List<String> list = new ArrayList<>(10);
        list.sort(Comparator.naturalOrder());
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
