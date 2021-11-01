package com.example.kiddies;

import android.os.Handler;
import android.util.Log;

//class timer for exiting app
public class Timer {

    //initialisng value for TAG
    String TAG = "ERROR";

    //handler object
    Handler handler = new Handler();

    //method for exiting from app
    public void onExit(int time){

        try{
            //when user choose default
            if(time == 0){
                return;
            }else {
                //delaying method
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //exits from app
                        System.exit(0);

                    }
                }, time);
            }
        }catch (Exception e){
            Log.println(Log.ASSERT,TAG,"error in timer");
        }
    }

}
