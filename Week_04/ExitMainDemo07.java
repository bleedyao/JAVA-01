import task.AsyncRunnable02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

public class ExitMainDemo07 {
    public static void main(String[] args) throws InterruptedException {
        int[] mainResult = {Integer.MIN_VALUE};
        Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                switch (phase) {
                    case 1:
                        System.out.println("Assign the Result");
                        return false;
                    default:
                        return true;
                }
            }
        };
        phaser.register();
        phaser.register();
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            mainResult[0] = result;
            System.out.println(ExitMainDemo07.class.getName() + " result is " + result);
            phaser.arriveAndAwaitAdvance();
        });
        Thread thread = new Thread(ar);
        thread.start();
        phaser.arriveAndAwaitAdvance();
        System.out.println("main result: " + mainResult[0]);
        System.out.println("Main Thread is exited");
    }
}
