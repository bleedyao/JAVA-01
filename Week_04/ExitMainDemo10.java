import task.AsyncRunnable02;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class ExitMainDemo10 {
    public static void main(String[] args) throws InterruptedException {
        int[] mainResult = {Integer.MIN_VALUE};
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10, true);
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            queue.offer(result);
            System.out.println(ExitMainDemo10.class.getName() + " result is " + result);
        });
        Thread thread = new Thread(ar);
        thread.start();
        mainResult[0] = queue.take();
        System.out.println("main result: " + mainResult[0]);
        System.out.println("Main Thread is exited");
    }
}
