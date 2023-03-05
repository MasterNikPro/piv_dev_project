package com.example.piv_dev_project.group;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.piv_dev_project.MainActivity;
import com.example.piv_dev_project.R;
import com.example.piv_dev_project.lesson.GroupClass;
import com.example.piv_dev_project.lesson.LessonClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class AddGroupDialog extends AppCompatDialogFragment {

    private TextInputEditText nameNewGroup;
    private String nameNewGroupStr;
    FirebaseFirestore db;
    FirebaseAuth mAuth;


    private AddGroupDialog.AddFriendListener listener;
    String name;
    String uid;
    List<GroupClass> groups=new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_new_group, null);

        nameNewGroup = view.findViewById(R.id.add_new_group_set_namegroup_edit_text);

        builder.setView(view)
                .setNegativeButton("скасувати", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("додати", new DialogInterface.OnClickListener() {
                    //тут инициализация имейла друга
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameNewGroupStr = nameNewGroup.getText().toString();
                        Random random = new Random();
                        int randomNumber = random.nextInt(89999) + 10000;
                        Map<String, Object> dataGroup = new HashMap<>();
                        dataGroup.put("name",nameNewGroupStr);
                        dataGroup.put("creator",mAuth.getCurrentUser().getUid());
                        dataGroup.put("uid",String.valueOf(randomNumber));
                       // HashMap<String, LessonClass> lessons= new HashMap<>();
                       // lessons.put("111",new LessonClass("","","","","","",""));
                        dataGroup.put("lessons",new HashMap<>());

                        db.collection("Groups").document(String.valueOf(randomNumber)).set(dataGroup);

                        MainActivity.navCo.navigate(R.id.fragment_groups);




                        //db.collection("friends"+ MainActivity.getUser().getUser().getUid().toString()).add(ab);

                        //MainActivity.navCo.navigate(R.id.friends);
                        //listener.applyText(newFriend);
                        //FriendsFragment.addFriend(newFriend);

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddGroupDialog.AddFriendListener) context;
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }

    }

    public interface AddFriendListener {
        void applyText(String friendEmail);
    }

}