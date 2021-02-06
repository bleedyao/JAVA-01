import task.AsyncRunnable02;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ExitMainDemo06 {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int[] mainResult = {Integer.MIN_VALUE};
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            mainResult[0] = result;
            System.out.println(ExitMainDemo06.class.getName() + " result is " + result);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread thread = new Thread(ar);
        thread.start();
        cyclicBarrier.await();
        System.out.println("main result: " + mainResult[0]);
        System.out.println("Main Thread is exited");
    }
}
