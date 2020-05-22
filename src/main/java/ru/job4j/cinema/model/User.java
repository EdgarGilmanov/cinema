package ru.job4j.cinema.model;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String phone;
    private int hall;
    private int place;

    public User(int id, String name, String phone, int hall, int place) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.hall = hall;
        this.place = place;
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public User(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getHall() {
        return hall;
    }

    public int getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                hall == user.hall &&
                place == user.place &&
                Objects.equals(name, user.name) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, hall, place);
    }
}
