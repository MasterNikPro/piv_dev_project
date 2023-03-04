package com.example.piv_dev_project.lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.piv_dev_project.MainActivity;
import com.example.piv_dev_project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class CreateLessonActivity extends AppCompatActivity {
    TextInputLayout title;
    TextInputLayout description;
    TextView time;
    TextView date;
    Spinner group;
    TextInputEditText link;
    Button add;
    TextView cancel;
    FirebaseFirestore db;
    Calendar dateAndTime = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date.setText(DateUtils.formatDateTime(CreateLessonActivity.this,
                    dateAndTime.getTimeInMillis(),
                    DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
        }
    };
    TimePickerDialog.OnTimeSetListener t = (view, hourOfDay, minute) -> {
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateAndTime.set(Calendar.MINUTE, minute);
        time.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));
    };

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

        time = findViewById(R.id.add_node_time_view_id);
        date = findViewById(R.id.add_node_date_view_id);
        time.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));
        date.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
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

    public void setTime(View v) {
        new TimePickerDialog(CreateLessonActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    public void setDate(View v) {
        new DatePickerDialog(CreateLessonActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();

    }
}