package com.example.piv_dev_project.user;

import android.text.Editable;

import java.util.List;

public class ProfessorNetwork extends ProfessorClass{
    String  id;
    List<Integer> groups;
    public ProfessorNetwork(Editable name, String id, List<Integer> groups) {
        super(String.valueOf(name));
        this.id = id;
        this.groups = groups;
    }

    public String getId() {
        return id;
    }

    public List<Integer> getGroups() {
        return groups;
    }
}
