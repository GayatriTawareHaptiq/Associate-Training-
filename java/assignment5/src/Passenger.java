import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Passenger implements Runnable {
    private final int id;
    private final Semaphore semaphore;
    private final CountDownLatch latch;

    public Passenger(int id, Semaphore semaphore, CountDownLatch latch) {
        this.id = id;
        this.semaphore = semaphore;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire(); // Wait for a driver
            String driver = DriverPool.assignDriver();
            System.out.println("Passenger " + id + " is riding with " + driver);
            Thread.sleep((long) (Math.random() * 10000)); // Simulate ride duration
            DriverPool.releaseDriver(driver);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(); // Release driver permit
            latch.countDown();   // Signal ride completion
        }
    }
}
