package com.example.ezgroceries.ui.Produtos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ezgroceries.R;

import java.util.ArrayList;

public class BebidasScreen extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    String[] categorias = {"Bebidas", "Todos", "Frescos", "Mercearias", "Laticínios", "Padaria"};

    Spinner sp;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas_screen);

        sp = (Spinner)findViewById(R.id.spinnerbebidas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==current)
                    return;
                else {
                    Intent intent = null;
                    switch (position) {
                        case 1:
                            intent = new Intent(BebidasScreen.this, TodosScreen.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(BebidasScreen.this, FrescosScreen.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(BebidasScreen.this, MerceariaScreen.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(BebidasScreen.this, LaticiniosScreen.class);
                            startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(BebidasScreen.this, PadariaScreen.class);
                            startActivity(intent);
                            break;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRecyclerView = findViewById(R.id.rv_bebidas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);
    }

    private ArrayList<Produto> getMyList() {
        ArrayList<Produto> bebidas = new ArrayList<>();

        return bebidas;
    }
}
