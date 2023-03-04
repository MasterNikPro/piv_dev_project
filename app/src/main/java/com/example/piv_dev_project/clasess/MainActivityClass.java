package com.example.piv_dev_project.clasess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.piv_dev_project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityClass extends AppCompatActivity {
    private FirebaseAuth mAuth;
    static FirebaseUser currentUser;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private static NavController navCo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_group);
        mAuth = FirebaseAuth.getInstance();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentClassesContainerView);
        navCo = navHostFragment.getNavController();

        bottomNavigationView = findViewById(R.id.bottomClassesNavigationView);

        navCo.navigate(R.id.fragment_classes_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Ваші лекції");

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.main_classes_menu_item:
                    navCo.navigate(R.id.fragment_classes_main);
                    toolbar.setTitle("Ваші лекції");

                    return true;
                case R.id.map_classes_menu_item:
                    navCo.navigate(R.id.frarment_classes_maps);
                    toolbar.setTitle("Ваші лекції");

                    return true;
                case R.id.settings_classes_menu_item:
                    navCo.navigate(R.id.fragment_classes_settings);
                    toolbar.setTitle("Налаштування");

                    return true;

            }
            return false;
        });
    }
}
