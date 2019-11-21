package com.example.ezgroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class CheckCart extends AppCompatActivity {

    private Button removeItem;
    private Button finishShoping;
    private ListView listView;
    private ArrayList<String> listaPlaceholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_cart);

        listaPlaceholder = new ArrayList<>();
        listaPlaceholder.add("Bolo de cenoura");
        listaPlaceholder.add("Açorda");
        listaPlaceholder.add("Caril de frango");
        listaPlaceholder.add("Canja");

        removeItem = (Button)findViewById(R.id.RemoverItem);
        finishShoping = (Button)findViewById(R.id.FinalizarCompra);
        listView = (ListView)findViewById(R.id.ItemsNoCarrinho);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                listaPlaceholder);
        listView.setAdapter(adapter);

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecker = listView.getCheckedItemPositions();
                int count = listView.getCount();
                for (int item = count-1; item>=0; item--){
                    if(positionChecker.get(item)){
                        adapter.remove(listaPlaceholder.get(item));
                    }
                }
            }
        });

        finishShoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
