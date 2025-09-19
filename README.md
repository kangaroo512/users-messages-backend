# Users & Messages Backend

A **Java-based backend service** designed to handle user creation and message management in a messaging system.

---

## Features

- **User Management**: Create, update, and manage user accounts.
- **Message Handling**: Search and retrieve messages for users.
- **RESTful API**: Provides endpoints for user and message operations.
- **Lightweight**: Minimal dependencies, easy to integrate into your system.

---

## Installation

Clone the repository to your local machine:

```
bash
git clone https://github.com/kangaroo512/users-messages-backend.git

```
## Usage
### Creating a User
```java
// Initialize the service
UserService userService = new UserService();

// Create a new user
User newUser = new User("username", "email@example.com");
userService.createUser(newUser);

```
### Searching of a User
```java
// Search users by a keyword
List<User> users = userService.searchUsers("searchTerm");

// Print the results
users.forEach(user -> System.out.println(user.getUsername()));

```

### Retrieving Messages for a User
```java
// Initialize the message service
MessageService messageService = new MessageService();

// Retrieve messages for a specific user
List<Message> messages = messageService.getMessagesForUser(userId);

// Print messages
messages.forEach(msg -> System.out.println(msg.getBody()));

```
