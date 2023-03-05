package com.example.piv_dev_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.piv_dev_project.R;
import com.example.piv_dev_project.group.AddExistGroupDialog;
import com.example.piv_dev_project.group.AddGroupDialog;
import com.example.piv_dev_project.lesson.GroupClass;
import com.example.piv_dev_project.user.ProfessorNetwork;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class fragment_groups extends Fragment {
    FloatingActionButton addNewGroupFOB;
    FloatingActionButton addExistGroupFOB;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    ListView groupList;
    List<ProfessorNetwork> groupClasses=new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        groupList= view.findViewById(R.id.groups_list_page);
        addNewGroupFOB = view.findViewById(R.id.addNewGroupFloatingActionButton);
        addExistGroupFOB = view.findViewById(R.id.addExistGroupFloatingActionButton);
        db.collection("Groups").whereEqualTo("creator",mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Loh", document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
            FragmentGroupAdapter adapter= new FragmentGroupAdapter(getActivity(),  groupClasses);
            groupList.setAdapter(adapter);


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