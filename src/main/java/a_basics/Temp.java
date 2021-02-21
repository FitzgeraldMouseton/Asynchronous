package a_basics;

import java.util.concurrent.CompletableFuture;

public class Temp {
    public static void main(String[] args) {

        /*
        При синхронном вызове главный метод дождется выполнения runnable, при асинхронном - нет.
        Если передать в параметре executor и в конце, как полагается вызвать shutdown(), то все выпонится,
        как ожидается, так как shutdown() отклоняет новые задания, но дожидается выполнения старых
         */

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("jk");
        };
        Thread thread = new Thread(runnable);
        thread.start();

        CompletableFuture.runAsync(runnable);

        // ======================================================



    }
}
