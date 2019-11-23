package com.example.ezgroceries;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezgroceries.ui.Produtos.Produto;

import java.util.ArrayList;

public class FinalScreenActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    AdapterFinal myAdapter;
    ArrayList<Produto> carrinho;
    ArrayList<Integer> qtdd;
    TextView valView;
    RadioGroup radGrup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.rv_final);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        carrinho = getMyList1();
        qtdd = getMyList2();
        myAdapter = new AdapterFinal(this, carrinho, qtdd);
        mRecyclerView.setAdapter(myAdapter);

        radGrup = findViewById(R.id.radGrup);

        valView = findViewById(R.id.valTextView);
        valView.setText("Total: " + String.valueOf(calcPrice()) + " €");
        radGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                valView.setText("Total: " + String.valueOf(calcPrice()) + " €");
            }
        });

    }

    private double calcPrice() {
        double price = 0;
        if (radGrup.getCheckedRadioButtonId() == R.id.radioButtonCheap) {
            for(Produto p: carrinho) {
                price += p.melhorPreco2();
            }
        } else if (radGrup.getCheckedRadioButtonId() == R.id.radioButtonContinente) {
            for(Produto p: carrinho) {
                price += p.preco1();
            }
        } else if (radGrup.getCheckedRadioButtonId() == R.id.radioButtonJumbo) {
            for(Produto p: carrinho) {
                price += p.preco2();
            }
        } else if (radGrup.getCheckedRadioButtonId() == R.id.radioButtonPingo) {
            for(Produto p: carrinho) {
                price += p.preco3();
            }
        }
        return price;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.getItem(0).setVisible(false);
        return true;
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

    private ArrayList<Produto> getMyList1() {
        carrinho = new ArrayList<>();
        carrinho = ((AppClass)this.getApplication()).getCarrinho();

        return carrinho;
    }

    private ArrayList<Integer> getMyList2() {

        qtdd = new ArrayList<>(carrinho.size());
        int aux = 0;
        for( Produto p : carrinho) {
            aux = ((AppClass) this.getApplication()).getQuantity(p);
            qtdd.add(aux);
        }
        return qtdd;
    }
}
