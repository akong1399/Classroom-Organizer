package com.example.classroomorganizerjava;

import androidx.annotation.NonNull;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private ArrayList<Task> tasks;
    private List<DayOfWeek> daysPresent;

    public Student(String name, ArrayList<Task> tasks) {
        this.name = name;
        this.tasks = new ArrayList<>(tasks);
        this.daysPresent = null;
    }
    public Student(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.daysPresent = null;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<DayOfWeek> getDaysPresent() {
        return daysPresent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDaysPresent(List<DayOfWeek> daysPresent) {
        this.daysPresent = daysPresent;
    }

    public void addTask(Task t){
        this.tasks.add(t);
    }

    public void addTask(String t) { this.tasks.add(new Task(t)); }

    public boolean removeTask(Task t){
        return this.tasks.remove(t);
    }

    public void updateTask(ArrayList<Task> t){
        this.tasks = t;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nStudent: " + this.name + "\n" + this.tasks.toString();
    }
}
