package com.example.ezgroceries.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ezgroceries.AppClass;
import com.example.ezgroceries.MainMenu;
import com.example.ezgroceries.R;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    private EditText usernameEditView;
    private EditText emailEditView;
    private EditText passwordEditView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        usernameEditView = (EditText)root.findViewById(R.id.usernameChange);
        emailEditView = (EditText)root.findViewById(R.id.emailChange);
        passwordEditView = (EditText)root.findViewById(R.id.passwordChange);


        Button doneButton = (Button) root.findViewById(R.id.done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainMenu.class);
                ((AppClass)getActivity().getApplication()).setGlobalVariable("user", usernameEditView.getText().toString());
                ((AppClass)getActivity().getApplication()).setGlobalVariable("email", emailEditView.getText().toString());
                startActivity(intent);
            }
        });
        return root;
    }
}