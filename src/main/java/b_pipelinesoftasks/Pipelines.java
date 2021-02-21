package b_pipelinesoftasks;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class Pipelines {
    public static void main(String[] args) {


//        CompletableFuture<Void> list_was_converted_to_hashSet = CompletableFuture.supplyAsync(() -> List.of(1, 2, 3))
//                .thenApply((Function<List<Integer>, HashSet<Integer>>) HashSet::new)        // function
//                .thenRun(() -> System.out.println("List was converted to HashSet"))  // runnable
//                .thenAccept(Object::toString);
//        CompletableFuture<HashSet<Integer>> hashSetCompletableFuture =
//                (CompletableFuture<HashSet<Integer>>) list_was_converted_to_hashSet;



        /*
        При использовании цепочек вызовов нужно быть осторожным, если после runnable вызвать consumer, то в него в качестве
        параметра будет передан null, т.к. runnable нечего не возвращает. Это нужно учитывать. Не сказать, что такой
        подход в принцпе имеет смысл
         */
        /*
        При создании pipelines CompletionFuture API берет на себя процесс передачи результатов выполнения первого шага в
        последующий по мере его готовности
         */
        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> System.out.println("J!"))
                .thenAccept(System.out::println);

    }
}
