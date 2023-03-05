package com.example.piv_dev_project.fragments;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.piv_dev_project.R;
import com.example.piv_dev_project.lesson.GroupClass;

import java.util.ArrayList;
import java.util.List;

public class FragmentGroupAdapter extends ArrayAdapter<GroupClass> {

    private final Activity context;
    TextView copy;
    TextView delete;
    TextView groupname;
    private List<GroupClass> data = new ArrayList<GroupClass>();

    public FragmentGroupAdapter(Activity context, List<GroupClass> data) {
        super(context, R.layout.group_tile, data);
        this.context = context;
        this.data = data;
    }


    public View getView(int position, View view, ViewGroup parent) {
        View listItem = view;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.group_tile, parent, false);
        copy = listItem.findViewById(R.id.copy);
        delete = listItem.findViewById(R.id.delete);
        groupname = listItem.findViewById(R.id.group_name);
        GroupClass groupClass = data.get(position);
        groupname.setText(groupClass.getName());

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {/*
                ClipboardManager clipboard = (ClipboardManager) getSystemService(view.getContext(), ClipboardManager.class);
                ClipData clip = ClipData.newPlainText("label", data.get(position).getGroups().get(position).getUid());
                clipboard.setPrimaryClip(clip);*/
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return listItem;
    }


}
