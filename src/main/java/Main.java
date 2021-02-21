import java.util.*;
import java.util.stream.Collectors;

/**
 * Модуль посвящен изучению CompletableFuture API.
 *
 */

public class Main {

    public static void main(String[] args) {

        Map<String, String> map = Map.of("2", "b",
                                        "1", "a");

        // LinkedHashMap сохраняет порядок добавления в map
        LinkedHashMap<String, String> collect = map.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        collect.forEach((k, v) -> System.out.println(k + ": " + v));

    }
}
