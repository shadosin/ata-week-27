package com.kenzie.threadsafety.immutable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChatMessageContentTest {

    @Test
    public void getClassModifiers_isFinal() {
        //GIVEN
        Class c = ChatMessageContent.class;
        //WHEN
        boolean isFinal = Modifier.isFinal(c.getModifiers());
        //THEN
        Assertions.assertTrue(isFinal, "Immutable classes should not be extendable.");
    }

    @Test
    public void getFieldModifiers_areFinal() {
        //GIVEN
        Class p  = ChatMessageContent.class;
        int numFields = 3;
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
        Class p  = ChatMessageContent.class;
        int numFields = 3;
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
        Date now = new Date(22);
        Date testDate = new Date();
        testDate.setTime(now.getTime());

        ChatUser user = new ChatUser("UserName", "12345");
        ChatUser testUser = new ChatUser(user.getUsername(), user.getUserId());

        String testMessage = "This is a Test";

        ChatMessageContent messageContent = new ChatMessageContent(testUser, testMessage, testDate);

        //WHEN
        //change the underlying fields
        testDate.setTime(99);

        //String is immutable so trying to change the message won't work.

        Class p  = ChatUser.class;
        for (Field f : p.getDeclaredFields()) {
            int modifiers = f.getModifiers();
            if(!Modifier.isFinal(modifiers) || !Modifier.isPrivate(modifiers)) {
                f.setAccessible(true);
                try {
                    f.set(testUser, "modified user");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //THEN
        Assertions.assertAll("ChatMessageContent values should be what were set: \n" +
                "any objects being passed in should be immutable themselves or defensively copied.",
                () -> assertEquals(user.getUsername(), messageContent.getSender().getUsername(), "User username was modified."),
                () -> assertEquals(user.getUserId(),messageContent.getSender().getUserId(), "User user id was modified."),
                () -> assertEquals(testMessage, messageContent.getMessage(), "The message was " +
                    "incorrect."),
                () -> assertEquals(now.getTime(), messageContent.getCreationDate().getTime(), "The creation date " +
                    "was modified.")
        );
    }

}
