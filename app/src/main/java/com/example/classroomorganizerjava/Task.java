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
import java.util.Objects;

public class Task {

  private static final String TAG = "Task";
  private String name;
  private String days;
  private String time;
  private String audio;

  @SuppressLint("NewApi")
  public Task(String name, String days, String time, String audio) {
    this.name = name;
    this.days = days;
    this.time = time;
    this.audio = audio;
  }

  @SuppressLint("NewApi")
  public Task(String line) {
    String[] items = line.split(", ");
    this.name = items[0].trim();
    this.days = items[1].trim();
    this.time = items[2].trim();
    this.audio = items[3].trim();
  }

  public String getName() {
    return name;
  }

  public String getTime() {
    return time;
  }

  public String getDays() {
    return days;
  }

  public String getAudio() {
    return audio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task task = (Task) o;
    return Objects.equals(name, task.name) && Objects.equals(days, task.days)
        && Objects.equals(time, task.time) && Objects.equals(audio, task.audio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, days, time, audio);
  }

  @NonNull
  @Override
  public String toString() {
    return this.name + ", " + this.days + ", " + this.time + ", " + this.audio;
  }
}
