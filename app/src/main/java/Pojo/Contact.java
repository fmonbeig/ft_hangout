package Pojo;

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
    private String  phone;
    private String  address;
    private String  otherInformation;
    private String  message;
    private String  picture;

    public Contact() {
    }

    public Contact(int id, String firstName, String name, String phone, String address,
                   String otherInformation, String message, String picture) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.otherInformation = otherInformation;
        this.message = message;
        this.picture = picture;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", otherInformation='" + otherInformation + '\'' +
                ", message='" + message + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    public List<String> getEveryMessage() {
        List<String> everyMessage;
        everyMessage = new ArrayList<String>(Arrays.asList(message.split("\n")));
        Log.d("message", everyMessage.toString());
        Log.d("size of ArrayList", Integer.toString(everyMessage.size()));
        return everyMessage;
    }
    //a chaque debut de message je mettrais un identifiant qui permettra de caractériser le user
}
