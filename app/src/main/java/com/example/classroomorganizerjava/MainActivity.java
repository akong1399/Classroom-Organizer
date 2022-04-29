package com.example.classroomorganizerjava;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.classroomorganizerjava.ui.main.SectionsPagerAdapter;
import com.example.classroomorganizerjava.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE = "Testing";
  private static final String TAG = "MainActivity";
  private ActivityMainBinding binding;
  private Button button;
  private Classroom c;

  @RequiresApi(api = VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    c = new Classroom(getApplicationContext());
    getNewTaskIntent();
    deleteStudentIntent();

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(R.layout.activity_main);

    button = (Button) findViewById(R.id.fakeDataButton);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        c.initializeFakeData(getApplicationContext());
        startActivity(new Intent(MainActivity.this, MainActivity.class));
      }
    });

    button = (Button) findViewById(R.id.add_student_button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, AddStudentActivity.class));
      }
    });

    button = findViewById(R.id.playAudioPage);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, PlayAudioActivity.class));
      }
    });

    button = findViewById(R.id.recordAudioPage);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, RecordAudioActivity.class));
      }
    });

    TextView classroomTextView = findViewById(R.id.classroomTextView);
    classroomTextView.setText(c.toString());

    createNotificationChannnel();

    AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
    Log.e(TAG, "Next exact alarm status: " + alarmManager.getNextAlarmClock());

  }

  @RequiresApi(api = VERSION_CODES.M)
  private void getNewTaskIntent() {
    Intent newTaskIntent = getIntent();
    Bundle newTask = newTaskIntent.getExtras();
    if (newTask != null) {
      try {
        String data = newTask.getString("newTaskString");
        if (data.split("\n")[0].equals("")) {
          Log.w(TAG,"No Student Name was Given");
          return;
        }
        Student s = c.getStudent(data.split("\n")[0]);
        if (s != null) {
          Alarm alarm = s.addTask(data.split("\n")[1]);
          if (alarm != null){
            alarm.schedule(getApplicationContext());
          }
        } else {
          c.addStudent(new Student(data), getApplicationContext());
        }
        Log.i(TAG,"Add New Task Success");
        c.saveFile(getApplicationContext());
      }
      catch (Exception e) {
        Log.e(TAG, "Adding New Task Failed\n" + e);
      }
    }
    else {
      Log.i(TAG, "No Task in Intent");
    }
  }

  private void deleteStudentIntent() {
    Intent newTaskIntent = getIntent();
    Bundle newTask = newTaskIntent.getExtras();
    if (newTask != null) {
      try {
        String data = newTask.getString("deleteStudentName");
        c.removeStudent(data, getApplicationContext());
        Log.i(TAG,"Delete Student Success");
        c.saveFile(getApplicationContext());
      }
      catch (Exception e) {
        Log.e(TAG, "Delete Student Fail\n" + e);
      }
    }
    else {
      Log.i(TAG, "No Student Name Intent");
    }
  }

  @RequiresApi(api = VERSION_CODES.O)
  private void createNotificationChannnel() {
    try {
      NotificationChannel serviceChannel = new NotificationChannel(
          "ALARM_SERVICE_CHANNEL",
          "Alarm Service Channel",
          NotificationManager.IMPORTANCE_DEFAULT
      );

      NotificationManager manager = getSystemService(NotificationManager.class);
      manager.createNotificationChannel(serviceChannel);
    } catch (Exception e) {
      Log.e(TAG, "createNotificationChannel() failed");
    }
  }
}