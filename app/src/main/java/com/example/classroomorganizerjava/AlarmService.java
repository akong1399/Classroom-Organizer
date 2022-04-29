package com.example.classroomorganizerjava;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {

  private static final String TAG = "AlarmService";
  private MediaPlayer mp;
  private Vibrator vibrator;

  @Override
  public void onCreate() {
    super.onCreate();
    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    Log.e(TAG, "AlarmService created!");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.e(TAG, "AlarmService onStartCommand started!");
    Intent notificationIntent = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

    String alarmTitle = String.format("%s Alarm", intent.getStringExtra("nameAudio"));

    Notification notification = new NotificationCompat.Builder(this, "ALARM_SERVICE_CHANNEL")
        .setContentTitle(alarmTitle)
        .setContentText("Ring Ring .. Ring Ring")
        .setContentIntent(pendingIntent)
        .build();

    Log.e(TAG, "Received Intent info: " + intent.getSerializableExtra("test"));

    Log.e(TAG, "Playing alarm audio!");
    playAudio(intent);

    long[] pattern = { 0, 100, 1000 };
    vibrator.vibrate(pattern, 0);

    startForeground(1, notification);

    return START_STICKY;
  }

  private void playAudio(Intent intent) {
    String universalAudioName = (String) intent.getSerializableExtra("mainAudio");
    String taskAudioName = (String) intent.getSerializableExtra("taskAudio");
    String nameAudioName = (String) intent.getSerializableExtra("nameAudio");

    if (!universalAudioName.equals("none")) {
      Log.i(TAG,"universal audio initiated");
      while(mp != null && mp.isPlaying()) {
        continue;
      }
      playAudioFile(universalAudioName);
      Log.i(TAG,"universal audio succeeded");
    }
    if (!taskAudioName.equals("none")) {
      Log.i(TAG,"task task initiated");
      while(mp != null && mp.isPlaying()) {
        continue;
      }
      playAudioFile(taskAudioName);
      Log.i(TAG,"task audio succeeded");
    }
    if (!nameAudioName.equals("none")) {
      Log.i(TAG,"name audio initiated");
      while(mp != null && mp.isPlaying()) {
        continue;
      }
      playAudioFile(nameAudioName);
      Log.i(TAG,"name audio succeeded");
    }
  }

  private void playAudioFile(String file) {
    try {
      mp = MediaPlayer
          .create(getApplicationContext(), R.raw.class.getDeclaredField(file).getInt(file));
    } catch (Exception e) {
      Log.e(TAG, "Media Player failed: " + e);
      return;
    }
    mp.start();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();

    mp.stop();
    vibrator.cancel();
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
