package com.example.eyefam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Taegyun on 2017-09-01.
 */

public class ScreenReceiver extends BroadcastReceiver{
    private  boolean screenOff;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("onReceive",intent.getAction());
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            screenOff = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            boolean value = prefs.getBoolean("check",true);
            Log.e("test",String.valueOf(value));
            if(!value){
                Log.e("onReceive","Suc");
            }
            else if(value){
                Log.e("onReceive","Fail");
            }
            screenOff = true;
        }
        Intent i = new Intent(context, MainService.class);
        i.putExtra("screen state", screenOff);

        if(screenOff == true){
            context.startService(i);
        }
        else {
            context.stopService(i);
        }
        //context.startService(i);
    }
}
