package com.example.hp_pk.dictionary.classes;

import java.util.ArrayList;
import java.util.List;

public class LevelGroup {

    private List<String> lessonList = new ArrayList<>();
    private String level;

    public List<String> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<String> lessonList) {
        this.lessonList = lessonList;
    }

    public String getLevel() {
        return level;
    }

    public void addLesson(String lesson) {
        lessonList.add(lesson);
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
