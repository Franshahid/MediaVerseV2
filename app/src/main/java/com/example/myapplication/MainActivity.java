package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_MEDIA_AUDIO},123);
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);

        View decorView = getWindow().getDecorView();
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(flags);
        setContentView(R.layout.activity_main);

//        requestPermission();

        TextView textView = findViewById(R.id.textView2);

        Animation increaseAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_increase);
        Animation decreaseAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_decrease);

// Set up animation listeners
        increaseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // Start the decrease animation when the increase animation ends
                textView.startAnimation(decreaseAnimation);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        decreaseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // Set the text view's alpha to 0 when the decrease animation ends
                textView.setAlpha(0);
                logIN();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

// Start the increase animation
        textView.startAnimation(increaseAnimation);




    }

    protected void logIN() {
        Intent intent = new Intent(this, logInActivity.class);
        startActivity(intent);
    }
}
