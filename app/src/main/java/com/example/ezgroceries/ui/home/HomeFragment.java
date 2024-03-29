package com.example.ezgroceries.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ezgroceries.FinalScreenActivity;
import com.example.ezgroceries.HomeScreen;
import com.example.ezgroceries.LocationScreen;
import com.example.ezgroceries.MainMenu;
import com.example.ezgroceries.R;
import com.example.ezgroceries.LocationScreen;
import com.example.ezgroceries.R;
import com.example.ezgroceries.ui.Produtos.TodosScreen;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button locBtn = (Button) root.findViewById(R.id.localizacao);
        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationScreen.class);
                startActivity(intent);
            }
        });

        Button prodBtn = (Button) root.findViewById(R.id.produtos);
        prodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TodosScreen.class);
                startActivity(intent);
            }
        });

        return root;
    }
}