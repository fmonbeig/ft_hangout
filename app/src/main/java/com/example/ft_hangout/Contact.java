package com.example.ft_hangout;

public class Contact {
    private int     id;
    private String  firstName;
    private String  name;
    private int     phone;
    private String  address;
    private String  otherInformation;

    public Contact() {
    }

    public Contact(int id, String firstName, String name, int phone, String address, String otherInformation) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.otherInformation = otherInformation;
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
}
