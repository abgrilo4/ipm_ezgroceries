package com.example.ezgroceries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ezgroceries.ui.Produtos.Produto;

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
        /*
        listaPlaceholder.add(new Product("Apple",2 , 1.0f));
        listaPlaceholder.add(new Product("Apple",2 , 2.0f));
        listaPlaceholder.add(new Product("Apple",2 , 3.0f));
        listaPlaceholder.add(new Product("Apple",2 , 4.0f));
        listaPlaceholder.add(new Product("Apple",2 , 5.0f));
        listaPlaceholder.add(new Product("Apple",2 , 6.0f));

         */

        ArrayList<Produto> listaTeste = new ArrayList<>();
        //((AppClass)this.getApplication()).addProdutoCarrinho(new Produto("Fiambre Perna Extra Fatias Nobre", 1.99, 1.99, 1.99, R.drawable.fiambre_perna_extra_fatias));
        //((AppClass)this.getApplication()).addProdutoCarrinho(new Produto("Fiambre Perna ", 1.99, 1.99, 1.99, R.drawable.fiambre_perna_extra_fatias));
        listaTeste = ((AppClass)this.getApplication()).getCarrinho();

        for (Produto p: listaTeste) {
            listaPlaceholder.add(new Product(p.nome(), 1, p.melhorPrecoFloat(), p));
        }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Carrinho");
        //ab.setDisplayHomeAsUpEnabled(true);

        // Enable the Up button

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
                    Product product = (Product) p;
                    listaPlaceholder.remove(p);
                    ((AppClass)CheckCart.this.getApplication()).removeProdutoCarrinho(product.getProduto());
                }

                listView.setAdapter(adapter);
            }
        });

        finishShoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckCart.this, FinalScreenActivity.class);
                startActivity(intent);
            }
        });
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
}
