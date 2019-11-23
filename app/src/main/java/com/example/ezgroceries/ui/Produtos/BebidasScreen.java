package com.example.ezgroceries.ui.Produtos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezgroceries.CheckCart;
import com.example.ezgroceries.MainMenu;
import com.example.ezgroceries.R;

import java.util.ArrayList;

public class BebidasScreen extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    ArrayList<Produto> bebidas;

    String[] categorias = {"Bebidas", "Todos", "Frescos", "Mercearia", "Laticínios", "Padaria"};

    Spinner sp;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_b);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        sp = (Spinner)findViewById(R.id.spinner_bebidas);
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

        EditText search = findViewById(R.id.search_bebidas);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<Produto> filtered = new ArrayList<>();

        for(Produto p : bebidas)
            if(p.nome().toLowerCase().contains(text.toLowerCase()))
                filtered.add(p);
        myAdapter.filteredList(filtered);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.CheckCart:
                Intent intent = new Intent(this, CheckCart.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BebidasScreen.this, MainMenu.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private ArrayList<Produto> getMyList() {
        bebidas = new ArrayList<>();
        bebidas.add(new Produto("Água Sem Gás 6L Fastio", 1.79, 2.19, 1.79, R.drawable.agua_6_litros_fastio));
        bebidas.add(new Produto("Água sem Gás 4x1.5L Fastio", 2.28, 2.28, 2.35, R.drawable.agua_15l_fastio));
        bebidas.add(new Produto("Água sem Gás 5.4L LUSO", 1.69, 1.30, 1.35, R.drawable.agua_5l_luso));
        bebidas.add(new Produto("Água sem Gás 6x1.5L LUSO", 2.56, 2.54, 2.55, R.drawable.agua_15l_luso));
        bebidas.add(new Produto("Néctar Pêssego 1L Compal", 0.99, 0.99, 0.99, R.drawable.compal_pessego));
        bebidas.add(new Produto("Refrigerante Laranja 1.25L Sumol", 1.27, 1.28, 1.29, R.drawable.sumol_laranja));
        bebidas.add(new Produto("Refrigerante 1L Coca Cola", 1.74, 1.35, 1.59, R.drawable.coca_cola));


        return bebidas;
    }


}
