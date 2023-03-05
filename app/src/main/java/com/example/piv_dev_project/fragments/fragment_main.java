package com.example.piv_dev_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.piv_dev_project.R;
import com.example.piv_dev_project.lesson.CreateLessonActivity;
import com.example.piv_dev_project.lesson.LessonClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class fragment_main extends Fragment {

    FloatingActionButton actionButton;
    ListView lessonList;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
                lessonList = view.findViewById(R.id.lesson_list);
        List<LessonClass> lessonClassArrayList = new ArrayList<>();
        db.collection("Groups").whereEqualTo("creator",mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Loh", document.getId() + " => " + document.getData());
                        Map<String, Object> map= document.getData();
                        Map<String,LessonClass> lessonClassMap= (Map<String, LessonClass>) map.get("lessons");
                       Log.d("Tagic", String.valueOf(lessonClassMap));
                        for(Map.Entry<String, LessonClass> item : lessonClassMap.entrySet()){
                          // LessonClass ls= item.getValue();
                           //lessonClassArrayList.add()

                        }
                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
        MainAdapter adapter = new MainAdapter(getActivity(), lessonClassArrayList);
        lessonList.setAdapter(adapter);
        FloatingActionButton calendarFOB = view.findViewById(R.id.mainFloatingActionButton);

        calendarFOB.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CreateLessonActivity.class);
            startActivity(intent);
        });

        return view;
    }
}