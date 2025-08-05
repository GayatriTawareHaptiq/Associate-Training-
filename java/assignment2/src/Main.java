import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatRoom techTalks = new ChatRoom();
        List<User> userList = new ArrayList<>(); // Keep a list for easy access by index

        System.out.println("--- Welcome to the Chat Room Simulation ---");

        // Pre-populate with some users for convenience
        User alice = new User("Alice");
        User bob = new User("Bob");
        techTalks.addUser(alice);
        techTalks.addUser(bob);
        userList.add(alice);
        userList.add(bob);


        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add New User");
            System.out.println("2. Send a Message");
            System.out.println("3. View Sorted User List");
            System.out.println("4. View a User's Message History");
            System.out.println("5. View Full Chat History (How messages are seen by all)"); // <-- NEW OPTION
            System.out.println("6. Exit"); // <-- UPDATED OPTION
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter username for the new user: ");
                    String newUsername = scanner.nextLine();
                    if (newUsername != null && !newUsername.trim().isEmpty()) {
                        User newUser = new User(newUsername);
                        techTalks.addUser(newUser); // Add to the chat room's Set
                        userList.add(newUser);      // Add to our local list for menu selection
                        System.out.println("User '" + newUsername + "' added successfully.");
                    } else {
                        System.out.println("Username cannot be empty.");
                    }
                    break;

                case "2":
                    if (userList.isEmpty()) {
                        System.out.println("No users in the chat to send a message.");
                        break;
                    }
                    System.out.println("Select a user to send a message:");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println((i + 1) + ". " + userList.get(i).getUsername());
                    }
                    System.out.print("Enter user number: ");
                    try {
                        int userIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        User sender = userList.get(userIndex);

                        System.out.print("Enter message content: ");
                        String messageContent = scanner.nextLine();
                        techTalks.sendMessage(sender, messageContent);

                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Invalid user selection.");
                    }
                    break;

                case "3":
                    System.out.println("\n--- Current Users (Sorted Alphabetically) ---");
                    List<User> sortedUsers = techTalks.getActiveUsersSorted();
                    System.out.println("Active users: " + sortedUsers);
                    break;

                case "4":
                    if (userList.isEmpty()) {
                        System.out.println("No users in the chat.");
                        break;
                    }
                    System.out.println("Select a user to view their history:");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println((i + 1) + ". " + userList.get(i).getUsername());
                    }
                    System.out.print("Enter user number: ");
                    try {
                        int userIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        User selectedUser = userList.get(userIndex);

                        System.out.println("\n--- " + selectedUser.getUsername() + "'s Message History (Sorted by Time) ---");
                        List<Message<?>> history = techTalks.getMessageHistoryForUserSorted(selectedUser);
                        if(history.isEmpty()){
                            System.out.println(selectedUser.getUsername() + " has not sent any messages.");
                        } else {
                            history.forEach(System.out::println);
                        }

                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Invalid user selection.");
                    }
                    break;

                case "5": // <-- NEW CASE
                    System.out.println("\n--- Full Chat History (Sorted by Time) ---");
                    List<Message<?>> fullHistory = techTalks.getFullChatHistorySorted();
                    if (fullHistory.isEmpty()) {
                        System.out.println("No messages have been sent yet.");
                    } else {
                        fullHistory.forEach(System.out::println);
                    }
                    break;

                case "6": // <-- UPDATED CASE
                    System.out.println("Exiting simulation. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
