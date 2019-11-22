package com.example.ezgroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class CheckCart extends AppCompatActivity {

    private Button removeItem;
    private Button finishShoping;
    private ListView listView;
    private ArrayList<Object> listaPlaceholder;
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_cart);

        listaPlaceholder = new ArrayList<>();
        listaPlaceholder.add(new String());
        listaPlaceholder.add(new Product("Apple",2 , "1$"));
        listaPlaceholder.add(new Product("Apple",2 , "1$"));
        listaPlaceholder.add(new Product("Apple",2 , "1$"));
        listaPlaceholder.add(new Product("Apple",2 , "1$"));
        listaPlaceholder.add(new Product("Apple",2 , "1$"));
        listaPlaceholder.add(new Product("Apple",2 , "1$"));


        removeItem = (Button)findViewById(R.id.RemoverItem);
        finishShoping = (Button)findViewById(R.id.FinalizarCompra);
        listView = (ListView)findViewById(R.id.ItemsNoCarrinho);
        adapter = new ProductAdapter(this, listaPlaceholder);
        listView.setAdapter(adapter);

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Object> toRemove = new ArrayList<>();

                for (Object o: listaPlaceholder) {
                    if(o instanceof Product){
                        if(((Product) o).isCheckbox())
                            toRemove.add(o);

                    }
                }

                for (Object p: toRemove) {
                    listaPlaceholder.remove(p);

                }

                listView.setAdapter(adapter);
            }
        });

        finishShoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
