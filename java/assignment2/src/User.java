import java.util.Objects;

public class User implements Comparable<User> {
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return username;
    }

    /**
     * This method provides the logic for sorting users alphabetically by username.

     */
    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.getUsername());
    }

    /**
     * These two methods are essential for HashSet and HashMap to work correctly.

     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
