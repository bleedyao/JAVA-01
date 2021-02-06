import task.AsyncRunnable02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

public class ExitMainDemo08 {
    public static void main(String[] args) throws InterruptedException {
//        int[] mainResult = {Integer.MIN_VALUE};
        int mainResult = 0;
        Exchanger<Integer> exchanger = new Exchanger<>();
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
//            mainResult[0] = result;
            System.out.println(ExitMainDemo08.class.getName() + " result is " + result);
            try {
                exchanger.exchange(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread = new Thread(ar);
        thread.start();
        mainResult = exchanger.exchange(mainResult);
        System.out.println("main result: " + mainResult);
        System.out.println("Main Thread is exited");
    }
}
