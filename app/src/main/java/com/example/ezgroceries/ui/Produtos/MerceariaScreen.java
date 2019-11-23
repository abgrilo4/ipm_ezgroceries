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

public class MerceariaScreen extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    ArrayList<Produto> mercearia;

    String[] categorias = {"Mercearia", "Todos", "Frescos", "Bebidas", "Laticínios", "Padaria"};

    Spinner sp;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercearia_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_m);
        setSupportActionBar(toolbar);

        sp = (Spinner)findViewById(R.id.spinner_mercearia);
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
                            intent = new Intent(MerceariaScreen.this, TodosScreen.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(MerceariaScreen.this, FrescosScreen.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(MerceariaScreen.this, BebidasScreen.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(MerceariaScreen.this, LaticiniosScreen.class);
                            startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(MerceariaScreen.this, PadariaScreen.class);
                            startActivity(intent);
                            break;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRecyclerView = findViewById(R.id.rv_mercearia);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);

        EditText search = findViewById(R.id.search_mercearia);
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

        for(Produto p : mercearia)
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
        Intent intent = new Intent(MerceariaScreen.this, MainMenu.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private ArrayList<Produto> getMyList() {
        mercearia = new ArrayList<>();
        mercearia.add(new Produto("Arroz Agulha 1Kg Cigala", 1.19, 1.16, 1.15, R.drawable.arroz_agulha_extra_longo_cigala));
        mercearia.add(new Produto("Arroz Carolino 1Kg Cigala", 1.15, 1.15, 0.89, R.drawable.arroz_carolino_extra_longo_cigala));
        mercearia.add(new Produto("Arroz Agulha 1Kg Saludães", 1.12, 1.09, 1.12, R.drawable.arroz_carolino_extra_longo_saludaes));
        mercearia.add(new Produto("Arroz Carolino 1Kg Saludães", 1.15, 1.15, 1.14, R.drawable.arroz_carolino_extra_longo_saludaes));
        mercearia.add(new Produto("Massa Esparguete 500g Milaneza", 0.56, 0.69, 0.55, R.drawable.massa_esparguete_milaneza));
        mercearia.add(new Produto("Massa Cotovelinhos 500g Milaneza", 0.95, 0.95, 0.95, R.drawable.massa_cotovelinhos_milaneza));
        mercearia.add(new Produto("Massa Macarrão 500g Milaneza", 0.95, 0.95, 0.95, R.drawable.massa_macarrao_milaneza));
        mercearia.add(new Produto("Massa Esparguete 500g Nacional", 0.75, 0.51, 0.75, R.drawable.massa_esparguete_nacional));
        mercearia.add(new Produto("Massa de Espirais 1Kg Nacional", 1.99, 1.89, 1.99, R.drawable.massa_espirais_nacional));
        mercearia.add(new Produto("Massa de Cotovelinhos 500g Caçarola", 1.09, 1.09, 1.09, R.drawable.massa_cotovelinhos_cacarola));
        mercearia.add(new Produto("Massa Espirais 500g Caçarola", 1.09, 1.09, 1.09, R.drawable.massa_espirais_cacarola));
        return mercearia;
    }


}
