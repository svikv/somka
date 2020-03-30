package qalight;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        Thread thread = new Thread();
//        try {
//            thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println( "Hello World!" );

        SleepRunnable sleepRunnable = new SleepRunnable();
        Thread one = new Thread(sleepRunnable);
        one.setName("Fred");
        Thread two = new Thread(sleepRunnable);
        two.setName("Lucy");
        Thread three = new Thread(sleepRunnable);
        three.setName("Ricky");

        one.start();
        two.start();
        three.start();
    }
}
