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
import android.widget.Toast;

import com.example.ezgroceries.ui.Produtos.Produto;

import java.util.ArrayList;

public class CheckCart extends AppCompatActivity {

    private Button removeItem;
    private Button finishShoping;
    private Button reduceQuant;
    private ListView listView;
    private ArrayList<Object> listaPlaceholder;
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_cart);


        listaPlaceholder = new ArrayList<>();

        listaPlaceholder.add(new String());

        ArrayList<Produto> listaTeste = new ArrayList<>();

        listaTeste = ((AppClass)this.getApplication()).getCarrinho();

        for (Produto p: listaTeste) {
            listaPlaceholder.add(new Product(p.nome(),  ((AppClass)this.getApplication()).getQuantity(p), p.melhorPrecoFloat(), p));
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
        reduceQuant = (Button)findViewById(R.id.button6);
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
                if (((AppClass)getApplication()).isCartEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Carrinho vazio!", Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.TOP, 0, 125);
                    toast.show();
                } else {
                    Intent intent = new Intent(CheckCart.this, FinalScreenActivity.class);
                    startActivity(intent);
                }

            }
        });

        reduceQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Object> toRemove = new ArrayList<>();

                for (Object o: listaPlaceholder) {
                    if(o instanceof Product){
                        if(((Product) o).isCheckbox())
                            toRemove.add(o);

                        ((Product) o).setCheckboxFalse();
                    }


                }

                for (Object p: toRemove) {
                    Product product = (Product) p;

                    int quant = ((AppClass)CheckCart.this.getApplication()).getQuantidade().get(product.getProduto());
                    ((AppClass)CheckCart.this.getApplication()).getQuantidade().remove(product.getProduto());
                    ((AppClass)CheckCart.this.getApplication()).getQuantidade().put(product.getProduto(), quant-1);

                    for (Object o: listaPlaceholder) {
                        if(o == p)
                            ((Product) o).setNumber(quant-1);
                    }

                    if (quant-1 == 0){
                        ((AppClass)CheckCart.this.getApplication()).removeProdutoCarrinho(product.getProduto());
                        listaPlaceholder.remove(p);
                    }

                }

                listView.setAdapter(adapter);
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
