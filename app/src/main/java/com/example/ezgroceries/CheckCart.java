package com.example.ezgroceries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Edits;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckCart extends AppCompatActivity {

    private Button removeItem;
    private Button finishShoping;
    private ListView listView;
    private ArrayList<Object> listaPlaceholder;
    private ProductAdapter adapter;
    private DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_cart);
        reff = FirebaseDatabase.getInstance().getReference().child("Product");

        listaPlaceholder = new ArrayList<>();
        listaPlaceholder.add(new String());

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String,Object> produtos = (Map<String,Object>) dataSnapshot.getValue();

                //iterate through each user, ignoring their UID
                for (Map.Entry<String, Object> entry : produtos.entrySet()){

                    //Get user map
                    Map singleProduct = (Map) entry.getValue();
                    //Get phone field and append to list
                    listaPlaceholder.add(new Product((String)singleProduct.get("name"), 2, (Double)singleProduct.get("price")));
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

                    listaPlaceholder.remove(p);
                }

                listView.setAdapter(adapter);
            }
        });

        finishShoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
