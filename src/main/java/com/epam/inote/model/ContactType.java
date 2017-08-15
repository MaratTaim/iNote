package com.epam.inote.model;


public enum ContactType{
    MAIL("Почта",1),
    PHONE("Тел.",2),
    HOME_PHONE("Домашний тел.",3),
    SKYPE("Skype",4),
    ICQ("ICQ",5),
    MOBILE("Мобильный",6);

    private String title;
    private int id;

    public int getId(){
        return id;
    }

    ContactType(String title,int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public static ContactType[] VALUES = ContactType.values();

}
