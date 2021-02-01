package task;

public class AsyncRunnable implements Runnable {
    private Listener listener = null;

    public AsyncRunnable(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        if (listener != null) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listener.onListener(1);
            synchronized (this) {
                this.notify();
            }
        }
    }
}
