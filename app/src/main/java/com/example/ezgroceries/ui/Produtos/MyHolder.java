package com.example.ezgroceries.ui.Produtos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ezgroceries.R;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mTitle, mPreco;

   public MyHolder(@NonNull View itemView) {
       super(itemView);

       this.mImageView = itemView.findViewById(R.id.image);
       this.mTitle = itemView.findViewById(R.id.title);
       this.mPreco = itemView.findViewById(R.id.preco);
   }
}
