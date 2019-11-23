package com.example.ezgroceries.ui.gallery;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ezgroceries.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    ArrayList<String> listaFavoritos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        listaFavoritos = new ArrayList<>();
        listaFavoritos.add("Fiambre");
        listaFavoritos.add("Queijo");
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        final ListView listView = view.findViewById(R.id.ListaFavoritos);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_multiple_choice,
                listaFavoritos);
        listView.setAdapter(adapter);

        Button deleteButton = (Button) view.findViewById(R.id.DeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecker = listView.getCheckedItemPositions();
                int count = listView.getCount();
                for (int item = count-1; item>=0; item--){
                    if(positionChecker.get(item)){
                        adapter.remove(listaFavoritos.get(item));
                    }
                }
            }
        });


        return view;
    }
}