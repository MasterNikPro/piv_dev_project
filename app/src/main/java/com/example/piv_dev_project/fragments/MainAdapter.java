package com.example.piv_dev_project.fragments;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.piv_dev_project.R;
import com.example.piv_dev_project.lesson.GroupClass;
import com.example.piv_dev_project.lesson.LessonClass;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends ArrayAdapter<LessonClass> {
    private final Activity context;
    TextView subject;
    TextView room;
    TextView groupname;
    TextView time;
    TextView date;

    private List<LessonClass> data = new ArrayList<LessonClass>();
    public MainAdapter(Activity context, List<LessonClass> data) {
        super(context, R.layout.main_tile, data);
        this.context = context;
        this.data = data;
    }
    public View getView(int position, View view, ViewGroup parent) {
        View listItem = view;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.group_tile, parent, false);
        subject=listItem.findViewById(R.id.subject_name);
        room=listItem.findViewById(R.id.room_name);
        groupname=listItem.findViewById(R.id.group);
        time=listItem.findViewById(R.id.time);
        date=listItem.findViewById(R.id.date);



        return listItem;
    }


}
