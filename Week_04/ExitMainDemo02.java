import task.AsyncRunnable02;

import java.util.concurrent.atomic.AtomicInteger;

public class ExitMainDemo02 {
    public static void main(String[] args) throws InterruptedException {
        int[] mainResult = {Integer.MIN_VALUE};
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            mainResult[0] = result;
            System.out.println(ExitMainDemo02.class.getName() + " result is " + result);
        });
        Thread thread = new Thread(ar);
        thread.start();
        thread.join();
        System.out.println("main result: "+ mainResult[0]);
        System.out.println("Main Thread is exited");
    }
}
