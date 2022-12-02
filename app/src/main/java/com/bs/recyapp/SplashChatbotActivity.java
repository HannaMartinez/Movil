package com.bs.recyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.bs.recyapp.activity.IntroActivity;
import com.bs.recyapp.activity.MainActivity;

public class SplashChatbotActivity extends AppCompatActivity {
LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_chatbot);
        lottieAnimationView = findViewById(R.id.Recibot);
        lottieAnimationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashChatbotActivity.this, Chatbot.class);
                startActivity(intent);
                finish();
            }
        }, 1500);



    }
}