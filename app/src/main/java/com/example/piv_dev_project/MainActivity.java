package com.example.piv_dev_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import com.example.piv_dev_project.auth.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    static FirebaseUser currentUser;
    public static NavController navCo;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        navCo = navHostFragment.getNavController();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.calendar_item:
                    navCo.navigate(R.id.fragment_main);
                    //toolbar.setTitle(Html.fromHtml("<b><font face = '' color='" + titleColor + "'>Ваші справи</font></b>"));

                    return true;
                case R.id.friends_item:
                    navCo.navigate(R.id.fragment_groups);
                    //toolbar.setTitle(Html.fromHtml("<b><font face = '' color='" + titleColor + "'>Список учасників</font></b>"));

                    return true;
                case R.id.settings_item:
                    navCo.navigate(R.id.fragment_settings);
                    //toolbar.setTitle(Html.fromHtml("<b><font face = '' color='" + titleColor + "'>Налаштування</font></b>"));

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
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}