package com.example.piv_dev_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piv_dev_project.R;
import com.example.piv_dev_project.lesson.CreateLessonActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment_main extends Fragment {

    FloatingActionButton actionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        FloatingActionButton calendarFOB = view.findViewById(R.id.mainFloatingActionButton);

        calendarFOB.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CreateLessonActivity.class);
            startActivity(intent);
        });

        return view;
    }
}