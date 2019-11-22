package com.example.ezgroceries.ui.Produtos;

public class Produto {

    String nome;
    double preco1;
    double preco2;
    double preco3;
    int idImg;

    public Produto(String nome, double preco1, double preco2, double preco3, int idImg) {
        this.nome = nome;
        this.preco1 = preco1;
        this.preco2 = preco2;
        this.preco3 = preco3;
        this.idImg = idImg;
    }

    public String nome() {
        return nome;
    }

    public String melhorPreco() {
        return String.valueOf(Math.min(preco1,Math.min(preco2,preco3)) + "€");
    }

    public double preco1() {
        return preco1;
    }

    public double preco2() {
        return preco2;
    }

    public double preco3() {
        return preco3;
    }

    public int idImg() {
        return idImg;
    }
}
