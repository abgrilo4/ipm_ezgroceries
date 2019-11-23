package com.example.ezgroceries.ui.Produtos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezgroceries.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Produto> models;

    public MyAdapter(Context c, ArrayList<Produto> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.mTitle.setText(models.get(i).nome());
        myHolder.mPreco.setText(models.get(i).melhorPreco());
        myHolder.mImageView.setImageResource(models.get(i).idImg());

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
