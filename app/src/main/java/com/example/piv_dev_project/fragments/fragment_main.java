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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
                        String hashMapToString = lessonClassMap.toString();
                        Log.d("Tagic", hashMapToString);
                        


                        HashMap<String, HashMap<String, String>> parsedData = new HashMap<>();
                        hashMapToString = hashMapToString.substring(1, hashMapToString.length() - 1); // Remove curly braces

                        String[] keyValuePairs = hashMapToString.split(", ");
                        for (String pair : keyValuePairs) {
                            String[] tokens = pair.split("=");
                            String outerKey = tokens[0];
                            String innerMapString = tokens[1];
                            innerMapString = innerMapString.substring(1, innerMapString.length() - 1); // Remove curly braces

                            String[] innerKeyValuePairs = innerMapString.split(", ");
                            HashMap<String, String> innerMap = new HashMap<>();
                            for (String innerPair : innerKeyValuePairs) {
                                String[] innerTokens = innerPair.split("=");
                                String innerKey = innerTokens[0];
                                String innerValue = innerTokens[1];
                                innerMap.put(innerKey, innerValue);
                            }
                            parsedData.put(outerKey, innerMap);
                        }
                        LessonClass ls = new LessonClass();

                        for(Map.Entry<String, HashMap<String, String>> item : parsedData.entrySet()){
                                ls.setDate((String) item.getValue().get("date"));
                                ls.setDescription((String) item.getValue().get("description"));
                                ls.setGroup((String) item.getValue().get("group"));
                                ls.setId((String) item.getValue().get("id"));
                                ls.setLesson((String) item.getValue().get("lesson"));
                                ls.setMapsLink((String) item.getValue().get("mapsLink"));
                                ls.setRoom((String) item.getValue().get("room"));
                                ls.setTime((String) item.getValue().get("time"));
                        }

                        lessonClassArrayList.add(ls);
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