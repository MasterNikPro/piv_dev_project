package com.example.piv_dev_project.lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.piv_dev_project.MainActivity;
import com.example.piv_dev_project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class CreateLessonActivity extends AppCompatActivity {
    TextInputLayout title;
    TextInputLayout description;
    String time;
    String date;
    Spinner group;
    TextInputEditText link;
    Button add;
    TextView cancel;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lesson);
        title= findViewById(R.id.add_note_set_title_input_layout);
        description= findViewById(R.id.add_note_set_description_input_layout);
        group=findViewById(R.id.add_node_set_friend_spinner_id);
        link=findViewById(R.id.add_note_link_google_map_layout_edit_text_view_id);
        add=findViewById(R.id.add_note_save_note_button);
        cancel=findViewById(R.id.add_note_cancel_text_view);
        db = FirebaseFirestore.getInstance();
    }

    public void cancel(View view) {
        Intent intent= new Intent(CreateLessonActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void add(View view) {

       // db.collection(MainActivity.getUser().getUser().getUid()).add(addingElement);
        Intent intent = new Intent(CreateLessonActivity.this, MainActivity.class);
        startActivity(intent);

    }
}