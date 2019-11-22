package com.example.ezgroceries;

public class Product {
    private String name;
    private String price;
    private int number;
    boolean checkbox;

    public  Product(String name, int number, String price){
        this.name = name;
        this.price = price;
        this.number = number;
        this.checkbox = false;
    }

    public String getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
}
