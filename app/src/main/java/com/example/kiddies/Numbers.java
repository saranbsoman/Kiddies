package com.example.kiddies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


//class Numbers
public class Numbers extends AppCompatActivity {

    //pos initialised as 0
    int pos = 0;

    //RecyclerView Object
    RecyclerView recyclerView;

    //Object of NumberAdapter class
    NumberAdapter numberAdapter;

    //Array object of NumberList
    NumberList[] numberLists;

     //initialising backpressed
     long backPressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //referring recyclerView
        recyclerView = findViewById(R.id.recyclerView);

        //adding data into numberlist array
        numberLists = new NumberList[]{

                //calling NumberList constructor
                new NumberList("Zero", R.drawable.zero),
                new NumberList("One",R.drawable.one),
                new NumberList("Two",R.drawable.two),
                new NumberList("Three",R.drawable.three),
                new NumberList("Four",R.drawable.four),
                new NumberList("Five",R.drawable.five),
                new NumberList("Six",R.drawable.six),
                new NumberList("Seven",R.drawable.seven),
                new NumberList("Eight",R.drawable.eight),
                new NumberList("Nine",R.drawable.nine),
        };

        //calls NumberAdapter constructor
        numberAdapter = new NumberAdapter(numberLists,0);

        //calling method for setting adapter
        adapterSetting();


        //<--next button-->

        //when next button clicks
        findViewById(R.id.next).setOnClickListener(v->{

            try{
                //incrementing pos by 1
                pos++;

                //checking array index greater than pos
                if(pos < numberLists.length){

                    //calls NumberAdapter constructor and passing position and arraylist
                    numberAdapter = new NumberAdapter(numberLists,pos);

                    //calling method for setting adapter
                    adapterSetting();

                }else
                    //setting pos to max length
                    pos = numberLists.length;
            }catch(ArithmeticException exception){
                Log.println(Log.WARN,"NUMBERS", "error in next button");
            }

        });

        //<--next button-->


        //<--previous button-->

        //when previous button clicks
        findViewById(R.id.prev).setOnClickListener(v->{

           try{
               //decrementing pos
               pos--;

               //checking pos greater than equal to zero
               if(pos >= 0){

                   //calls NumberAdapter constructor and passing position and arraylist
                   numberAdapter = new NumberAdapter(numberLists,pos);

                   //calling method for setting adapter
                   adapterSetting();
               }else
                   //setting pos zero
                   pos = 0;
           }catch (ArithmeticException exception){
               Log.println(Log.WARN,"NUMBERS","error in previous button");
           }
        });

        //<--previous button-->

    }

    public void adapterSetting(){

        //setting LinerLayout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //adding to adapter
        recyclerView.setAdapter(numberAdapter);
    }

    //method for Home button
    public void backHome(View view) {

        //navigating to MainActivity
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
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