package com.example.ezgroceries;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ezgroceries.ui.login.LoginActivity;
import com.example.ezgroceries.ui.slideshow.DetalisSlideshowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private String username;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        toolbar.setTitle("");
        NavigationUI.setupWithNavController(navigationView, navController);
        //Get user's name and email
        /*if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username = ((TextView) findViewById(R.id.sliderUsername)).getText().toString();
                email = ((TextView) findViewById(R.id.sliderEmail)).getText().toString();

            } else {
                username = extras.getString("user");
                email = extras.getString("email");
            }
        } else {
            username= (String) savedInstanceState.getSerializable("user");
            email= (String) savedInstanceState.getSerializable("email");
        }
        ((AppClass)getApplication()).setGlobalVariable("user", username);
        ((AppClass)getApplication()).setGlobalVariable("email", email);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Set username and email
        TextView userText = (TextView) findViewById(R.id.sliderUsername);
        userText.setText(((AppClass)getApplication()).getGlobalVariable("user"));
        TextView emailText = (TextView) findViewById(R.id.sliderEmail);
        emailText.setText(((AppClass)getApplication()).getGlobalVariable("email"));
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
