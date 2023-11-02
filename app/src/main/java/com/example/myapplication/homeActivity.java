package com.example.myapplication;

import static android.app.PendingIntent.getActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import android.os.Build;
import android.graphics.Color;


public class homeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    FragmentManager fragmentManager;

    public static ImageView note;
    public static ImageView half;
    private Toolbar toolbar;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(flags);
        setContentView(R.layout.activity_home);

        half = findViewById(R.id.half);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(5000);

        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());

        half.startAnimation(rotateAnimation);

        note = findViewById(R.id.music_note);
        note.setAlpha(0);
        half.setAlpha(0);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        home hhh = new home();
        fragmentTransaction.replace(R.id.fragmentContainer, hhh);
        fragmentTransaction.commit();


        final int[] h = {1};
        final int[] s = { 0 };
        final int[] l = { 0 };


        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.bottom_home) {
                    if(h[0] ==0) {
                        h[0] = 1;
                        s[0] = 0;
                        l[0] = 0;
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation, R.anim.pop_enter_animation, R.anim.pop_exit_animation);

                        home hhh = new home();
                        fragmentTransaction.replace(R.id.fragmentContainer, hhh);
                        fragmentTransaction.commit();
                    }
                    return true;
                } else if (itemId == R.id.bottom_search) {
                        if(s[0] ==0) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                            if (h[0] == 1) {
                                fragmentTransaction.setCustomAnimations(R.anim.enter2_animation, R.anim.exit2_animation, R.anim.pop_enter_animation, R.anim.pop_exit_animation);
                            } else {
                                fragmentTransaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation, R.anim.pop_enter_animation, R.anim.pop_exit_animation);
                            }

                            h[0] = 0;
                            s[0] = 1;
                            l[0] = 0;

                            Search search = new Search();
                            fragmentTransaction.replace(R.id.fragmentContainer, search);
                            fragmentTransaction.addToBackStack("MyFragmentTag");
                            fragmentTransaction.commit();
                        }
                    return true;
                } else if (itemId == R.id.bottom_library) {
                    if(l[0] ==0) {
                        h[0] = 0;
                        s[0] = 0;
                        l[0] = 1;
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.enter2_animation, R.anim.exit2_animation, R.anim.pop_enter_animation, R.anim.pop_exit_animation);


                        LibraryFragment lll = new LibraryFragment();
                        fragmentTransaction.replace(R.id.fragmentContainer, lll);
                        fragmentTransaction.commit();
                    }
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        //for selecting particular item on navigation drawer each fragment will be displayed
        if (itemId == R.id.nav_profile) {
            Intent intent = new Intent(homeActivity.this, profileActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_settings) {
            openFragment(new SettingsFragment());
        } else if (itemId == R.id.nav_about) {
            openFragment(new AboutFragment());
        } else if (itemId == R.id.nav_logout) {
            Toast.makeText(this, "Logout Successful!", Toast.LENGTH_SHORT).show();
        }
        //closing navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void openFragment(Fragment fragment){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer,fragment);
        transaction.commit();
    }

}
