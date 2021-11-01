package com.example.kiddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.security.spec.ECField;

//class Animal1
public class Animal1 extends AppCompatActivity {

    //creating objects of ImageView for 3 animals
    ImageView img,img2,img3;

    //image object for next and previous button
    ImageView prebtn,nextbtn;

    //textview objects for animal names
    TextView tv,tv2,tv3;

    //declaring count variable for ..
    int count=0;

    //initialising backpressed
    long backPressed = 0;

    //MediaPlayer object
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal1);

        //adding audio to MediaPlayer object
        mediaPlayer = MediaPlayer.create(this, R.raw.dee);

        //spell animal name
        mediaPlayer.start();

        //referring textView
        tv=(TextView) findViewById(R.id.dog);
        tv2=(TextView)findViewById(R.id.cat);
        tv3=(TextView)findViewById(R.id.cow);

        //referring images in xml
        img=(ImageView) findViewById(R.id.img1);
        img2=(ImageView) findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);

        //referring navigation buttons
        prebtn=(ImageView) findViewById(R.id.prev);
        nextbtn=(ImageView)findViewById(R.id.next);

        //setting visibilities to view
        img2.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        prebtn.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }


    //method of home button
    public void home2(View view) {

        //stops audio while moving to home page
        mediaPlayer.stop();

        try{
            //navigating to home page
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Log.println(Log.ASSERT,"INTENT ERROR","error in animal activity");
        }

    }

    //method for next button
    public void next(View view) {

        //incrementing count by one
        count++;

        //select corresponding animal
        switch (count){
            case 1:

                //showing next image using visibility
                img2.setVisibility(View.VISIBLE);

                //showing animal name using visibility
                tv2.setVisibility(View.VISIBLE);

                try{
                    //stop audio if playing
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }
                }catch (Exception exception){
                    Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
                }

                //animate slideout image and name of dog
                YoYo.with(Techniques.SlideOutLeft).duration(1500).repeat(0).playOn(img);
                YoYo.with(Techniques.SlideOutLeft).duration(200).repeat(0).playOn(tv);

                //animate slidein image and name of cat
                YoYo.with(Techniques.SlideInRight).duration(1500).repeat(0).playOn(img2);
                YoYo.with(Techniques.SlideInRight).duration(200).repeat(0).playOn(tv2);

                try{
                    //spell cat
                    mediaPlayer=MediaPlayer.create(this,R.raw.cat);
                    mediaPlayer.start();
                }catch (Exception exception){
                    Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
                }

                //show the previous button
                prebtn.setVisibility(View.VISIBLE);
                break;

            case 2:
                //make the image and name of cow visible
                img3.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);

                //animate slideout image and name of cat
                YoYo.with(Techniques.SlideOutLeft).duration(1500).repeat(0).playOn(img2);
                YoYo.with(Techniques.SlideOutLeft).duration(1500).repeat(0).playOn(tv2);

                //animate slidein image and name of cow
                YoYo.with(Techniques.SlideInRight).duration(1500).repeat(0).playOn(img3);
                YoYo.with(Techniques.SlideInRight).duration(1500).repeat(0).playOn(tv3);

                try{
                    //stop audio if playing
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }

                    //spell cow
                    mediaPlayer=MediaPlayer.create(this,R.raw.cowspell);
                    mediaPlayer.start();
                }catch (Exception exception){
                    Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
                }

                //make next button invisible
                nextbtn.setVisibility(View.INVISIBLE);
                break;
        }
    }

    //method for previous button
    public void previous(View view) {

        //decrement the count
        count--;

       //select corresponding animal
        switch (count){

            //display dog
            case 0:

                //make previous button hidden
                prebtn.setVisibility(View.INVISIBLE);

                //slide out cat
                YoYo.with(Techniques.SlideOutRight).duration(1500).repeat(0).playOn(img2);
                YoYo.with(Techniques.SlideOutRight).duration(1500).repeat(0).playOn(tv2);

                //slide in dog
                YoYo.with(Techniques.SlideInLeft).duration(1500).repeat(0).playOn(img);
                YoYo.with(Techniques.SlideInLeft).duration(1500).repeat(0).playOn(tv);

                try{
                    //stop audio if playing
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }

                    //spell dog
                    mediaPlayer = MediaPlayer.create(this, R.raw.dee);
                    mediaPlayer.start();
                }catch (Exception exception){
                    Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
                }

                break;

            //display cat
            case 1:

                //slide out cow
                YoYo.with(Techniques.SlideOutRight).duration(1500).repeat(0).playOn(img3);
                YoYo.with(Techniques.SlideOutRight).duration(1500).repeat(0).playOn(tv3);

                //slide in cat
                YoYo.with(Techniques.SlideInLeft).duration(1500).repeat(0).playOn(img2);
                YoYo.with(Techniques.SlideInLeft).duration(1500).repeat(0).playOn(tv2);

                try{
                    //stop if playing
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }

                    //spell cat
                    mediaPlayer=MediaPlayer.create(this,R.raw.cat);
                    mediaPlayer.start();
                }catch (Exception exception){
                    Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
                }

                //make next button visible
                nextbtn.setVisibility(View.VISIBLE);
                break;
        }
    }

    //method onClick dog image
    public void animationDog(View view) {

        try{
            //stop audio if playing
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }

            //animation to dog image
            YoYo.with(Techniques.Tada).duration(1500).repeat(0).playOn(img);

            //play sound of dog
            mediaPlayer=MediaPlayer.create(this,R.raw.dog1);
            mediaPlayer.start();
        }catch (Exception exception){
            Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
        }
    }

    //method onClick cat image
    public void animationCat(View view) {
        try{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }

            //animation to cat image
            YoYo.with(Techniques.Tada).duration(1500).repeat(0).playOn(img2);

            //play sound of cat
            mediaPlayer=MediaPlayer.create(this,R.raw.meow);
            mediaPlayer.start();
        }catch (Exception exception){
            Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
        }
    }

    //method onClick cow image
    public void animationCow(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        //animation to cow image
        YoYo.with(Techniques.Tada).duration(1500).repeat(0).playOn(img3);

        try{
            //play sound of cow
            mediaPlayer = MediaPlayer.create(this, R.raw.cowmoo);
            mediaPlayer.start();
        }catch (Exception e){
            Log.println(Log.WARN,"RAW FILE EXCEPTION", "error in music play");

        }

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

}