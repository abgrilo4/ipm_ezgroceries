package com.example.ezgroceries.ui.Produtos;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ezgroceries.AppClass;
import com.example.ezgroceries.R;

import java.util.ArrayList;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    CheckBox favView;
    TextView mTitle, mPreco;
    Button addBtn;

   public MyHolder(@NonNull View itemView) {
       super(itemView);

       this.mImageView = itemView.findViewById(R.id.image);
       this.mTitle = itemView.findViewById(R.id.title);
       this.mPreco = itemView.findViewById(R.id.preco);
       this.favView = itemView.findViewById(R.id.fav);
       this.addBtn = itemView.findViewById(R.id.adic);
   }
}
