package com.example.eyefam;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {
    public MainService() {
    }

    //private ScreenReceiver mReceiver = null;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);

        Log.e("Service", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent,flags,startId);
        boolean screenOn = intent.getBooleanExtra("screen state",false);
        if(!screenOn){
            Log.e("Screen","OFF");
        }
        else{
            startForeground(1, new Notification());
            Log.e("Screen","ON");
        }
        return START_REDELIVER_INTENT;
    }
    @Override
    public void onStart(Intent intent, int startId){
        boolean screenOn = intent.getBooleanExtra("screen state",false);
        if(!screenOn){
            Log.e("Screen","OFF");
        }
        else{
            startForeground(1, new Notification());
            Log.e("Screen","ON");
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.e("Service","onDestroy");
    }
}
