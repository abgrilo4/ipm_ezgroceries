package com.example.ezgroceries;

import android.app.Application;

import com.example.ezgroceries.ui.Produtos.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppClass  extends Application {
    private String globalUsername = "Guest User";
    private String globalEmail = "No email available";
    private ArrayList<Produto> carrinho = new ArrayList<>();
    private HashMap<Produto, Integer> quantidade = new HashMap<>();

    public ArrayList<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    public boolean allReadyInChart(Produto produto){
        for (Produto p: carrinho
             ) {
            if(produto.nome().equals(p.nome()))
                return true;
        }
        return false;
    }

    public ArrayList<Produto> addProdutoCarrinho(Produto produto){
       /*
        if(allReadyInChart(produto)){
            int i = quantidade.get(produto);
            quantidade.remove(produto);
            quantidade.put(produto, i++);
        }else{
            quantidade.put(produto, 1);

        }
        */

        carrinho.add(produto);
        return  carrinho;
    }

    /*
    public int getQuantity(Produto produto){
        return quantidade.get(produto);
    }

     */

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