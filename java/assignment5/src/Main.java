import java.util.concurrent.*;



public class Main {
    private static  final int numDrivers=10;
    private static final  int numPassengers=20;

    public static void main(String[] args) throws InterruptedException {

        Semaphore driverSemaphore=new Semaphore(numDrivers);
        CountDownLatch latch=new CountDownLatch(numPassengers);
        ExecutorService executor=Executors.newFixedThreadPool(numDrivers);

        DriverPool.initializeDrivers();
        for(int i=0;i<=numPassengers;i++){
            executor.execute(new Passenger(i,driverSemaphore,latch));

        }

        latch.await(); // Wait for all passengers to finish
        executor.shutdown();
        System.out.println(" All passengers have completed their rides.");

    }
}