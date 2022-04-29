package com.example.classroomorganizerjava;

import android.content.Intent;
import android.os.IBinder;
import androidx.lifecycle.LifecycleService;
import java.util.List;

public class RescheduleAlarmsService extends LifecycleService {
  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    super.onStartCommand(intent, flags, startId);

    // TODO: load alarms from txt and recreate

    return START_STICKY;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent intent) {
    super.onBind(intent);
    return null;
  }
}