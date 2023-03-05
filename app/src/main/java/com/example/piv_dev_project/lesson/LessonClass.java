package com.example.piv_dev_project.lesson;

public class LessonClass {
    String lesson;
    String room;
    String description;
    String date;
    String time;
    String group;
    String mapsLink;

    public LessonClass(String lesson, String room, String description, String date, String time, String group, String mapsLink) {
        this.lesson = lesson;
        this.room = room;
        this.description = description;
        this.date = date;
        this.time = time;
        this.group = group;
        this.mapsLink = mapsLink;
    }
}
