package com.epam.inote.model;


import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Marat 15.07.2017
 */

public class User {
    public static final User EMPTY = new User();
    private int id;
    private String name = "";
    private String mail = "";
    private String surname = "";
    private String address = "";
    private LocalDate birthday;
    private Map<ContactType,String> contact = new EnumMap<>(ContactType.class);
    private Set<String> group = new TreeSet<>();

    public User(){}

    public User(String surname, String name, String address){
        this.surname = surname;
        this.name = name;
        this.address = address;
    }

    public User(String surname, String name, String address, LocalDate birthday){
        this.surname = surname;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
    }

    public User(int id, String surname, String name, String address, LocalDate birthday) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
    }

    public void removeContact(ContactType type){
        contact.remove(type);
    }

    public String getContact(ContactType type) {
        return contact.get(type);
    }

    public Map<ContactType, String> getContacts(){
        return contact;
    }

    public void setContact(ContactType type, String contact) {
        this.contact.put(type,contact);
    }

    public Set<String> getGroup() {
        return group;
    }

    public String stringGroups(){
        String string = group.toString();
        return string.substring(1,string.length()-1);
    }

    public void setGroup(String group) {
        this.group.add(group);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getMail(){
        for(ContactType type: ContactType.VALUES){
            mail = contact.get(type);
            if(type.name().equals("MAIL")){
                return mail;
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", contact=" + contact +
                ", group=" + group +
                '}';
    }
}
