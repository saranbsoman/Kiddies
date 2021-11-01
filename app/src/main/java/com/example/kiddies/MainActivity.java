package com.example.kiddies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//MainActivity class
public class MainActivity extends AppCompatActivity {

    //Object declaration
    ViewPager v;
    LinearLayout sliderDotspanel;
    ViewPagerAdapter mViewPagerAdapter;

    //Declaring variables for Carousel
    private int dotscount;

    //ImageView array object
    private ImageView[] dots;
    private long backPressed;

    //Image array to store card images
    int[] images = {R.drawable.acard, R.drawable.bcard,R.drawable.ncard};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View pager Id
        v=findViewById(R.id.v);

        //Linear layout Id
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        //Calling ViewPagerAdapter constructor
        mViewPagerAdapter = new ViewPagerAdapter(this, images);

        //Adding to ViewPAger
        v.setAdapter(mViewPagerAdapter);

        //getting the count of cards
        dotscount = mViewPagerAdapter.getCount();

        dots = new ImageView[dotscount];

        try{
            for(int i = 0; i < dotscount; i++){

                dots[i] = new ImageView(this);

                //setting dot image
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

                //Layout object
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                //set margins for dots
                params.setMargins(8, 0, 8, 0);

                //adding to Layout
                sliderDotspanel.addView(dots[i], params);

            }
        }catch (ArithmeticException exception){
            Log.println(Log.WARN, "MAINACTIVITY", "error in dot count");
        }

        //setting first image as default card
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        //adding page change listener to ViewPager
        v.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                try{
                    //setting unselected card dots
                    for(int i = 0; i< dotscount; i++){
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                    }
                }catch(ArithmeticException exception){
                    Log.println(Log.WARN, "MAINACTIVITY", "error in dot count");
                }

                //setting dot color to selected card
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //function to perform when back button is pressed
    public void onBackPressed(){

        try {
            //if back button is pressed within 2 seconds app exits
            if(backPressed+ 2000 >System.currentTimeMillis()){
                super.onBackPressed();

                //clear all background activities
                finishAffinity();
                return;

            }else{
                //show toast when pressed only one time
                Toast.makeText(getBaseContext(), "Press once more to exit", Toast.LENGTH_SHORT).show();
            }

            //get system time in milliseconds
            backPressed = System.currentTimeMillis();
        }catch (ArithmeticException exception){
            Log.println(Log.WARN,"ARITHMETIC ERROR", "Error in backpressed");
        }
    }


    //onclick function of setting button
    public void settingsView(View view) {

        //navigating to Settings Activity
        Intent intent2= new Intent(getApplicationContext(),Settings.class);
        startActivity(intent2);
    }

    //onclick function of exit button
    public void exit(View view) {

       try{
           //creating an instance of AlertDialog
           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

           //creating an instance of ViewGroup
           ViewGroup viewGroup = findViewById(android.R.id.content);

           //inflate customview.xml
           View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.customview, viewGroup, false);

           //adding to builder
           builder.setView(dialogView);

           //restricting dialog from cancelling when clicked outside
           builder.setCancelable(false);

           //onclicking positive button
           builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {

                   //exits from all activities
                   finishAffinity();
               }
           });

           //onclicking negative button
           builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {

                   //cancelling dialog
                   dialog.cancel();
               }
           });

           //creating an object of AlertDialog
           AlertDialog alertDialog = builder.create();

           //display alertDialog
           alertDialog.show();
       }catch (Exception exception){
           Log.println(Log.WARN, "DIALOG BOX", "error in exit dialog");
       }

    }

    //start background music when app starts
    protected void onStart() {
        super.onStart();

        //starts service MyService
        startService(new Intent(getApplicationContext(),MyService.class));
    }

    //calls when activity destroys
    protected void onDestroy() {
        super.onDestroy();

        //stops service MyService
        stopService(new Intent(getApplicationContext(),MyService.class));
    }

    //calls when activity stops
    protected void onStop() {
        super.onStop();

        //stops service MyService
        stopService(new Intent(getApplicationContext(),MyService.class));
    }

}