import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message<T> implements Comparable<Message<?>> {
    private final User sender;
    private final T content;
    private final LocalDateTime timestamp;

    public Message(User sender, T content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now(); // Automatically set on creation
    }

    public User getSender() {
        return sender;
    }

    public T getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("[%s] %s: %s",
                timestamp.format(formatter),
                sender.getUsername(),
                content.toString());
    }

    /**
     * This method provides the logic for sorting messages chronologically.
     **/
    @Override
    public int compareTo(Message<?> other) {
        return this.timestamp.compareTo(other.getTimestamp());
    }
}
