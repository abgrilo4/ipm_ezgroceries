package com.example.ezgroceries.ui.slideshow;

import android.content.Intent;
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
import com.example.ezgroceries.CheckCart;
import com.example.ezgroceries.FinalScreenActivity;
import com.example.ezgroceries.Product;
import com.example.ezgroceries.ProductAdapter;
import com.example.ezgroceries.ProductAdapterNoBoxes;
import com.example.ezgroceries.R;
import com.example.ezgroceries.ui.Produtos.Produto;

import java.util.ArrayList;
import java.util.HashMap;

public class DetalisSlideshowFragment extends Fragment {

    private ArrayList<Object> listaPlaceholder;

    int checkBoxIndex;
    private ListView listView;
    private ProductAdapterNoBoxes adapter;
    private Button useList;

    public DetalisSlideshowFragment(int checkBoxIndex){
        this.checkBoxIndex = checkBoxIndex;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final ArrayList<String> listKeys = new ArrayList<>();
        listKeys.addAll(((AppClass) getActivity().getApplication()).getListasFav().keySet());
        ArrayList<Produto> productsInList = ((AppClass) getActivity().getApplication()).getListasFav().get(listKeys.get(checkBoxIndex));

        ArrayList<String> nomes = new ArrayList<>();
        for (Produto p : productsInList) {
            nomes.add(p.nome());
        }
        ArrayList<String> precos = new ArrayList<>();
        for (Produto p : productsInList) {
            precos.add(p.melhorPreco());
        }


        View view = inflater.inflate(R.layout.fragment_slideshow_details, container, false);
        useList = (Button)view.findViewById(R.id.button5);
        listaPlaceholder = new ArrayList<>();

        listaPlaceholder.add(new String());

        HashMap<Produto, Integer> quantidade = ((AppClass)getActivity().getApplication()).quantFav.get(listKeys.get(checkBoxIndex));

        for (Produto p: productsInList) {
            listaPlaceholder.add(new Product(p.nome(),  quantidade.get(p), p.melhorPrecoFloat(), p));
        }


        listView = view.findViewById(R.id.ListaIngredientes);
        adapter = new ProductAdapterNoBoxes(getActivity(), listaPlaceholder);
        listView.setAdapter(adapter);

        useList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppClass)getActivity().getApplication()).setCarrinho(new ArrayList<Produto>(((AppClass)getActivity().getApplication()).getListasFav().get(listKeys.get(checkBoxIndex))));
                ((AppClass)getActivity().getApplication()).setQuantidade(new HashMap<Produto, Integer>(((AppClass)getActivity().getApplication()).getQuantFav().get(listKeys.get(checkBoxIndex))));
            }
        });

        return view;
    }
}
