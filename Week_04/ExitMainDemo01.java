import task.AsyncRunnable01;

public class ExitMainDemo01 {
    public static void main(String[] args) throws InterruptedException {
        AsyncRunnable01 ar = new AsyncRunnable01(result ->
                System.out.println(ExitMainDemo01.class.getName() + " result is " + result)
        );
        new Thread(ar).start();
        synchronized (ar) {
            ar.wait();
        }
        System.out.println("Main Thread is exited");
    }
}
