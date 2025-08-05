import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DriverPool {
    private static final Queue<String> drivers = new ConcurrentLinkedQueue<>();

    public static void initializeDrivers() {
        for (char c = 'A'; c <= 'J'; c++) {
            drivers.add("Driver " + c);
        }
    }

    public static synchronized String assignDriver() {
        return drivers.poll(); // Get a driver
    }

    public static synchronized void releaseDriver(String driver) {
        drivers.add(driver); // Return driver to pool
    }
}
