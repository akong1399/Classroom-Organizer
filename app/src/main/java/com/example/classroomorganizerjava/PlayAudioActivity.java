package com.example.classroomorganizerjava;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.classroomorganizerjava.databinding.ActivityPlayAudioBinding;

public class PlayAudioActivity extends AppCompatActivity {

  private static final String TAG = "PlayAudioActivity";
  private AppBarConfiguration appBarConfiguration;
  private ActivityPlayAudioBinding binding;
//  private boolean mediaPlayerActive;
  private MediaPlayer mp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityPlayAudioBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    Spinner universalAudioSpinner = findViewById(R.id.universal_audio_spinner);
    Spinner nameAudioSpinner = findViewById(R.id.name_audio_spinner);
    Spinner taskAudioSpinner = findViewById(R.id.task_audio_spinner);

    ArrayAdapter<CharSequence> universalAudio = ArrayAdapter
        .createFromResource(this, R.array.universal_audios, android.R.layout.simple_spinner_item);
    universalAudio.setDropDownViewResource(android.R.layout.simple_spinner_item);
    universalAudioSpinner.setAdapter(universalAudio);

    ArrayAdapter<CharSequence> nameAudio = ArrayAdapter
        .createFromResource(this, R.array.name_audios, android.R.layout.simple_spinner_item);
    nameAudio.setDropDownViewResource(android.R.layout.simple_spinner_item);
    nameAudioSpinner.setAdapter(nameAudio);

    ArrayAdapter<CharSequence> taskAudio = ArrayAdapter
        .createFromResource(this, R.array.task_audios, android.R.layout.simple_spinner_item);
    taskAudio.setDropDownViewResource(android.R.layout.simple_spinner_item);
    taskAudioSpinner.setAdapter(taskAudio);

    Button button = findViewById(R.id.playAudioButton);
    button.setOnClickListener(view -> {
      Log.i(TAG,"audio button clicked");
      String universalAudioName = universalAudioSpinner.getSelectedItem().toString();
      String nameAudioName = nameAudioSpinner.getSelectedItem().toString();
      String taskAudioName = taskAudioSpinner.getSelectedItem().toString();

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
    });

    button = findViewById(R.id.backPlayAudio);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(PlayAudioActivity.this, MainActivity.class));
      }
    });

  }

  private void playAudioFile(String file) {
    try {
      mp=MediaPlayer.create(getApplicationContext(),R.raw.class.getDeclaredField(file).getInt(file));
    } catch (Exception e) {
      Log.e(TAG,"Media Player failed: " + e);
      return;
    }
    mp.start();
  }
}