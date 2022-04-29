package com.example.classroomorganizerjava;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {

  private static final String TAG = "AddStudentActivity";
  private Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_student);

//    // Get the Intent that started this activity and extract the string
////    Intent intent = getIntent();
//    String message = "Page for adding students and tasks";
//
//    // Capture the layout's TextView and set the string as its text
//    TextView textView = findViewById(R.id.add_student_textview);
//    textView.setText(message);

    Spinner spinnerAudios = findViewById(R.id.audio_menu_spinner);
    Spinner spinnerAudios2 = findViewById(R.id.audio_menu_spinner2);
    Spinner spinnerAudios3 = findViewById(R.id.audio_menu_spinner3);
//    ArrayAdapter<CharSequence> adapter = ArrayAdapter
//        .createFromResource(this, R.array.Audios, android.R.layout.simple_spinner_item);
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//    spinnerAudios.setAdapter(adapter);

    RadioButton mondayRadioButton = (RadioButton) findViewById(
        R.id.monday_radioButton); // Initiate Monday radio button
    final Boolean[] mondayRadioButtonState = {
        mondayRadioButton.isChecked()}; // Check current state of a radio button (true or false).
    mondayRadioButton.setOnClickListener(view -> {
      if (mondayRadioButtonState[0] == true) {
        mondayRadioButton.setChecked(false);
        mondayRadioButtonState[0] = false;
        //write code for when button is unchecked
      } else {
        mondayRadioButtonState[0] = true;
        mondayRadioButton.setChecked(true);
        //write code for when button is checked
      }
    });

    RadioButton tuesdayRadioButton = (RadioButton) findViewById(
        R.id.tuesday_radioButton); // Initiate Monday radio button
    final Boolean[] tuesdayRadioButtonState = {
        tuesdayRadioButton.isChecked()}; // Check current state of a radio button (true or false).
    tuesdayRadioButton.setOnClickListener(view -> {
      if (tuesdayRadioButtonState[0] == true) {
        tuesdayRadioButton.setChecked(false);
        tuesdayRadioButtonState[0] = false;
        //write code for when button is unchecked
      } else {
        tuesdayRadioButtonState[0] = true;
        tuesdayRadioButton.setChecked(true);
        //write code for when button is checked
      }
    });

    RadioButton wednesdayRadioButton = (RadioButton) findViewById(
        R.id.wednesday_radioButton); // Initiate Monday radio button
    final Boolean[] wednesdayRadioButtonState = {
        wednesdayRadioButton.isChecked()}; // Check current state of a radio button (true or false).
    wednesdayRadioButton.setOnClickListener(view -> {
      if (wednesdayRadioButtonState[0] == true) {
        wednesdayRadioButton.setChecked(false);
        wednesdayRadioButtonState[0] = false;
        //write code for when button is unchecked
      } else {
        wednesdayRadioButtonState[0] = true;
        wednesdayRadioButton.setChecked(true);
        //write code for when button is checked
      }
    });

    RadioButton thursdayRadioButton = (RadioButton) findViewById(
        R.id.thursday_radioButton); // Initiate Monday radio button
    final Boolean[] thursdayRadioButtonState = {
        thursdayRadioButton.isChecked()}; // Check current state of a radio button (true or false).
    thursdayRadioButton.setOnClickListener(view -> {
      if (thursdayRadioButtonState[0] == true) {
        thursdayRadioButton.setChecked(false);
        thursdayRadioButtonState[0] = false;
        //write code for when button is unchecked
      } else {
        thursdayRadioButtonState[0] = true;
        thursdayRadioButton.setChecked(true);
        //write code for when button is checked
      }
    });

    RadioButton fridayRadioButton = (RadioButton) findViewById(
        R.id.friday_radioButton); // Initiate Monday radio button
    final Boolean[] fridayRadioButtonState = {
        fridayRadioButton.isChecked()}; // Check current state of a radio button (true or false).
    fridayRadioButton.setOnClickListener(view -> {
      if (fridayRadioButtonState[0] == true) {
        fridayRadioButton.setChecked(false);
        fridayRadioButtonState[0] = false;
        //write code for when button is unchecked
      } else {
        fridayRadioButtonState[0] = true;
        fridayRadioButton.setChecked(true);
        //write code for when button is checked
      }
    });

    button = (Button) findViewById(R.id.save_task_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(AddStudentActivity.this, AddStudentActivity.class));

        EditText studentName = findViewById(R.id.student_name_EditText);
        EditText nameView = findViewById(R.id.task_name_EditText);
        EditText timeView = findViewById(R.id.task_time_TextTime);
        RadioButton[] days = new RadioButton[]{mondayRadioButton, tuesdayRadioButton,
            wednesdayRadioButton, thursdayRadioButton, fridayRadioButton};
        Map<String,String> fullDays = new HashMap<>();
        fullDays.put("M","MONDAY");
        fullDays.put("Tu","TUESDAY");
        fullDays.put("W","WEDNESDAY");
        fullDays.put("Th","THURSDAY");
        fullDays.put("F","FRIDAY");

        try {
          String task = studentName.getText().toString() + "\n";
          task += nameView.getText().toString() + ", ";
          for (RadioButton rb : days) {
            if (rb.isChecked()){
              task += fullDays.get(rb.getText().toString()) + " ";
            }
          }
          task += ", " + timeView.getText().toString() + ", ";
          task += spinnerAudios.getSelectedItem().toString() + ", ";
          task += spinnerAudios2.getSelectedItem().toString() + ", ";
          task += spinnerAudios3.getSelectedItem().toString();
          Intent i = new Intent(AddStudentActivity.this, MainActivity.class);
          i.putExtra("newTaskString",task);
          Log.i(TAG, "New Task here: " + task);
          startActivity(i);
        } catch (Exception e) {
          Log.e(TAG, "Problem adding new task - task couldn't be created\n" + e);
        }
      }
    });

    button = findViewById(R.id.deleteStudentButton);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(AddStudentActivity.this, AddStudentActivity.class));
        try {
          EditText studentName = findViewById(R.id.student_name_EditText);
          Intent i = new Intent(AddStudentActivity.this, MainActivity.class);
          i.putExtra("deleteStudentName",studentName.getText().toString());
          startActivity(i);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}