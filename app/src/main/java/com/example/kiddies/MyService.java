package com.example.kiddies;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;


//service class for background music
public class MyService extends Service {
    public MyService() {
    }

    //created an object of MediaPlayer
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
    return null;
    }

    //starts when service starts
    public int onStartCommand(Intent intent,int flags, int startId){

        try{
            //creating mediaplayer using audio from raw file
            player = MediaPlayer.create(this,R.raw.bgm);
        }catch (Exception e){
            Log.println(Log.WARN,"FILE EXCEPTION", "error in music play");

        }

        //continuing the audio
        player.setLooping(true);

        //starts audio
        player.start();

        return START_STICKY;
    }


    //calls when activity destroys
    public void onDestroy(){
        super.onDestroy();

        //stops audio
        player.stop();
    }

}