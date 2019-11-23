package com.example.ezgroceries;

import android.app.Application;

import com.example.ezgroceries.ui.Produtos.Produto;

import java.util.ArrayList;

public class AppClass  extends Application {
    private String globalUsername = "Guest User";
    private String globalEmail = "No email available";
    private ArrayList<Produto> carrinho = new ArrayList<>();
    private ArrayList<Produto> favoritos = new ArrayList<>();

    public ArrayList<Produto> getCarrinho() {
        return carrinho;
    }

    public ArrayList<Produto> getFavoritos() {
        return favoritos;
    }

    public void setCarrinho(ArrayList<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    public ArrayList<Produto> addProdutoFavoritos(Produto produto){
        favoritos.add(produto);
        return  favoritos;
    }

    public ArrayList<Produto> addProdutoCarrinho(Produto produto){
        carrinho.add(produto);
        return  carrinho;
    }

    public ArrayList<Produto> removeProdutoCarrinho(Produto produto){
        carrinho.remove(produto);
        return  carrinho;
    }

    public String getGlobalVariable(String varName) {

        switch(varName) {
            case "user":
                return globalUsername;
            case "email":
                return globalEmail;
            default:
                return null;
        }
    }

    public void setGlobalVariable(String varName, String varValue) {

        switch(varName) {
            case "user":
                globalUsername = varValue;
            case "email":
                globalEmail = varValue;
            default:
                break;
        }
    }
}