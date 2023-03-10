package com.example.piv_dev_project.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.piv_dev_project.MainActivity;
import com.example.piv_dev_project.R;
import com.example.piv_dev_project.lesson.GroupClass;
import com.example.piv_dev_project.user.ProfessorNetwork;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "MyApp";
    ActionBar toolbar;
    private EditText name_professor;
    private EditText email_register;
    private EditText password_register;
    private Button button_register;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        email_register = findViewById(R.id.editTextTextEmailAddress);
        password_register = findViewById(R.id.editTextTextPassword);
        button_register = findViewById(R.id.register_btn);
        name_professor = findViewById(R.id.editTextTextNameProfessor);

        toolbar = getSupportActionBar();
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(androidx.appcompat.R.attr.colorAccent, typedValue, true);
        int titleColor = typedValue.data;

        //toolbar.hide();

        button_register.setOnClickListener(v -> {
            if (email_register.getText().toString().isEmpty() || password_register.getText().toString().isEmpty()) {
                Toast.makeText(RegisterActivity.this, "?????????????????? ?????? ????????", Toast.LENGTH_SHORT).show();
            } else if (password_register.getText().toString().length() < 6) {
                Toast.makeText(RegisterActivity.this, "???????????? ?????? ???????? ???????????? 6 ????????????????", Toast.LENGTH_SHORT).show();
            } else if (!email_register.getText().toString().contains("@")) {
                Toast.makeText(RegisterActivity.this, "?????????????? ?????? email", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                // intent.putExtra("professorname",name_professor.getText());
                                ProfessorNetwork tempprof = new ProfessorNetwork(name_professor.getText().toString(), mAuth.getCurrentUser().getUid().toString(), new ArrayList<>());
                                Map<String, Object> data = new HashMap<>();
                                data.put("name", tempprof.getName());
                                data.put("uid", tempprof.getId());
                                data.put("groups", new ArrayList<GroupClass>());
                                db.collection(mAuth.getCurrentUser().getUid()).document("professor").set(data);
                                startActivity(intent);
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "????, ???????? ???? ??????", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}