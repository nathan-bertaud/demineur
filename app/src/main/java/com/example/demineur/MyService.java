package com.example.demineur;
import android.os.Handler;
import android.os.Binder;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private Handler handler;
    private int timer = 0;
    private int count = 0;
    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
    private final MyBinder myBinder = new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("service started");
        Runnable runnable;
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(MainActivity.BROADCAST);
                intent.putExtra("timer", timer);
                sendBroadcast(intent);
                count++;
                timer++;
                if(count < 1000){
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(runnable,1000);
        return START_STICKY;
    }

}