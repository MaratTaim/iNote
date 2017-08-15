package com.epam.inote.resource;

import java.util.ResourceBundle;

/**
 * Created by User on 05.08.2017.
 *
 */
public class ConnectionManager {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("connection");

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
