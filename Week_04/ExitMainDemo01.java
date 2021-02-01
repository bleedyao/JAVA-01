import task.AsyncRunnable;

public class ExitMainDemo01 {
    public static void main(String[] args) throws InterruptedException {
        AsyncRunnable ar = new AsyncRunnable(result ->
                System.out.println(ExitMainDemo01.class.getName() + " result is " + result)
        );
        new Thread(ar).start();
        synchronized (ar) {
            ar.wait();
        }
        System.out.println("Main Thread is exit");
    }
}
