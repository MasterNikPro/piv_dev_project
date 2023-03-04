package com.example.piv_dev_project.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


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
        db.collection(mAuth.getCurrentUser().getUid()).document("professor").collection("info").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    // Access data in documentSnapshot
                    String name = documentSnapshot.getString("name");
                    String  uid = documentSnapshot.getString("uid");
                    List<String> names= (List<String>) documentSnapshot.get("groupsname");
                    List<String> uids= (List<String>) documentSnapshot.get("groupsuid");
                    List<GroupClass> groups=new ArrayList<>();
                    for (int i=0;i<names.size();i++){
                        assert uids != null;
                        groups.add(new GroupClass(names.get(i),uids.get(i)));
                    }

                    groupClasses.add(new ProfessorNetwork(name,uid,groups));
                    // ...
                }
            }
        });
        if(!groupClasses.isEmpty()){
            FragmentGroupAdapter adapter= new FragmentGroupAdapter(getActivity(),  groupClasses);
            groupList.setAdapter(adapter);
        }

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