
public class Main {
    public static void main(String[] args) {
        // Start the logging service
        OutputManager.start();

        try {
            // 1. Create the data manager
            Gradebook gradebook = new Gradebook();

            // 2. Create the user interface, giving it access to the data manager
            AppUI userInterface = new AppUI(gradebook);

            // 3. Start the application
            userInterface.start();

        } finally {
            // 4. Ensure the log file is always closed properly, even if an error occurs
            OutputManager.log("\nApplication shutting down. Closing resources.");
            OutputManager.close();
        }
    }
}