import java.util.*;
import java.util.stream.Collectors;

public class ChatRoom {

    private final Set<User> activeUsers = new HashSet<>();


    private final Map<User, List<Message<?>>> messageHistory = new HashMap<>();

    public void addUser(User user) {
        activeUsers.add(user);
        System.out.println("✅ " + user.getUsername() + " has joined the chat.");
    }

    public <T> void sendMessage(User sender, T content) {
        if (!activeUsers.contains(sender)) {
            System.out.println("⚠️ Error: " + sender.getUsername() + " is not an active user.");
            return;
        }


        Message<T> message = new Message<>(sender, content);

        // This line gets the user's message list, or creates a new one if it's their first message.
        messageHistory.computeIfAbsent(sender, k -> new ArrayList<>()).add(message);

        System.out.println("💬 " + message);
    }


    public List<User> getActiveUsersSorted() {
        return activeUsers.stream()
                .sorted() // This works because User implements Comparable.
                .collect(Collectors.toList());
    }

    /**
     * Requirement: Sort messages by timestamp.

     */
    public List<Message<?>> getMessageHistoryForUserSorted(User user) {
        // Get the list of messages for the user, or an empty list if they have none.
        List<Message<?>> messages = messageHistory.getOrDefault(user, Collections.emptyList());

        return messages.stream()
                .sorted() // This works because Message implements Comparable.
                .collect(Collectors.toList());
    }


    public List<Message<?>> getFullChatHistorySorted() {
        return messageHistory.values() // Gets a collection of all the message lists
                .stream()             // Creates a stream of these lists (e.g., Stream<List<Message>>)
                .flatMap(List::stream) // Flattens the stream of lists into a single stream of messages (Stream<Message>)
                .sorted()             // Sorts the single stream of messages by their timestamp
                .collect(Collectors.toList()); // Collects the sorted messages into a final list
    }
}
