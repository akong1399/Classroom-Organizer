package com.example.classroomorganizerjava;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

  private static final String TAG = "AlarmBroadcastReceiver";

  @Override
  public void onReceive(Context context, Intent intent) {
    if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
      Log.e(TAG, "AlarmBR: Intent ACTION_BOOT_COMPLETED received");
      startRescheduleAlarmsService(context);
    }
    else if (alarmIsToday(intent)){
      Log.e(TAG, "AlarmBR is today!");
      startAlarmService(context, intent);
    }
  }

  @SuppressLint("NewApi")
  private boolean alarmIsToday(Intent intent) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    DayOfWeek[] daysArray = (DayOfWeek[]) intent.getSerializableExtra("days");
    List<DayOfWeek> days = Arrays.asList(daysArray);
    int today = calendar.get(Calendar.DAY_OF_WEEK);

    switch(today) {
      case Calendar.MONDAY:
        if (days.contains(DayOfWeek.MONDAY))
          return true;
        return false;
      case Calendar.TUESDAY:
        if (days.contains(DayOfWeek.TUESDAY))
          return true;
        return false;
      case Calendar.WEDNESDAY:
        if (days.contains(DayOfWeek.WEDNESDAY))
          return true;
        return false;
      case Calendar.THURSDAY:
        if (days.contains(DayOfWeek.THURSDAY))
          return true;
        return false;
      case Calendar.FRIDAY:
        if (days.contains(DayOfWeek.FRIDAY))
          return true;
        return false;
      case Calendar.SATURDAY:
        if (days.contains(DayOfWeek.SATURDAY))
          return true;
        return false;
      case Calendar.SUNDAY:
        if (days.contains(DayOfWeek.SUNDAY))
          return true;
        return false;
    }
    return false;
  }

  private void startAlarmService(Context context, Intent intent) {
    Intent intentService = new Intent(context, AlarmService.class);
    intentService.putExtra("mainAudio", intent.getSerializableExtra("mainAudio"));
    intentService.putExtra("taskAudio", intent.getSerializableExtra("taskAudio"));
    intentService.putExtra("nameAudio", intent.getSerializableExtra("nameAudio"));
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      context.startForegroundService(intentService);
      Log.e(TAG, "AlarmBR starting service");
    } else {
      Log.e(TAG, "AlarmBR starting service");
      context.startService(intentService);
    }
  }

  private void startRescheduleAlarmsService(Context context) {
    Log.e(TAG, "Restart Alarm Service started");
    Intent intentService = new Intent(context, RescheduleAlarmsService.class);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      context.startForegroundService(intentService);
    } else {
      context.startService(intentService);
    }
  }
}
