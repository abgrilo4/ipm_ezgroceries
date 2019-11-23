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

import com.example.ezgroceries.R;

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
        ArrayList<String> list = new ArrayList<>();
        list.add("Cenouras");
        list.add("Farinha");
        map.put(0, list);
        list = new ArrayList<>();
        list.add("Pão");
        list.add("Alho");
        list.add("Sal");
        map.put(1, list);
        list = new ArrayList<>();
        list.add("Frango");
        list.add("Caril em pó");
        list.add("Arroz");
        map.put(2, list);
        list = new ArrayList<>();
        list.add("Frango");
        list.add("Arroz");
        list.add("Cebola");
        map.put(3, list);

        View view = inflater.inflate(R.layout.fragment_slideshow_details, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.ListaIngredientes);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                map.get(checkBoxIndex));
        listView.setAdapter(adapter);

        return view;
    }
}
