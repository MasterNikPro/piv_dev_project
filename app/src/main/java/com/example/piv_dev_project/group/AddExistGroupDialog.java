package com.example.piv_dev_project.group;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.piv_dev_project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class AddExistGroupDialog extends AppCompatDialogFragment {

    private TextInputEditText idExistGroup;
    private String idExistGroupStr;
    FirebaseFirestore db;

    private AddFriendListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_exist_group, null);

        idExistGroup = view.findViewById(R.id.add_exist_group_set_namegroup_edit_text);

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
                        idExistGroupStr = Objects.requireNonNull(idExistGroup.getText()).toString();
                        class Lol{
                            String friend;

                            public String getFriend() {
                                return friend;
                            }
                            Lol(String friend){
                                this.friend =friend;
                            }
                        }
                        Lol ab= new Lol(idExistGroupStr.toString());

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
            listener = (AddFriendListener) context;
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }

    }

    public interface AddFriendListener {
        void applyText(String friendEmail);
    }

}