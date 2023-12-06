package com.stuarddevapps.scancam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    public final String VERSION_NAME = BuildConfig.VERSION_NAME;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = findViewById(R.id.VersionName);
        textView.setText("Version " + VERSION_NAME);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };
        Timer time = new Timer();
        time.schedule(tarea, 3000);


    }
}