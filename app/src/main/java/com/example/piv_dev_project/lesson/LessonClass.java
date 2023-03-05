package com.example.piv_dev_project.lesson;

import java.util.Random;

public class LessonClass {
    String id;
    String lesson;
    String room;
    String description;
    String date;
    String time;
    String group;
    String mapsLink;

    public String getId() {
        return id;
    }

    public String getLesson() {
        return lesson;
    }

    public String getRoom() {
        return room;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getGroup() {
        return group;
    }

    public String getMapsLink() {
        return mapsLink;
    }
    public LessonClass(){

    }
    public LessonClass(String lesson, String room, String description, String date, String time, String group, String mapsLink) {

        this.lesson = lesson;
        this.room = room;
        this.description = description;
        this.date = date;
        this.time = time;
        this.group = group;
        this.mapsLink = mapsLink;

        Random random = new Random();
        int randomNumber = random.nextInt(899999) + 100000;
        id=String.valueOf(randomNumber);
    }
}
