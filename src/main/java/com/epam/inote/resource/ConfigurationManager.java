package com.epam.inote.resource;

import java.util.ResourceBundle;

/**
 * Created by User on 25.07.2017.
 *
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    // класс извлекает информацию из файла config.properties
    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
