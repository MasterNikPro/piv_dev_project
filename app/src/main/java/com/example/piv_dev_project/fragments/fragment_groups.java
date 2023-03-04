package com.example.piv_dev_project.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piv_dev_project.R;
import com.example.piv_dev_project.group.AddExistGroupDialog;
import com.example.piv_dev_project.group.AddGroupDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class fragment_groups extends Fragment {
    FloatingActionButton addNewGroupFOB;
    FloatingActionButton addExistGroupFOB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);

        addNewGroupFOB = view.findViewById(R.id.addNewGroupFloatingActionButton);
        addExistGroupFOB = view.findViewById(R.id.addExistGroupFloatingActionButton);

        addNewGroupFOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddGroupDialog();
            }
        });

        addExistGroupFOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddExistGroupDialog();
            }
        });
        return view;
    }

    private void openAddExistGroupDialog() {
        AddExistGroupDialog addFriendDialog = new AddExistGroupDialog();
        addFriendDialog.show(getFragmentManager(), "Додати учасника");
    }

    private void openAddGroupDialog() {
        AddGroupDialog addFriendDialog = new AddGroupDialog();
        addFriendDialog.show(getFragmentManager(), "Додати учасника");
    }
}