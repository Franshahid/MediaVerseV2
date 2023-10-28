package com.example.myapplication;

import static android.app.PendingIntent.getActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class homeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(flags);
        setContentView(R.layout.activity_home);

//        Toolbar toolbar=findViewById(R.id.toolbar2);
//        setSupportActionBar(toolbar);
//
//        drawerLayout=findViewById(R.id.drawer_layout);
//        NavigationView navigationView=findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//
//        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
//                R.string.open_nav,R.string.close_nav);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();


        final FragmentManager[] fragmentManager = {getSupportFragmentManager()};
        FragmentTransaction fragmentTransaction = fragmentManager[0].beginTransaction();

        home hhh = new home(); // Instantiate the fragment
        fragmentTransaction.replace(R.id.fragmentContainer, hhh);
        fragmentTransaction.commit();


        final int[] h = {1};
        final int[] s = { 0 };
        final int[] l = { 0 };

        ImageView v1 = findViewById(R.id.imageView4);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });


        ImageView v2 = findViewById(R.id.imageView2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(h[0] ==0) {
                    h[0] =1;
                    s[0] =0;
                    l[0] =0;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation, R.anim.pop_enter_animation, R.anim.pop_exit_animation);

                    home hhh = new home();
                    fragmentTransaction.replace(R.id.fragmentContainer, hhh);
                    fragmentTransaction.commit();
                }
            }
        });

        ImageView v3 = findViewById(R.id.imageView3);

        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s[0] ==0) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    if(h[0]==1){
                        fragmentTransaction.setCustomAnimations(R.anim.enter2_animation, R.anim.exit2_animation, R.anim.pop_enter_animation, R.anim.pop_exit_animation);
                    }
                    else{
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
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }



}
