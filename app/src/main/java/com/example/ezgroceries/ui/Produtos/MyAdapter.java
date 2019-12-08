package com.example.ezgroceries.ui.Produtos;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezgroceries.AppClass;
import com.example.ezgroceries.R;

import java.lang.reflect.Array;
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
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        myHolder.mTitle.setText(models.get(i).nome());
        myHolder.mPreco.setText(models.get(i).melhorPreco());
        myHolder.mImageView.setImageResource(models.get(i).idImg());
        myHolder.mStore.setText(models.get(i).melhorLoja());

        ((MyHolder)myHolder).addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText quant = myHolder.quant;
                ((AppClass)((Activity)c).getApplication()).addProdutoCarrinho(models.get(i), Integer.parseInt(quant.getText().toString()));
                Log.i("TAG", quant.getText().toString());
                Toast toast = Toast.makeText(c, "Produto adicionado!", Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.TOP, 0, 125);
                toast.show();
            }
        });



        ((MyHolder)myHolder).favView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setButtonDrawable(ContextCompat.getDrawable(buttonView.getContext(), android.R.drawable.btn_star_big_on));
                    ((AppClass)((Activity)c).getApplication()).addProdutoFavoritos(models.get(i));
                }
                else {
                    buttonView.setButtonDrawable(ContextCompat.getDrawable(buttonView.getContext(), android.R.drawable.btn_star_big_off));
                    ((AppClass)((Activity)c).getApplication()).remProdutoFavoritos(models.get(i));
                }
            }
        });

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
