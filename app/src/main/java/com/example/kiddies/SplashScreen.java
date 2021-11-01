package com.example.kiddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

//Splash screen class
public class SplashScreen extends AppCompatActivity {

//  Splash screen for 5 seconds
    private static int SPLASH_SCREEN=5000;

//  Object Declaration
    Animation topanim,bottomanim;
    ImageView logo;
    TextView appname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

//      Setting animation for splash screen logo
        topanim= AnimationUtils.loadAnimation(this,R.anim.top);

//      Setting animation for splash screen app name
        bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom);

        logo=findViewById(R.id.logo);
        appname=findViewById(R.id.appname);
        logo.setAnimation(topanim);
        appname.setAnimation(bottomanim);

//      Delaying splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//              Redirecting to MainActivity
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
            }
        },SPLASH_SCREEN);

    }
}