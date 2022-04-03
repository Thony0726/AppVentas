package com.example.appventas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Activity_splash_interfaz extends AppCompatActivity {
    ImageView splashImg;
    TextView appName, textViewFrom, textViewAPP;
    LottieAnimationView lottieAnimationView;
//    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_interfaz);
        /*FIREBASE AUTH*/
//        mAuth = FirebaseAuth.getInstance();

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        appName = findViewById(R.id.app_name);
        textViewAPP = findViewById(R.id.textViewAPP);
        textViewFrom = findViewById(R.id.textViewFrom);

        splashImg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);

        splashImg.animate().translationY(-2500).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        textViewAPP.animate().translationY(-2500).setDuration(1000).setStartDelay(4000);
        textViewFrom.animate().translationY(-2500).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Activity_splash_interfaz.this, ActivityLogin_interfaz.class));
                finish();
            }
        }, 5000);
    }
}