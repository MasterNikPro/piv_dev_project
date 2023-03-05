package com.example.piv_dev_project.lesson;

import java.util.HashMap;
import java.util.Map;

public class GroupClass {

    private  String name;
    private  String uid;
    private  String creator;
    public Map<String, LessonClass> lessons;

    public GroupClass(String name, String uid, String creator) {
        this.name = name;
        this.uid = uid;
        this.creator = creator;
    }

    public GroupClass() {
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getCreator() {
        return creator;
    }
}
