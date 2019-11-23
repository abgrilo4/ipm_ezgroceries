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

public class TodosScreen extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    ArrayList<Produto> todos;

    String[] categorias = {"Todos", "Frescos", "Mercearia", "Bebidas", "Laticínios", "Padaria"};

    Spinner sp;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_t);
        setSupportActionBar(toolbar);

        sp = (Spinner)findViewById(R.id.spinner_todos);
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
                            intent = new Intent(TodosScreen.this, FrescosScreen.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(TodosScreen.this, MerceariaScreen.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(TodosScreen.this, BebidasScreen.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(TodosScreen.this, LaticiniosScreen.class);
                            startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(TodosScreen.this, PadariaScreen.class);
                            startActivity(intent);
                            break;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRecyclerView = findViewById(R.id.rv_todos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);

        EditText search = findViewById(R.id.search_todos);
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

        for(Produto p : todos)
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
        Intent intent = new Intent(TodosScreen.this, MainMenu.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private ArrayList<Produto> getMyList() {
        todos = new ArrayList<>();
        todos.add(new Produto("Fiambre Perna Extra Fatias Nobre", 1.99, 1.99, 1.99, R.drawable.fiambre_perna_extra_fatias));
        todos.add(new Produto("Queijo Flamengo Terra Nostra", 0.99, 0.99, 0.99, R.drawable.queijo_flamengo_fatias_terra_nostra));
        todos.add(new Produto("Fiambre Perna Extra Fatias Nobre", 1.99, 1.99, 1.99, R.drawable.arroz_agulha_extra_longo_cigala));
        todos.add(new Produto("Queijo Flamengo Terra Nostra", 0.99, 0.99, 0.99, R.drawable.arroz_carolino_extra_longo_cigala));
        todos.add(new Produto("Fiambre Perna Extra Fatias Nobre", 1.99, 1.99, 1.99, R.drawable.arroz_carolino_extra_longo_saludaes));
        todos.add(new Produto("Queijo Flamengo Terra Nostra", 0.99, 0.99, 0.99, R.drawable.pao_bicos));
        todos.add(new Produto("Fiambre Perna Extra Fatias Nobre", 1.99, 1.99, 1.99, R.drawable.pao_de_centeio_serra_da_estrela));
        todos.add(new Produto("Queijo Flamengo Terra Nostra", 0.99, 0.99, 0.99, R.drawable.pao_de_agua));

        return todos;
    }


}
