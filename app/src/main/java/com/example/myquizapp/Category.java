package com.example.myquizapp;

public class Category {
    public static final int PROGRAMMING=1;
    public static final int BIOLOGY=2;
    public static final int SPORTS=3;
    public static final int MUSIC=4;
    public static final int MOVIES=5;

    private int id;
    private String name;

    public Category(){   }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
