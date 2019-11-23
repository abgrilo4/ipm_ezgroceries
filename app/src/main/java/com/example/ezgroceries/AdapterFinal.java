package com.example.ezgroceries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezgroceries.ui.Produtos.Produto;

import java.util.ArrayList;

public class AdapterFinal extends RecyclerView.Adapter<HolderFinal> {

    Context c;
    ArrayList<Produto> models;
    ArrayList<Integer> qtdd;

    public AdapterFinal(Context c, ArrayList<Produto> models, ArrayList<Integer> qtdd) {
        this.c = c;
        this.models = models;
        this.qtdd = qtdd;
    }

    @NonNull
    @Override
    public HolderFinal onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_final,null);

        return new HolderFinal(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderFinal myHolder, final int i) {

        myHolder.mTitle.setText(models.get(i).nome());
        myHolder.mPreco.setText(models.get(i).melhorPreco());
        myHolder.mImageView.setImageResource(models.get(i).idImg());

        System.out.println(qtdd.get(i) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        myHolder.quantidade.setText("Quantidade: " + qtdd.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void filteredList(ArrayList<Produto> filtered) {
        models = filtered;
        notifyDataSetChanged();
    }
}
