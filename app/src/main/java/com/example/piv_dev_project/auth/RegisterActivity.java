package com.example.piv_dev_project.auth;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piv_dev_project.MainActivity;
import com.example.piv_dev_project.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "MyApp";

    private EditText email_register;
    private EditText password_register;
    private Button button_register;
    private FirebaseAuth mAuth;
    ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        email_register = findViewById(R.id.editTextTextEmailAddress);
        password_register = findViewById(R.id.editTextTextPassword);
        button_register = findViewById(R.id.register_btn);

        toolbar = getSupportActionBar();
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(androidx.appcompat.R.attr.colorAccent, typedValue, true);
        int titleColor = typedValue.data;

        toolbar.hide();

        button_register.setOnClickListener(v -> {
            if (email_register.getText().toString().isEmpty() || password_register.getText().toString().isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Заповніть всі поля", Toast.LENGTH_SHORT).show();
            } else if (password_register.getText().toString().length() < 6) {
                Toast.makeText(RegisterActivity.this, "Пароль має бути більше 6 символів", Toast.LENGTH_SHORT).show();
            } else if (!email_register.getText().toString().contains("@")) {
                Toast.makeText(RegisterActivity.this, "Введіть ваш email", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Ой, щось не так", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}