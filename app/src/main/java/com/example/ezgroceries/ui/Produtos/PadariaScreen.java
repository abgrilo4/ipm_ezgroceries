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
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezgroceries.CheckCart;
import com.example.ezgroceries.MainMenu;
import com.example.ezgroceries.R;

import java.util.ArrayList;

public class PadariaScreen extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    ArrayList<Produto> padaria;

    String[] categorias = {"Padaria", "Todos", "Frescos", "Mercearia", "Bebidas", "Laticínios"};

    Spinner sp;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padaria_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_p);
        setSupportActionBar(toolbar);

        sp = (Spinner)findViewById(R.id.spinner_padaria);
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
                            intent = new Intent(PadariaScreen.this, TodosScreen.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(PadariaScreen.this, FrescosScreen.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(PadariaScreen.this, MerceariaScreen.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(PadariaScreen.this, BebidasScreen.class);
                            startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(PadariaScreen.this, LaticiniosScreen.class);
                            startActivity(intent);
                            break;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRecyclerView = findViewById(R.id.rv_padaria);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);

        EditText search = findViewById(R.id.search_padaria);
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

        for(Produto p : padaria)
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
        Intent intent = new Intent(PadariaScreen.this, MainMenu.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private ArrayList<Produto> getMyList() {
        padaria = new ArrayList<>();
        padaria.add(new Produto("Pão de bicos", 0.19, 0.20, 0.21, R.drawable.pao_bicos));
        padaria.add(new Produto("Pão de água", 1.99, 2.29, 2.09, R.drawable.pao_de_agua));
        padaria.add(new Produto("Pão Serra da Estreal", 1.39, 1.35, 1.29, R.drawable.pao_de_centeio_serra_da_estrela));
        padaria.add(new Produto("Pão de centeio", 1.95, 1.99, 2.01, R.drawable.pao_de_centeio));
        padaria.add(new Produto("Pão de mistura", 0.59, 0.61, 0.58, R.drawable.pao_mistura));


        return padaria;
    }


}
