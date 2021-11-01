package com.example.kiddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

//class bird
public class Bird1 extends AppCompatActivity {

    //creating objects of ImageView for 3 birds
    ImageView img,img2,img3;

    //image object for next and previous button
    ImageView prebtn,nextbtn;

    //textview objects for birds names
    TextView tv ,tv2,tv3;

    //initialise count as zero
    int count=0;

    //initialising backpressed
    long backPressed = 0;

    //MediaPlayer object
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird1);

        //adding audio to MediaPlayer object
        mediaPlayer = MediaPlayer.create(this, R.raw.spellduck);

        //spell animal name
        mediaPlayer.start();

        //referring textView
        tv=(TextView) findViewById(R.id.duck);
        tv2=(TextView)findViewById(R.id.crow);
        tv3=findViewById(R.id.rooster);

        //referring images in xml
        img=(ImageView) findViewById(R.id.img1);
        img2=(ImageView) findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);

        //referring navigation buttons
        prebtn=(ImageView) findViewById(R.id.prev);
        nextbtn=(ImageView)findViewById(R.id.next);

        //setting visibilities to view
        img2.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
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

    //onClick method to go homePage
    public void home2(View view) {

        try{
            //stop if playing audio
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
        }catch (Exception exception){
            Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
        }

        //navigating to home page
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    //onClick method for next button
    public void next(View view) {

        //incrementing count by one
        count++;

        //select corresponding bird
        switch (count){
            case 1:
                //showing next image and name using visibility
                img2.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);

                try{
                    //stop audio if playing
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }
                }catch (Exception exception){
                    Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
                }

                //animate slideout image and name of duck
                YoYo.with(Techniques.SlideOutLeft).duration(1500).repeat(0).playOn(img);
                YoYo.with(Techniques.SlideOutLeft).duration(200).repeat(0).playOn(tv);

                //animate slidein image and name of crow
                YoYo.with(Techniques.SlideInRight).duration(1500).repeat(0).playOn(img2);
                YoYo.with(Techniques.SlideInRight).duration(200).repeat(0).playOn(tv2);

                try{
                    //spell crow
                    mediaPlayer=MediaPlayer.create(this,R.raw.spellcrow);
                    mediaPlayer.start();
                }catch (Exception exception){
                    Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
                }

                //show the previous button
                prebtn.setVisibility(View.VISIBLE);
                break;

            case 2:
                //make the image and name of rooster visible
                img3.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);

                //animate slideout image and name of crow
                YoYo.with(Techniques.SlideOutLeft).duration(1500).repeat(0).playOn(img2);
                YoYo.with(Techniques.SlideOutLeft).duration(1500).repeat(0).playOn(tv2);

                //animate slidein image and name of rooster
                YoYo.with(Techniques.SlideInRight).duration(1500).repeat(0).playOn(img3);
                YoYo.with(Techniques.SlideInRight).duration(1500).repeat(0).playOn(tv3);

                try{
                    //stop audio if playing
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }

                    //spell rooster
                    mediaPlayer=MediaPlayer.create(this,R.raw.spellrooster);
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

        //decrement count
        count--;

        //select corresponding birds
        switch (count){

            //show duck
            case 0:

                //make previous button hidden
                prebtn.setVisibility(View.INVISIBLE);

                //animate slide out crow
                YoYo.with(Techniques.SlideOutRight).duration(1500).repeat(0).playOn(img2);
                YoYo.with(Techniques.SlideOutRight).duration(1500).repeat(0).playOn(tv2);

                //animate slide in duck
                YoYo.with(Techniques.SlideInLeft).duration(1500).repeat(0).playOn(img);
                YoYo.with(Techniques.SlideInLeft).duration(1500).repeat(0).playOn(tv);

                try{
                    //stop audio if playing
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }

                    //spell duck
                    mediaPlayer = MediaPlayer.create(this, R.raw.spellduck);
                    mediaPlayer.start();
                }catch (Exception exception){
                    Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
                }

                //make next button visible
                nextbtn.setVisibility(View.VISIBLE);
                break;

            //display crow
            case 1:
                //animate slide out rooster
                YoYo.with(Techniques.SlideOutRight).duration(1500).repeat(0).playOn(img3);
                YoYo.with(Techniques.SlideOutRight).duration(1500).repeat(0).playOn(tv3);

                //animate slide in crow
                YoYo.with(Techniques.SlideInLeft).duration(1500).repeat(0).playOn(img2);
                YoYo.with(Techniques.SlideInLeft).duration(1500).repeat(0).playOn(tv2);

                try{
                    //stop media if playing
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }

                    //spell crow
                    mediaPlayer = MediaPlayer.create(this, R.raw.spellcrow);
                    mediaPlayer.start();
                }catch (Exception exception){
                    Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
                }

                //make next button visible
                nextbtn.setVisibility(View.VISIBLE);
                break;
        }
    }
    //method onClick duck image
    public void animationDuck(View view) {

        try{
            //stop audio if playing
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }

            //animation to duck image
            YoYo.with(Techniques.Tada).duration(1500).repeat(0).playOn(img);

            //play duck sound
            mediaPlayer=MediaPlayer.create(this,R.raw.ducksound);
            mediaPlayer.start();
        }catch (Exception exception){
            Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
        }
    }

    //method onClick crow image
    public void animationCrow(View view) {

        try{
            //stop audio if playing
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }

            //animation to crow image
            YoYo.with(Techniques.Tada).duration(1500).repeat(0).playOn(img2);

            //play crow sound
            mediaPlayer=MediaPlayer.create(this,R.raw.crow);
            mediaPlayer.start();
        }catch (Exception exception){
            Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
        }
    }

    //method onClick rooster image
    public void animationRooster(View view) {

        try{
            //stop audio if playing
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }

            //animation to rooster image
            YoYo.with(Techniques.Tada).duration(1500).repeat(0).playOn(img3);

            //play rooster sound
            mediaPlayer=MediaPlayer.create(this,R.raw.rooster);
            mediaPlayer.start();
        }catch (Exception exception){
            Log.println(Log.WARN,"AUDIO ERROR", "error in audio");
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