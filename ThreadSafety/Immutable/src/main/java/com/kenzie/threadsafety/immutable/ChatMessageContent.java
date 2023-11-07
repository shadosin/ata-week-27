package com.kenzie.threadsafety.immutable;

import java.util.Date;

public class ChatMessageContent {
    public ChatUser sender;
    public String message;
    public Date creationDate;

    /**
     *
     * @param sender ChatUser for sender so we can look at username as well as id.
     * @param message The message to send.
     * @param creationDate date the message was created
     */
    public ChatMessageContent(ChatUser sender, String message, Date creationDate) {
        this.sender = sender;
        this.message = message;
        this.creationDate = creationDate;
    }

    public ChatUser getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
