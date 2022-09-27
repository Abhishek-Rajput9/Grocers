package com.example.myapplication.Acticities;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.myapplication.databinding.ActivitySplashBinding;

public class splashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        binding.logo.animate().translationX(250).setDuration(1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startIntent =  new Intent(splashActivity.this, signupActivity.class);
                startActivity(startIntent);
                finish();
            }
        },1500);
    }
}