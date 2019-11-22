package com.example.ezgroceries.ui.slideshow;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ezgroceries.R;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    ArrayList<String> listaPlaceholder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        listaPlaceholder = new ArrayList<>();
        listaPlaceholder.add("Bolo de cenoura");
        listaPlaceholder.add("Açorda");
        listaPlaceholder.add("Caril de frango");
        listaPlaceholder.add("Canja");

        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);

        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.ListasFavoritas);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_multiple_choice,
                listaPlaceholder);
        listView.setAdapter(adapter);

        Button removeButton = (Button) view.findViewById(R.id.RemoveButton);
        Button checkButton = (Button) view.findViewById(R.id.CheckButton);


        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecker = listView.getCheckedItemPositions();
                int count = listView.getCount();
                for (int item = count-1; item>=0; item--){
                    if(positionChecker.get(item)){
                        adapter.remove(listaPlaceholder.get(item));
                    }
                }
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecker = listView.getCheckedItemPositions();
                int count = listView.getCount();
                for (int item = count-1; item>=0; item--){
                    if(positionChecker.get(item)){
                        FragmentTransaction fragTrans =  getFragmentManager().beginTransaction();
                        fragTrans.replace(R.id.nav_host_fragment, new DetalisSlideshowFragment(item));
                        fragTrans.addToBackStack(null);
                        fragTrans.commit();
                    }
                }
            }
        });



        return view;
    }
}