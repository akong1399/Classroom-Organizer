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
  private String mainAudio;
  private String taskAudio;
  private String nameAudio;
  private Alarm alarm;

  // only called by fake data
  @SuppressLint("NewApi")
  public Task(String name, String days, String time, String mainAudio, String taskAudio, String nameAudio) {
    this.name = name.toLowerCase().trim();
    this.days = days;
    this.time = time;
    this.mainAudio = mainAudio;
    this.taskAudio = taskAudio;
    this.nameAudio = nameAudio;

    setupAlarm();
  }

  @SuppressLint("NewApi")
  public Task(String line) {
    Log.e(TAG, "New Task about to be created");
    String[] items = line.split(", ");
    try {
      this.name = items[0].toLowerCase().trim();
      this.days = items[1].trim();
      this.time = items[2].trim();
      this.mainAudio = items[3].trim();
      this.taskAudio = items[4].trim();
      this.nameAudio = items[5].trim();

      setupAlarm();

      Log.e(TAG, "Success parsing Task from string\n" + line);
    } catch (Exception e) {
      Log.e(TAG, "Unable to parse Task string\n" + line);
      Log.e(TAG, e.getMessage());
    }
  }

  private void setupAlarm() {
    String line = name + ", " +
        time.split(":")[0] + ", " +
        time.split(":")[1] + ", " +
        days + ", " +
        mainAudio + ", " +
        taskAudio + ", " +
        nameAudio;
    this.alarm = new Alarm(line);
    Log.e(TAG, "New Alarm created in Task");
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

  public Alarm getAlarm() { return alarm; }

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
        && Objects.equals(time, task.time) && Objects
        .equals(mainAudio, task.mainAudio) && Objects.equals(taskAudio, task.taskAudio)
        && Objects.equals(nameAudio, task.nameAudio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, days, time, mainAudio, taskAudio, nameAudio);
  }

  @NonNull
  @Override
  public String toString() {
    return this.name + ", " + this.days + ", " + this.time + ", " + this.mainAudio + ", " + this.taskAudio + ", " + this.nameAudio;
  }
}
