package com.example.piv_dev_project.lesson;

import java.util.List;

public class GroupClass {
    String name;
    String uid;
    String creator;

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getCreator() {
        return creator;
    }

    public GroupClass(String name, String uid, String creator) {
        this.name = name;
        this.uid = uid;
        this.creator = creator;
    }
}
