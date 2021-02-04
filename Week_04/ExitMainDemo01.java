import task.AsyncRunnable01;

import java.util.concurrent.atomic.AtomicInteger;

public class ExitMainDemo01 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger mainResult = new AtomicInteger(Integer.MIN_VALUE);
        AsyncRunnable01 ar = new AsyncRunnable01(result -> {
            mainResult.set(result);
            System.out.println(ExitMainDemo01.class.getName() + " result is " + result);
        });
        new Thread(ar).start();
        synchronized (ar) {
            ar.wait();
        }
        System.out.println("main result: " + mainResult);
        System.out.println("Main Thread is exited");
    }
}
