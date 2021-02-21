/**
 * Представим, что у нас есть задача - отправить запрос на получение юзера из БД по id - дождаться результата - отправить юзеру сообщение.
 * Запрос к БД - дело не так чтоб очень быстрое, поэтому нам бы не хотелось, чтобы выполнение всей прогаммы блокировалось на это время.
 * Можно попробовать запустить это задание в отдельном потоке. Но что не так с многопоточностью, в некотором смысле?
 *
 * Как работает concurrency. Мы выпоняем различные задания в разных потоках. Задание может быть создано в одном потоке, но выполниться другим потоком,
 * тогда результат выполнения нужно будет вернуть в первый поток. Для переноса результата используется объект Future.
 *
 *     Callable<String> callable = () -> "Sending json to database";
 *     ExecutorService executorService = Executors.newSingleThreadExecutor();
 *     Future<String> future = executorService.submit(callable);
 *
 * Как нам сообщить главному потоку, что executor закончил и будущее готово? Нужно использовать метод get(). В этом случае главный поток просто
 * будет ждать выпонения задания executor'ом. То есть future.get() -  это т.н. blocking call, он останавливает тред, вызывающий его. Т.о. многопоточность -
 * это возможность разбросать задания по разным потокам, тем самым ускорив выполнение программы, но она не позволяет работать не блокируя поток, создавший
 * задание (главный, например).
 *
 * Еще раз.
 * Synchronous - поток, запустивший исполнение задания должен дождаться его выполнения, чтобы продолжить работу.
 * Asynchronous - поток продолжает работу, пока ждет резльтата
 *
 * Message-driven и event-driven так же являются блокирующими и не подходят для того, чтобы оповещать о выполнении задания в неблокируемом режиме.
 *
 *
 * CompletableFuture - это класс, импл-й два интерфейса - Future и CompletionStage. В каком-то смысле это просто более продвинутая
 * версия Future.
 * Для получения CompletableFuture используются 2 паттерна - с использованием Runnable и c использованием Supplier (вместо Callable).
 * В обоих случаях вторым параметром можно передать executor, в котором будет выполняться задание (по умолчанию используеся создаваемый
 * для таких случаев common fork/join pool) (см Basics.class)
 *
 * Задание, переданное для получения CompletableFuture может находиться в трех состояниях
 * - Running
 * - Completed normally
 * - Completed exceptionally
 *
 *
 */
package a_basics;