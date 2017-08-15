package com.epam.inote.resource;

import java.util.ResourceBundle;

/**
 * Created by User on 25.07.2017.
 *
 */
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    // класс извлекает информацию из файла messages.properties
    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
