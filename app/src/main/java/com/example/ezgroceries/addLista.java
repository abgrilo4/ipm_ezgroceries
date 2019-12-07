package com.example.ezgroceries;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ezgroceries.ui.Produtos.Produto;

import java.util.ArrayList;
import java.util.HashMap;

public class addLista extends AppCompatActivity {

    private Button add;
    private String nome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lista);
        nome = "";

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Adicionar Lista");

        EditText setterNome = findViewById(R.id.editText);

        add = (Button)findViewById(R.id.button4);

        setterNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                nome = s.toString();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((AppClass) addLista.this.getApplication()).getCarrinho().isEmpty()) {
                    Toast.makeText(addLista.this, "Carrinho vazio", Toast.LENGTH_LONG).show();
                }else if(nome.equals("")){
                    Toast.makeText(addLista.this, "Inserir nome da lista", Toast.LENGTH_LONG).show();
                }else{
                    ((AppClass)addLista.this.getApplication()).getListasFav().put(
                            nome,
                            new ArrayList<Produto> (((AppClass)addLista.this.getApplication()).getCarrinho())
                    );

                    ((AppClass)addLista.this.getApplication()).getQuantFav().put(
                            nome,
                            new HashMap<Produto, Integer>(((AppClass)addLista.this.getApplication()).getQuantidade())
                    );


                    Toast.makeText(addLista.this, "Lista adicionada", Toast.LENGTH_LONG).show();
                    addLista.this.finish();
                }
            }
        });



    }
}
