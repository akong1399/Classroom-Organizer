package com.example.classroomorganizerjava.components;

import androidx.annotation.NonNull;

import java.time.DayOfWeek;
import java.util.Arrays;

public class Student {
    private String name;
    private Task[] tasks;
    private DayOfWeek[] daysPresent;

    public Student(String name, Task[] tasks, DayOfWeek[] daysPresent) {
        this.name = name;
        this.tasks = tasks;
        this.daysPresent = daysPresent;
    }

    public String getName() {
        return name;
    }

    // TODO: return copy
    public Task[] getTasks() {
        return tasks;
    }

    // TODO: return copy
    public DayOfWeek[] getDaysPresent() {
        return daysPresent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDaysPresent(DayOfWeek[] daysPresent) {
        this.daysPresent = daysPresent;
    }

    // TODO: implement
    public void addTask(Task t){
    }

    // TODO: implement
    // should we take in string name?
    public void removeTask(Task t){
    }

    // TODO: implement
    public void updateTask(Task[] t){
    }

    @NonNull
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", tasks=" + Arrays.toString(tasks) +
                ", daysPresent=" + Arrays.toString(daysPresent) +
                '}';
    }
}
