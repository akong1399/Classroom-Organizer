package com.example.classroomorganizerjava;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

public class Task {

  private String name;
  private DayOfWeek[] days;
  private LocalTime time;
  private String audio;

  @SuppressLint("NewApi")
  public Task(String name, DayOfWeek[] days, String time, String audio) {
    this.name = name;
    this.days = days;
    this.time = LocalTime.parse(time);
    this.audio = audio;
  }

  @SuppressLint("NewApi")
  public Task(String line) {
    String[] items = line.split(", ");
    this.name = items[0];
    try {
      this.time = LocalTime.parse(items[2]);
    }
    catch (Exception e) {
      System.out.println("Task Parsing FAILED :(");
      System.out.println("Parsing: " + line);
    }
    this.audio = items[3];
    String[] weekdays = items[1].trim().split(" ");
    DayOfWeek[] temp = new DayOfWeek[weekdays.length];
    for (int i = 0; i < temp.length; i++) {
      temp[i] = DayOfWeek.valueOf(weekdays[i].trim());
    }
    this.days = temp;
  }

  public String getName() {
    return name;
  }

  public LocalTime getTime() {
    return time;
  }

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

  public void setAudio(String audio) {
    this.audio = audio;
  }


  @NonNull
  @Override
  public String toString() {
    String d = "";
    for (Enum e : this.days) {
      d += e.toString() + " ";
    }
    return "Task\n" + this.name + ", " + d + ", " + this.time + ", " + this.audio + "\n";
  }
}
