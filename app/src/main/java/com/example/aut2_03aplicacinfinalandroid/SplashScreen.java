package com.example.aut2_03aplicacinfinalandroid;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aut2_03aplicacinfinalandroid.MainActivity;
import com.example.aut2_03aplicacinfinalandroid.R;

public class SplashScreen extends AppCompatActivity {

    // Constante de tiempo de duración para el Splash Screen
    final static int TIEMPO = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, TIEMPO);
    }
}