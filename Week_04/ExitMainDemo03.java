import task.AsyncRunnable02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExitMainDemo03 {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        AsyncRunnable02 ar = new AsyncRunnable02(result -> {
            lock.lock();
            System.out.println(ExitMainDemo01.class.getName() + " result is " + result);
            condition.signal();
            lock.unlock();
        });
        Thread thread = new Thread(ar);
        thread.start();
        lock.lock();
        condition.await();
        System.out.println("Main Thread is exited");
        lock.unlock();
    }
}
