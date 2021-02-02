import task.AsyncRunnable02;

public class ExitMainDemo02 {
    public static void main(String[] args) throws InterruptedException {
        AsyncRunnable02 ar = new AsyncRunnable02(result ->
                System.out.println(ExitMainDemo01.class.getName() + " result is " + result)
        );
        Thread thread = new Thread(ar);
        thread.start();
        thread.join();
        System.out.println("Main Thread is exited");
    }
}
