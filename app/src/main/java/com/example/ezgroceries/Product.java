package com.example.ezgroceries;

public class Product {
    private String name;
    private Float price;
    private int number;
    boolean checkbox;

    public  Product(String name, int number, Float price){
        this.name = name;
        this.price = price;
        this.number = number;
        this.checkbox = false;
    }

    public Float getPrice() {
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
