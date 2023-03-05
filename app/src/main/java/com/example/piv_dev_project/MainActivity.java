package com.example.piv_dev_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import com.example.piv_dev_project.auth.AuthActivity;
import com.example.piv_dev_project.auth.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    static FirebaseUser currentUser;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    public static NavController navCo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        navCo = navHostFragment.getNavController();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Ваші лекції");

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    navCo.navigate(R.id.fragment_main);
                    toolbar.setTitle("Ваші лекції");

                    return true;
                case R.id.navigation_dashboard:
                    navCo.navigate(R.id.fragment_groups);
                    toolbar.setTitle("Ваші групи");

                    return true;
                case R.id.navigation_notifications:
                    navCo.navigate(R.id.fragment_settings);
                    toolbar.setTitle("Налаштування");

                    return true;

            }
            return false;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();

        } else {
            Intent intent = new Intent(MainActivity.this, AuthActivity.class);
            startActivity(intent);
        }
    }
}