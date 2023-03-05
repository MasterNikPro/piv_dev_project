package com.example.piv_dev_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.piv_dev_project.R;
import com.example.piv_dev_project.lesson.CreateLessonActivity;
import com.example.piv_dev_project.lesson.LessonClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_main extends Fragment {

    FloatingActionButton actionButton;
    ListView lessonList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        lessonList=view.findViewById(R.id.lesson_list);
        MainAdapter adapter = new MainAdapter(getActivity(), new ArrayList<LessonClass>());

        FloatingActionButton calendarFOB = view.findViewById(R.id.mainFloatingActionButton);

        calendarFOB.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CreateLessonActivity.class);
            startActivity(intent);
        });

        return view;
    }
}