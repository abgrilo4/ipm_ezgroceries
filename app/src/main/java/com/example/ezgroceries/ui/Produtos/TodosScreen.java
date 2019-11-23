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
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

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
        todos.add(new Produto("Água Sem Gás 6L Fastio", 1.79, 2.19, 1.79, R.drawable.agua_6_litros_fastio));
        todos.add(new Produto("Água sem Gás 4x1.5L Fastio", 2.28, 2.28, 2.35, R.drawable.agua_15l_fastio));
        todos.add(new Produto("Água sem Gás 5.4L LUSO", 1.69, 1.30, 1.35, R.drawable.agua_5l_luso));
        todos.add(new Produto("Água sem Gás 6x1.5L LUSO", 2.56, 2.54, 2.55, R.drawable.agua_15l_luso));
        todos.add(new Produto("Arroz Agulha 1Kg Cigala", 1.19, 1.16, 1.15, R.drawable.arroz_agulha_extra_longo_cigala));
        todos.add(new Produto("Arroz Carolino 1Kg Cigala", 1.15, 1.15, 0.89, R.drawable.arroz_carolino_extra_longo_cigala));
        todos.add(new Produto("Arroz Agulha 1Kg Saludães", 1.12, 1.09, 1.12, R.drawable.arroz_carolino_extra_longo_saludaes));
        todos.add(new Produto("Arroz Carolino 1Kg Saludães", 1.15, 1.15, 1.14, R.drawable.arroz_carolino_extra_longo_saludaes));
        todos.add(new Produto("Fiambre Perna Extra Fatias Nobre", 2.49, 2.49, 2.47, R.drawable.fiambre_perna_extra_fatias));
        todos.add(new Produto("Leite Meio Gordo 1L Gresso", 0.56, 0.55, 0.55, R.drawable.gresso_mg));
        todos.add(new Produto("Leite Magro 1L Gresso", 0.56, 0.55, 0.55, R.drawable.gresso_m));
        todos.add(new Produto("Leite Meio Gordo 1L Mimosa", 0.69, 0.68, 0.69, R.drawable.mimosa_mg));
        todos.add(new Produto("Leite Magro 1L Mimosa", 0.69, 0.69, 0.69, R.drawable.mimosa_m));
        todos.add(new Produto("Néctar Pêssego 1L Compal", 0.99, 0.99, 0.99, R.drawable.compal_pessego));
        todos.add(new Produto("Refrigerante Laranja 1.25L Sumol", 1.27, 1.28, 1.29, R.drawable.sumol_laranja));
        todos.add(new Produto("Refrigerante 1L Coca Cola", 1.74, 1.35, 1.59, R.drawable.coca_cola));
        todos.add(new Produto("Massa Esparguete 500g Milaneza", 0.56, 0.69, 0.55, R.drawable.massa_esparguete_milaneza));
        todos.add(new Produto("Massa Cotovelinhos 500g Milaneza", 0.95, 0.95, 0.95, R.drawable.massa_cotovelinhos_milaneza));
        todos.add(new Produto("Massa Macarrão 500g Milaneza", 0.95, 0.95, 0.95, R.drawable.massa_macarrao_milaneza));
        todos.add(new Produto("Massa Esparguete 500g Nacional", 0.75, 0.51, 0.75, R.drawable.massa_esparguete_nacional));
        todos.add(new Produto("Massa de Espirais 1Kg Nacional", 1.99, 1.89, 1.99, R.drawable.massa_espirais_nacional));
        todos.add(new Produto("Massa de Cotovelinhos 500g Caçarola", 1.09, 1.09, 1.09, R.drawable.massa_cotovelinhos_cacarola));
        todos.add(new Produto("Massa Espirais 500g Caçarola", 1.09, 1.09, 1.09, R.drawable.massa_espirais_cacarola));
        todos.add(new Produto("Pão de bicos", 0.19, 0.20, 0.21, R.drawable.pao_bicos));
        todos.add(new Produto("Pão de água", 1.99, 2.29, 2.09, R.drawable.pao_de_agua));
        todos.add(new Produto("Pão Serra da Estreal", 1.39, 1.35, 1.29, R.drawable.pao_de_centeio_serra_da_estrela));
        todos.add(new Produto("Pão de centeio", 1.95, 1.99, 2.01, R.drawable.pao_de_centeio));
        todos.add(new Produto("Pão de mistura", 0.59, 0.61, 0.58, R.drawable.pao_mistura));
        todos.add(new Produto("Queijo Flamengo Terra Nostra", 1.99, 2.19, 1.97, R.drawable.queijo_flamengo_fatias_terra_nostra));


        return todos;
    }


}
