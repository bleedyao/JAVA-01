package task;

public class AsyncRunnable02 implements Runnable{
    private Listener listener = null;
    public AsyncRunnable02(Listener listener) {
        this.listener = listener;
    }
    @Override
    public void run() {
        if (listener != null) {
            listener.onListener(fibo(30));
        }
    }
    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
