package com.example.classroomorganizerjava;

import android.util.Log;
import androidx.annotation.NonNull;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Student {

    private static final String TAG = "Student";
    private String name;
    private ArrayList<Task> tasks;

    public Student(String name, ArrayList<Task> tasks) {
        this.name = name;
        this.tasks = new ArrayList<>(tasks);
    }

    public Student(String data) {
        String[] lines = data.split("\n");
        this.name = lines[0];
        this.tasks = new ArrayList<>();
        for (String l : Arrays.copyOfRange(lines,1, lines.length)) {
            this.tasks.add(new Task(l));
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    public Task getRecentTask() { return this.tasks.get(this.tasks.size() - 1); }

    public Alarm addTask(String task) {
        Task t = new Task(task);
        Log.e(TAG, "Curr tasks: " + this.tasks.get(this.tasks.size() - 1));
        Log.e(TAG, "new task?: "  + t);
        Log.e(TAG, "contains?: " + this.tasks.get(this.tasks.size() - 1).equals(t));
        if (this.tasks.contains(t)) {
            return null;
        }
        this.tasks.add(t);
        return t.getAlarm();
    }

    public boolean removeTask (String task) {
        Task t = new Task(task);
        return this.tasks.remove(t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects
            .equals(tasks, student.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tasks);
    }

    @NonNull
    @Override
    public String toString() {
        String ret = "Student: " + this.name;
        for (Task t : this.tasks) {
            ret += "\n" + t.toString();
        }
        return ret;
    }
}
