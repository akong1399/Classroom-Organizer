package com.example.classroomorganizerjava.components;

import androidx.annotation.NonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

public class Task {
    private String name;
    private LocalTime time;
    private DayOfWeek[] days;
    private String audio;

    public Task(String name, LocalTime time, DayOfWeek[] days, String audio) {
        this.name = name;
        this.time = time;
        this.days = days;
        this.audio = audio;
    }

    public String getName() {
        return name;
    }

    // TODO: return copy
    public LocalTime getTime() {
        return time;
    }

    // TODO: return copy
    public DayOfWeek[] getDays() {
        return days;
    }

    public String getAudio() {
        return audio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDays(DayOfWeek[] days) {
        this.days = days;
    }

    // TODO: verify path
    public void setAudio(String audio) {
        this.audio = audio;
    }

    @NonNull
    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", days=" + Arrays.toString(days) +
                ", audio='" + audio + '\'' +
                '}';
    }
}
