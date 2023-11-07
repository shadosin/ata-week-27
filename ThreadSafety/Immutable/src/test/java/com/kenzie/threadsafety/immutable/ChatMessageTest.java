package com.kenzie.threadsafety.immutable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChatMessageTest {

    @Test
    public void getClassModifiers_isFinal() {
        //GIVEN
        Class c = ChatMessage.class;
        //WHEN
        boolean isFinal = Modifier.isFinal(c.getModifiers());
        //THEN
        Assertions.assertTrue(isFinal, "Immutable classes should not be extendable.");
    }

    @Test
    public void getFieldModifiers_areFinal() {
        //GIVEN
        Class p  = ChatMessage.class;
        int numFields = 2;
        //WHEN & THEN
        Assertions.assertEquals(numFields, p.getDeclaredFields().length);
        for (Field f : p.getDeclaredFields()) {
            Assertions.assertTrue(Modifier.isFinal(f.getModifiers()),
                    "Some fields were not declared final.");
        }
    }

    @Test
    public void getFieldModifiers_arePrivate() {
        //GIVEN
        Class p  = ChatMessage.class;
        int numFields = 2;
        //WHEN & THEN
        Assertions.assertEquals(numFields, p.getDeclaredFields().length);
        for (Field f : p.getDeclaredFields()) {
            Assertions.assertTrue(Modifier.isPrivate(f.getModifiers()),
                    "Some fields were not declared private.");
        }
    }

    @Test
    public void getMethods_retrieveClassFields_unchangedSinceConstruction() {
        //GIVEN
        Date now = new Date(90000);

        ChatUser user1 = new ChatUser("UserName", "12345");
        ChatUser user2 = new ChatUser("UserName", "54321");
        ChatUser testUser2 = new ChatUser(user2.getUsername(),user2.getUserId());

        ChatMessageContent messageContent = new ChatMessageContent(user1, "This is a test", now);
        ChatMessageContent testMessageContent = new ChatMessageContent(
                messageContent.getSender(),
                messageContent.getMessage(),
                messageContent.getCreationDate());

        ChatMessage cm = new ChatMessage(testUser2, testMessageContent);

        //WHEN
        //we modify the underlying objects (if not immutable themselves)
        Class p  = ChatUser.class;
        for (Field f : p.getDeclaredFields()) {
            int modifiers = f.getModifiers();
            if(!Modifier.isFinal(modifiers) || !Modifier.isPrivate(modifiers)) {
                f.setAccessible(true);
                try {
                    f.set(testUser2, "modified user");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        p = ChatMessageContent.class;
        try {
            Field f = p.getDeclaredField("sender");
            int modifiers = f.getModifiers();

            if(!Modifier.isFinal(modifiers) || !Modifier.isPrivate(modifiers)){
                f.setAccessible(true);
                try {
                    f.set(testMessageContent, new ChatUser("modified user 1","00000"));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }catch(NoSuchFieldException e){
            fail("sender has been modified, please revert the name of the ChatUser back to sender.");
        }

        try {
            Field f = p.getDeclaredField("message");
            int modifiers = f.getModifiers();

            if(!Modifier.isFinal(modifiers) || !Modifier.isPrivate(modifiers)){
                f.setAccessible(true);
                try {
                    f.set(testMessageContent, "modified message");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }catch(NoSuchFieldException e){
            fail("message has been modified, please revert the name of the String back to message.");
        }

        try {
            Field f = p.getDeclaredField("creationDate");
            int modifiers = f.getModifiers();

            if(!Modifier.isFinal(modifiers) || !Modifier.isPrivate(modifiers)){
                f.setAccessible(true);
                try {
                    f.set(testMessageContent, new Date(29));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }catch(NoSuchFieldException e){
            fail("creationDate has been modified, please revert the name of the Date back to creationDate.");
        }

        //THEN
        Assertions.assertAll("Chat Message should be what was set, \n" +
                "all objects passed into an immutable object should be immutable themselves or defensively copied.",
                () -> assertEquals(user2.getUsername(), cm.getRecipient().getUsername(),
                        "Recipient username was modified."),
                () -> assertEquals(user2.getUserId(), cm.getRecipient().getUserId(),
                        "Recipient user id was modified."),
                () -> assertEquals(messageContent.getSender().getUsername(), cm.getMessageContent().getSender().getUsername(),
                        "Message content sender was modified."),
                () -> assertEquals(messageContent.getSender().getUserId(), cm.getMessageContent().getSender().getUserId(),
                        "Message content sender was modified."),
                () -> assertEquals(messageContent.getMessage(), cm.getMessageContent().getMessage(),
                        "Message content message was modified."),
                () -> assertEquals(messageContent.getCreationDate().getTime(), cm.getMessageContent().getCreationDate().getTime(),
                        "Message content date was modified.")
        );
    }

}
