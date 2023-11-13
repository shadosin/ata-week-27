package com.kenzie.threadsafety.immutable;

import java.util.Date;

public final class ChatMessageContent {
    private final ChatUser sender;
    private final String message;
    private final Date creationDate;

    /**
     *
     * @param sender ChatUser for sender so we can look at username as well as id.
     * @param message The message to send.
     * @param creationDate date the message was created
     */
    public ChatMessageContent(ChatUser sender, String message, Date creationDate) {
        this.sender = new ChatUser(sender.getUsername(), sender.getUserId());
        this.message = message;
        this.creationDate = new Date(creationDate.getTime());
    }

    public ChatUser getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreationDate() {
        return new Date(creationDate.getTime());
    }


}
