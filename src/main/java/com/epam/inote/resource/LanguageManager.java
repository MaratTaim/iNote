package com.epam.inote.resource;

import com.epam.inote.constant.Const;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by User on 04.08.2017.
 */
public class LanguageManager extends Const {
    private ResourceBundle bundle;

    public LanguageManager() {
        bundle = ResourceBundle.getBundle(LANG, new Locale(RU_L, RU));
    }

    public void changeRU() {
        bundle = ResourceBundle.getBundle(LANG, new Locale(RU_L, RU));
    }

    public void changeUS() {
        bundle = ResourceBundle.getBundle(LANG, new Locale(EN,US));
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
