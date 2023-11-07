package com.kenzie.threadsafety.immutable;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class representing final state of the coding activity.
 */
public class ChatMessageDistributor {

    /**
     *
     * @param chatGroup List of ChatUsers to send the messageContent to.
     * @param messageContent Message content to be sent.
     */
    public void sendChatMessage(List<ChatUser> chatGroup, ChatMessageContent messageContent) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (ChatUser recipient : chatGroup) {
            Runnable messageTask = new ChatMessage(recipient, messageContent);
            executor.execute(messageTask);
        }

        executor.shutdown();
    }
}


