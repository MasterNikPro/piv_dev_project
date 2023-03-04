package com.example.piv_dev_project.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.piv_dev_project.R;
import com.google.android.material.textfield.TextInputEditText;

public class AuthActivity extends AppCompatActivity {
    private Button enterIdGroupBtn;
    private TextInputEditText idGroupEditText;
    private TextView imProfessor;
    private int idGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        enterIdGroupBtn = findViewById(R.id.auth_confirm_id_button);
        idGroupEditText = findViewById(R.id.editTextTextIdGroup);
        imProfessor = findViewById(R.id.auth_text_view_professor);

        enterIdGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}