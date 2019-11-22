package com.example.ezgroceries.ui.Produtos;

import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezgroceries.Product;
import com.example.ezgroceries.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,null);

        Button adicionar = view.findViewById(R.id.adic);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Product");
                Product product = new Product(models.get(i).nome(),2 , models.get(i).melhorPreco());
                reff.push().setValue(product);
            }
        });

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.mTitle.setText(models.get(i).nome());
        myHolder.mPreco.setText(Double.toString(models.get(i).melhorPreco()));
        myHolder.mImageView.setImageResource(models.get(i).idImg());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
