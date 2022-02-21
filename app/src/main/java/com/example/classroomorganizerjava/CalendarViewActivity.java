package com.example.classroomorganizerjava;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CalendarViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = "Page for calendar view";

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.calendar_textview);
        textView.setText(message);
    }
}