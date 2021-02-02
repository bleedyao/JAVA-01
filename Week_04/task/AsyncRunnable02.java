package task;

public class AsyncRunnable02 implements Runnable{
    private Listener listener = null;
    public AsyncRunnable02(Listener listener) {
        this.listener = listener;
    }
    @Override
    public void run() {
        if (listener != null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listener.onListener(1);
        }
    }
}
