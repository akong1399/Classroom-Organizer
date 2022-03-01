package com.example.classroomorganizerjava;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
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
    c = initializeFakeData();

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(R.layout.activity_main);

    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,
        getSupportFragmentManager());
    ViewPager viewPager = binding.viewPager;
    viewPager.setAdapter(sectionsPagerAdapter);

    button = (Button) findViewById(R.id.student_view_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, StudentViewActivity.class));
      }
    });

    button = (Button) findViewById(R.id.calendar_view_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, CalendarViewActivity.class));
      }
    });

    button = (Button) findViewById(R.id.add_student_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, AddStudentActivity.class));
      }
    });

    button = (Button) findViewById(R.id.remove_student_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, RemoveStudentActivity.class));
      }
    });

    // TODO: ??????
    Intent newTaskIntent = getIntent();
    Bundle newTask = newTaskIntent.getExtras();
    if (newTask != null) {
      try {
        c.getStudent("Kevin").addTask(newTask.getString("newTaskString"));
        c.saveFile(getApplicationContext());
        System.out.println("new task added!");
      }
      catch (Exception e) {
        Log.e(TAG, "Adding New Task Failed");
      }
    }
    else {
      Log.i(TAG, "No Task in Intent");
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private Classroom initializeFakeData() {
    Classroom heather = new Classroom(getApplicationContext());
    DayOfWeek[] oddDays = {DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY};
    DayOfWeek[] evenDays = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY};
    ArrayList<Task> tasks = new ArrayList<Task>();
    Task t1 = new Task("potty", oddDays, "09:00", "Daniel Tiger");
    Task t2 = new Task("mask", oddDays, "13:00", "Peppa Pig");
    Task t3 = new Task("potty", evenDays, "10:30", "Daniel Tiger");
    Task t4 = new Task("mask", evenDays, "14:30", "Peppa Pig");
    tasks.add(t1);
    tasks.add(t2);
    heather.addStudent(new Student("Kevin", tasks),getApplicationContext());
    tasks.clear();
    tasks.add(t3);
    tasks.add(t4);
    heather.addStudent(new Student("Caves", tasks),getApplicationContext());
    return heather;
  }

}