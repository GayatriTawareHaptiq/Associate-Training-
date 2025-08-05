Real-Time Chat Room Simulation
A Java-based simulation of a real-time chat room designed to demonstrate advanced data structures and object-oriented principles. This project focuses on the core logic of managing users and message history efficiently using the Java Collections Framework, Generics, and custom sorting.

📝 Overview
This application simulates the backend logic of a chat room. It provides an interactive console menu to add new users, send messages, and retrieve sorted lists of active users and message history. The primary goal is to showcase the effective use of Set for uniqueness, Map for relational data, Generics for type safety and flexibility, and the Comparable interface for custom sorting logic.

Note: This is a simulation of the core logic and does not include networking (sockets). All interactions happen in a single console session.

✨ Features
Interactive User Management: Add new users to the chat room via a console menu.

Unique User Roster: Stores active users in a Set to automatically prevent duplicate usernames.

Organized Message History: Maintains a Map to link each user directly to a list of all messages they have sent.

Flexible Message Types: Uses a generic Message<T> class, allowing messages to contain different types of content (e.g., String for text, or custom objects for images/files).

Sorted User Lists: Provides a menu option to retrieve a list of all active users, sorted alphabetically.

Chronological History: Offers options to view a specific user's message history or the entire chat log, sorted by timestamp.

🧠 Core Concepts & Design Choices
This project is built on several fundamental computer science concepts.

Set<User> for Active Users
Why this concept? A Set is a collection that guarantees uniqueness. In a chat room, a user should only be able to join once. Using a Set is the most efficient and logical way to enforce this rule without writing extra code to check for duplicates.

How it's used: We use a HashSet in the ChatRoom class to store User objects. When addUser() is called, the Set ensures that if the user is already present, they are not added again.

Map<User, List<Message>> for History
Why this concept? A Map is a key-value data structure perfect for creating relationships between data. We need to associate a specific User (the key) with a List of all their Message objects (the value).

How it's used: This structure provides an incredibly fast way to look up a user's entire message history. Instead of searching through one massive list of all messages, we can instantly retrieve the correct list with messageHistory.get(user).

Generic Message<T> Class
Why this concept? Generics (<T>) allow us to write flexible, reusable, and type-safe code. Instead of creating separate TextMessage and ImageMessage classes, we create one Message<T> template.

How it's used: We can create a text message with Message<String> or an image message with Message<ImageObject>. This allows the Message class to handle any type of content while still enforcing type safety.

Comparable Interface for Sorting
Why this concept? Java needs instructions to sort custom objects. By implementing the Comparable interface in our User and Message classes, we provide these instructions.

How it's used:

In the User class, the compareTo method compares usernames alphabetically.

In the Message class, the compareTo method compares timestamps chronologically.

This allows us to simply call .sorted() on a stream of these objects, and Java will automatically use our logic to sort them correctly.

📜 Class Pseudocode
Here is the high-level logic for each class in the application.

Main.java
CLASS Main
METHOD main():
// This is the starting point of the entire application.
CREATE a Scanner for user input.
CREATE a ChatRoom object.
PRE-POPULATE the chat room with a few initial users.

    LOOP forever:
        DISPLAY the main menu with options (Add User, Send Message, etc.).
        GET the user's choice.
        USE a switch statement to handle the choice:
          - CASE "Add User":
              PROMPT for a new username.
              CREATE a new User and ADD them to the ChatRoom.
          - CASE "Send Message":
              DISPLAY a list of users.
              PROMPT the user to select a sender.
              PROMPT for the message content.
              CALL sendMessage() on the ChatRoom object.
          - CASE "View Sorted Users":
              CALL getActiveUsersSorted() on the ChatRoom.
              PRINT the resulting list.
          - CASE "View User History":
              PROMPT the user to select a user.
              CALL getMessageHistoryForUserSorted() on the ChatRoom.
              PRINT the resulting list.
          - CASE "View Full History":
              CALL getFullChatHistorySorted() on the ChatRoom.
              PRINT the resulting list.
          - CASE "Exit":
              PRINT a goodbye message.
              EXIT the program.

ChatRoom.java
CLASS ChatRoom
PROPERTIES: - activeUsers (a Set of Users) - messageHistory (a Map of User to a List of Messages)

METHOD addUser(user):
ADD the user to the activeUsers Set.

METHOD sendMessage(sender, content):
CREATE a new Message object with the sender and content.
FIND the sender's message list in the messageHistory Map.
IF no list exists for that sender, CREATE a new empty list first.
ADD the new message to the sender's list.

METHOD getActiveUsersSorted():
CONVERT the activeUsers Set into a List.
SORT the List (Java will use the User's compareTo method).
RETURN the sorted List.

METHOD getMessageHistoryForUserSorted(user):
GET the message list for the specified user from the messageHistory Map.
IF a list exists, SORT it (Java will use the Message's compareTo method).
RETURN the sorted List.

METHOD getFullChatHistorySorted():
GET all the message lists from the messageHistory map.
FLATTEN them into a single list of all messages.
SORT the single list by timestamp.
RETURN the sorted list.

User.java
CLASS User that can be compared to another User
PROPERTIES: - username (String)

METHOD compareTo(otherUser): - Compare my username to the otherUser's username alphabetically.

// These methods are essential for the User to work correctly as a key in a Set or Map.
METHOD equals(otherObject): - Check if the otherObject is a User with the exact same username.
METHOD hashCode(): - Generate a unique number (hash code) based on the username.

Message<T>.java
// A generic class that works for any type <T>
CLASS Message<T> that can be compared to another Message
PROPERTIES: - sender (User) - content (of generic Type T) - timestamp (LocalDateTime)

CONSTRUCTOR(sender, content): - Set the sender and content. - Set the timestamp to the current time.

METHOD compareTo(otherMessage): - Compare my timestamp to the otherMessage's timestamp chronologically.
