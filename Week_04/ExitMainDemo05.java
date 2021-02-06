import task.AsyncRunnable02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExitMainDemo05 {
    public static void main(String[] args) throws InterruptedException {
        int[] mainResult = {Integer.MIN_VALUE};
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            mainResult[0] = result;
            System.out.println(ExitMainDemo05.class.getName() + " result is " + result);
            countDownLatch.countDown();
        });
        Thread thread = new Thread(ar);
        thread.start();
        countDownLatch.await();
        System.out.println("main result: " + mainResult[0]);
        System.out.println("Main Thread is exited");
    }
}
