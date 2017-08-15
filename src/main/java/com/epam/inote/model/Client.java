package com.epam.inote.model;

/**
 * Created by User on 03.08.2017.
 *
 */
public class Client {
    public static final Client EMPTY = new Client();
    private int id;
    private String login = "";
    private String password = "";
    private String email = "";

    public Client(){}

    public Client(String login, String password, String email){
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
