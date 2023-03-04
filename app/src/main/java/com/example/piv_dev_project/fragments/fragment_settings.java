package com.example.piv_dev_project.fragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.piv_dev_project.MainActivity;
import com.example.piv_dev_project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class fragment_settings extends Fragment {

    FirebaseAuth mAuth;
    FirebaseUser user;
    TextInputEditText newEmailEditText;
    Button setNewEmail;
    TextInputEditText newPasswordEditText;
    TextInputEditText newPasswordCheckEditText;
    Button setNewPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();

        Button logout_button = view.findViewById(R.id.settings_log_out_button);
        newEmailEditText = view.findViewById(R.id.edit_text_view_edit_email);
        setNewEmail = view.findViewById(R.id.button_save_edit_email);
        newPasswordEditText = view.findViewById(R.id.edit_text_view_edit_password);
        newPasswordCheckEditText = view.findViewById(R.id.edit_text_check_edit_password);
        setNewPassword = view.findViewById(R.id.button_save_edit_password);
        logout_button.setOnClickListener(v -> {
            mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();

            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });

        setNewEmail.setOnClickListener(v -> {
            if (!(newEmailEditText.getText().toString().isEmpty()) && (newEmailEditText.getText().toString().contains("@"))) {
                user.updateEmail(newEmailEditText.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User email address updated.");
                                Toast.makeText(getContext(), "Ваш email змінено", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Для того щоб змінити email заново пройдіть авторизацію", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(getContext(), "Введіть ваш email", Toast.LENGTH_SHORT).show();
            }

        });

        setNewPassword.setOnClickListener(v -> {
            if (!(newPasswordEditText.getText().toString().isEmpty() && newPasswordCheckEditText.getText().toString().isEmpty()) &&
                    newPasswordEditText.getText().toString().equals(newPasswordCheckEditText.getText().toString())) {

                user.updatePassword(newPasswordEditText.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User password updated.");
                                Toast.makeText(getContext(), "Пароль успішно змінено", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Для того щоб змінити пароль заново пройдіть авторизацію", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else if (newPasswordEditText.getText().toString().isEmpty() && newPasswordCheckEditText.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Заповніть всі поля", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Паролі не співпадають", Toast.LENGTH_SHORT).show();
            }
        });
    }
}