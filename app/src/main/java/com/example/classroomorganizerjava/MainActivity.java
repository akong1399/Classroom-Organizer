package com.example.classroomorganizerjava;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

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

import java.time.DayOfWeek;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE = "Testing";
  private static final String TAG = "MainActivity";
  private ActivityMainBinding binding;
  private Button button;
  private Classroom c;

  @RequiresApi(api = Build.VERSION_CODES.O)
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
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, AddStudentActivity.class));
      }
    });

    button = findViewById(R.id.playAudioPage);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, PlayAudioActivity.class));
      }
    });

    TextView classroomTextView = findViewById(R.id.classroomTextView);
    classroomTextView.setText(c.toString());
  }

  private void mediaPlayer() {
    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.potty_song);
    mediaPlayer.start(); // no need to call prepare(); create() does that for you
  }

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
          s.addTask(data.split("\n")[1]);
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
}