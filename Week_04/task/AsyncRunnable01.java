package task;

public class AsyncRunnable01 implements Runnable {
    private Listener listener = null;

    public AsyncRunnable01(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        if (listener != null) {
            listener.onListener(fibo(30));
            synchronized (this) {
                this.notify();
            }
        }
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
