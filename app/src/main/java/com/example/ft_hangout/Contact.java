package com.example.ft_hangout;

import android.util.Log;

import org.intellij.lang.annotations.JdkConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contact implements Serializable {
    private int     id;
    private String  firstName;
    private String  name;
    private int     phone;
    private String  address;
    private String  otherInformation;
    private String  message;

    public Contact() {
    }

    public Contact(int id, String firstName, String name, int phone, String address,
                   String otherInformation, String message) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.otherInformation = otherInformation;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", otherInformation='" + otherInformation + '\'' +
                '}';
    }

    public List<String> getEveryMessage() {
        List<String> everyMessage;
        everyMessage = new ArrayList<String>(Arrays.asList(message.split(" \n ")));
        Log.d("message", everyMessage.toString());
        return everyMessage;
    }
    //a chaque debut de message je mettrais un identifiant qui permettra de caractériser le user
}
