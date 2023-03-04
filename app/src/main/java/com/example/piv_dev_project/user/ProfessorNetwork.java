package com.example.piv_dev_project.user;

import android.text.Editable;

import com.example.piv_dev_project.lesson.GroupClass;

import java.util.List;

public class ProfessorNetwork extends ProfessorClass{
    String  id;
    List<GroupClass> groups;
    public ProfessorNetwork(String name, String id, List<GroupClass> groups) {
        super(String.valueOf(name));
        this.id = id;
        this.groups = groups;
    }

    public String getId() {
        return id;
    }

    public List<GroupClass> getGroups() {
        return groups;
    }
}
