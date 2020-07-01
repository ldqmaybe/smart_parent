package com.smart.jetpack.ui.main;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smart.jetpack.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_view);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_navigation_fragment);
        NavController navController = navHostFragment.getNavController();

        initBottomView(bottomNavigationView, navController);
    }

    private void initBottomView(BottomNavigationView bottomNavigationView, NavController navController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}