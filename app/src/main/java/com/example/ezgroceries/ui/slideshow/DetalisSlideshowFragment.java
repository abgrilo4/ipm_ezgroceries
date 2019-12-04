package com.example.ezgroceries.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ezgroceries.AppClass;
import com.example.ezgroceries.Product;
import com.example.ezgroceries.R;
import com.example.ezgroceries.ui.Produtos.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetalisSlideshowFragment extends Fragment {

    private Map<Integer, ArrayList<String>> map;

    int checkBoxIndex;

    public DetalisSlideshowFragment(int checkBoxIndex){
        this.checkBoxIndex = checkBoxIndex;
        this.map = new HashMap<>();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArrayList<String> listKeys = new ArrayList<>();
        listKeys.addAll(((AppClass) getActivity().getApplication()).getListasFav().keySet());
        ArrayList<Produto> productsInList = new ArrayList<>();
        productsInList = ((AppClass) getActivity().getApplication()).getListasFav().get(listKeys.get(checkBoxIndex));

        ArrayList<String> nomes = new ArrayList<>();
        for (Produto p : productsInList) {
            nomes.add(p.nome());
        }

        View view = inflater.inflate(R.layout.fragment_slideshow_details, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.ListaIngredientes);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                nomes);
        listView.setAdapter(adapter);

        return view;
    }
}
