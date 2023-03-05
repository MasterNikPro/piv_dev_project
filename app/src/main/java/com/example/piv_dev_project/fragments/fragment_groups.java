package com.example.piv_dev_project.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.piv_dev_project.R;
import com.example.piv_dev_project.group.AddExistGroupDialog;
import com.example.piv_dev_project.group.AddGroupDialog;
import com.example.piv_dev_project.lesson.GroupClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class fragment_groups extends Fragment {
    //List<ProfessorNetwork> groupClasses=new ArrayList<>();
    public static List<GroupClass> groupClasses = new ArrayList<>();
    public static List<String> groupClassesNames = new ArrayList<>();
    FloatingActionButton addNewGroupFOB;
    FloatingActionButton addExistGroupFOB;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    ListView groupList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        groupList = view.findViewById(R.id.groups_list_page);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        addNewGroupFOB = view.findViewById(R.id.addNewGroupFloatingActionButton);
        addExistGroupFOB = view.findViewById(R.id.addExistGroupFloatingActionButton);
        groupClasses.clear();

        db.collection("Groups").whereEqualTo("creator", mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Loh", document.getId() + " => " + document.getData());
                        if (groupClasses.size() != task.getResult().size()) {
                            groupClasses.add(document.toObject(GroupClass.class));
                        }
                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }

                FragmentGroupAdapter adapter = new FragmentGroupAdapter(getActivity(), groupClasses);
                groupList.setAdapter(adapter);

                for (GroupClass groupClass : groupClasses) {
                    groupClassesNames.add(groupClass.getName());
                }
            }
        });



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