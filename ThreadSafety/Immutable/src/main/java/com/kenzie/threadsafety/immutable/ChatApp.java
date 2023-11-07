package com.kenzie.threadsafety.immutable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Allows calling methods of ChatMessageDistributor via a main method.
 */
public class ChatApp {

    /**
     * Main method to instantiate and run ChatMessageDistributor.
     * @param args Main method parameter
     */
    public static void main(String[] args) {
        // This user is sending the message
        ChatUser myUser = new ChatUser("Bill76", "89392");

        // To this group of users
        List<ChatUser> chatGroup = new ArrayList<>();
        chatGroup.add(new ChatUser("Juanita387", "38990"));
        chatGroup.add(new ChatUser("Xiang22", "73626"));
        chatGroup.add(new ChatUser("Brooklyn14", "28829"));
        chatGroup.add(new ChatUser("Marlin99", "12987"));
        chatGroup.add(new ChatUser("MrFrank", "32882"));
        chatGroup.add(new ChatUser("Lorenzo73", "89437"));

        // The message to send
        ChatMessageContent messageContent = new ChatMessageContent(myUser, "Welcome to the grouper!", new Date());

        // The message is sent through the chat message distributor.
        ChatMessageDistributor messageDistributor = new ChatMessageDistributor();
        messageDistributor.sendChatMessage(chatGroup, messageContent);

        // There was a misspelling in our message so we're changing it and resending it!
        // This shouldn't replace our original message, it should just send a new one.
        messageContent.message = "Welcome to the group!";

        // Because all the threads are referencing the same `ChatMessageContent` object,
        // any threads that haven't executed yet will send the modified message.
        //messageDistributor.sendChatMessage(chatGroup, messageContent);

    }
}

