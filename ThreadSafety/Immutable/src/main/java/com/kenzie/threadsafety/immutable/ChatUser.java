package com.kenzie.threadsafety.immutable;

public class ChatUser {
    public String username;
    public String userId;

    /**
     * Constructor for Chat user. Class used to tag messages in the chat system.
     * @param username the user's username.
     * @param userId the user's userId
     */
    public ChatUser(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }
}
