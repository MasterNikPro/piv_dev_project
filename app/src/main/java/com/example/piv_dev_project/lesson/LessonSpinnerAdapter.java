package com.example.piv_dev_project.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.piv_dev_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LessonSpinnerAdapter extends ArrayAdapter<String> {
    LayoutInflater mInflater;
    Context mContext;
    List<String> groups;
    List<String> checkedGroups = new ArrayList<>();
    HashMap<Integer, Boolean> checked = new HashMap<>();
    int mResource;

    public List<String> getCheckedGroups() {
        return checkedGroups;
    }

    public LessonSpinnerAdapter(Context context, int resource, List<String> groups) {
        super(context, resource, groups);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        this.groups = groups;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.group_spinner_tile, parent, false);

        TextView spinnerItemTitle = view.findViewById(R.id.title_spinner);

        CheckBox spinnerItemCheckbox = view.findViewById(R.id.checkBoxPressed);

        String item = groups.get(position);
        spinnerItemTitle.setText(item);

        if (Boolean.TRUE.equals(checked.get(position))) {
            spinnerItemCheckbox.setChecked(Boolean.TRUE.equals(checked.get(position)));
        }

        spinnerItemCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (spinnerItemCheckbox.isChecked()) {
                    checked.put(position, true);
                    checkedGroups.add(spinnerItemTitle.getText().toString());
                } else {
                    checked.put(position, false);
                    checkedGroups.remove(spinnerItemTitle.getText().toString());
                }
            }
        });

        return view;
    }
}
