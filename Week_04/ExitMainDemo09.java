import sun.awt.windows.ThemeReader;
import sun.misc.Unsafe;
import task.AsyncRunnable02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class ExitMainDemo09 {
    public static void main(String[] args) throws InterruptedException {
        int[] mainResult = {Integer.MIN_VALUE};
        Thread main = Thread.currentThread();
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            mainResult[0] = result;
            LockSupport.unpark(main);
            System.out.println(ExitMainDemo09.class.getName() + " result is " + result);
        });
        Thread thread = new Thread(ar);
        thread.start();
        LockSupport.park();
        System.out.println("main result: " + mainResult[0]);
        System.out.println("Main Thread is exited");
    }
}
