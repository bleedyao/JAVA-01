import task.AsyncRunnable02;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExitMainDemo03 {
    public static void main(String[] args) throws InterruptedException {
        int[] mainResult = {Integer.MIN_VALUE};
        Lock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            lock.lock();
            mainResult[0] = result;
            System.out.println(ExitMainDemo03.class.getName() + " result is " + result);
            condition.signal();
            lock.unlock();
        });
        Thread thread = new Thread(ar);
        thread.start();
        lock.lock();
        condition.await();
        System.out.println("main result: " + mainResult[0]);
        System.out.println("Main Thread is exited");
        lock.unlock();
    }
}
