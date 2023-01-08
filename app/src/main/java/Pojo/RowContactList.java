package Pojo;

import java.io.Serializable;

public class RowContactList implements Serializable {
    private String picture;
    private String name;
    private String phone;
    private int id;

    public RowContactList(String name, String phone, String picture, int id) {
        this.name = name;
        this.phone = phone;
        this.picture = picture;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "RowContactList{" +
                "picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                '}';
    }
}
