package com.example.ezgroceries;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderFinal extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mTitle, mPreco, quantidade;

    public HolderFinal(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.img);
        this.mTitle = itemView.findViewById(R.id.tit);
        this.mPreco = itemView.findViewById(R.id.prec);
        this.quantidade = itemView.findViewById(R.id.quantidade);
    }
}
