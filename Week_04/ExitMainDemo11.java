import task.AsyncRunnable02;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class ExitMainDemo11 {
    public static void main(String[] args) throws InterruptedException {
        int[] mainResult = {Integer.MIN_VALUE};
        Semaphore semaphore = new Semaphore(0);
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            mainResult[0] = result;
            System.out.println(ExitMainDemo11.class.getName() + " result is " + result);
            semaphore.release();
        });
        Thread thread = new Thread(ar);
        thread.start();
        semaphore.acquire();
        System.out.println("main result: " + mainResult[0]);
        System.out.println("Main Thread is exited");
    }
}
