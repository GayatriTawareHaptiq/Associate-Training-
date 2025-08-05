import java.io.FileWriter;
import java.io.IOException;

public class OutputManager {

    private static FileWriter fileWriter;
    private static final String FILENAME = "grade_tracker_log.txt";

    /**
     * Initializes the FileWriter to start logging.
     */
    public static void start() {
        try {
            fileWriter = new FileWriter(FILENAME);
        } catch (IOException e) {
            System.out.println("[Critical Error] Could not open log file: " + FILENAME);
            e.printStackTrace();
        }
    }

    /**
     * Logs a message to both the console and the file.
     * @param message The message to log.
     */
    public static void log(String message) {
        // Print to console for real-time feedback
        System.out.println(message);

        // Write to the log file
        try {
            if (fileWriter != null) {
                fileWriter.write(message + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("[Critical Error] Could not write to log file.");
            e.printStackTrace();
        }
    }

    /**
     * Closes the FileWriter to ensure all data is saved.
     */
    public static void close() {
        try {
            if (fileWriter != null) {
                fileWriter.flush(); // Ensure buffer is written
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("[Critical Error] Could not close log file properly.");
            e.printStackTrace();
        }
    }
}