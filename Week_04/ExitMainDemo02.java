import task.AsyncRunnable02;

import java.util.concurrent.atomic.AtomicInteger;

public class ExitMainDemo02 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger mainResult = new AtomicInteger(Integer.MIN_VALUE);
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            mainResult.set(result);
            System.out.println(ExitMainDemo01.class.getName() + " result is " + result);
        });
        Thread thread = new Thread(ar);
        thread.start();
        thread.join();
        System.out.println("main result: "+ mainResult);
        System.out.println("Main Thread is exited");
    }
}
