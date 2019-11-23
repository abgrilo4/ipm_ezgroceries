package com.example.ezgroceries;

import com.example.ezgroceries.ui.Produtos.Produto;

public class Product {
    private String name;
    private Float price;
    private int number;
    boolean checkbox;
    Produto produto;

    public  Product(String name, int number, Float price, Produto produto){
        this.name = name;
        this.price = price;
        this.number = number;
        this.checkbox = false;
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
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
