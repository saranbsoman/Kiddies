package com.example.kiddies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

//class settings
public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //spinner object
    Spinner spinner;

    //creating object for Timer class
    Timer timer=new Timer();

    //initialising backpressed
    long backPressed = 0;

    //initialising timers with data need to be in the spinner
    private static final String[] timers = {"Choose timer", "10 seconds", "15 seconds", "20 seconds"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //referring to spinner view
        spinner = (Spinner) findViewById(R.id.spinner);

        //spinner
        ArrayAdapter<String> adapter =new ArrayAdapter<>(Settings.this, android.R.layout.simple_spinner_item, timers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //adding values to spinner
        spinner.setAdapter(adapter);

        //spinner listener
        spinner.setOnItemSelectedListener(this);

    }

    //methods to work whenever spinner is selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        //spinner value selection
        switch (position) {

            //first spinner value
            case 0:
                //calls timer to simply return
                timer.onExit(0);
                break;

                //10 seconds
            case 1:

                //calls timer function which exits in 5 seconds
                timer.onExit(10000);

                //calls to show timer alert
                onExitReminder(5000);
                break;
            case 2:

                //calls timer function which exits in 5 seconds
                onExitReminder(10000);

                //calls to show timer alert
                timer.onExit(15000);
                break;
            case 3:

                //calls timer function which exits in 5 seconds
                onExitReminder(15000);

                //calls to show timer alert
                timer.onExit(20000);
                break;

        }

    }

    //alerts for exit
    public void onExitReminder(int time){

        //delaying toast
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Exits in 5 seconds", Toast.LENGTH_SHORT).show();
            }
        }, time);
        return;
    }

    //method onClick about text
    public void newAbout(View view) {

        //creating object of fragment
        Fragment2 fragment = new Fragment2();

        //showing fragment content
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment).commit();
    }

    //method onClick home button to go homePage
    public void home2(View view){
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //function to perform when back button is pressed
    public void onBackPressed(){

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
    }

}