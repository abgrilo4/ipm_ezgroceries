package com.example.ezgroceries;

public class Product {
    private String name;
    private double price;
    private int number;
    boolean checkbox;

    public  Product(String name, int number, double price){
        this.name = name;
        this.price = price;
        this.number = number;
        this.checkbox = false;
    }

    public double getPrice() {
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

    public void setCheckbox() {

        if(checkbox == false)
            checkbox = true;
        else
            checkbox = false;
    }
}
