package com.example.classroomorganizerjava;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlarmManager.AlarmClockInfo;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build.VERSION_CODES;
import android.util.Log;
import androidx.annotation.RequiresApi;
import java.time.DayOfWeek;
import java.util.Calendar;

public class Alarm {

  private static final String TAG = "Alarm";
  private int id, hour, minute;
  private String mainAudio, taskAudio, nameAudio;
  private DayOfWeek[] days;

  /**
   *
   * @param info "name, hour, minute, days, mainAudio, taskAudio, nameAudio"
   */
  @SuppressLint("NewApi")
  public Alarm(String info) {
    this.id = info.hashCode();
    String[] params = info.split(",");
    this.hour = Integer.parseInt(params[1].trim());
    this.minute = Integer.parseInt(params[2].trim());
    String[] dayList = params[3].trim().split(" ");
    this.days = new DayOfWeek[dayList.length];
    for (int i = 0; i < this.days.length; i++) {
      this.days[i] = DayOfWeek.valueOf(dayList[i]);
    }
    this.mainAudio = params[4].trim().toLowerCase();
    this.taskAudio = params[5].trim().toLowerCase();
    this.nameAudio = params[6].trim().toLowerCase();
    Log.e(TAG, "Alarm created");
  }

  @RequiresApi(api = VERSION_CODES.M)
  public void schedule(Context context) {
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
    intent.putExtra("test", "bro is this going through");
    intent.putExtra("id", this.id);
    intent.putExtra("days",this.days);
    intent.putExtra("hour",this.hour);
    intent.putExtra("minute",this.minute);
    intent.putExtra("mainAudio",this.mainAudio);
    intent.putExtra("taskAudio",this.taskAudio);
    intent.putExtra("nameAudio",nameAudio);

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.set(Calendar.HOUR_OF_DAY, this.hour);
    calendar.set(Calendar.MINUTE, this.minute);

    PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, this.id, intent, 0);
    AlarmManager.AlarmClockInfo alarmInfo = new AlarmClockInfo(calendar.getTimeInMillis(), alarmPendingIntent);
    alarmManager.setAlarmClock(alarmInfo, alarmPendingIntent);
    Log.e(TAG, "Alarm scheduled for: " + calendar.getTimeInMillis());
    Log.e(TAG, "Current time is: " + System.currentTimeMillis());
    Log.e(TAG, "Upcoming alarm is: " + alarmManager.getNextAlarmClock());
    Log.e(TAG, "Alarm main audio: " + intent.getSerializableExtra("mainAudio"));
  }

  public void cancelAlarm(Context context) {
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
    PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, this.id, intent, 0);
    alarmManager.cancel(alarmPendingIntent);
  }
}
