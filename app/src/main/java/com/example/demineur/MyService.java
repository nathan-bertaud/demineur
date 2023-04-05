package com.example.demineur;
import android.os.Handler;
import android.os.Binder;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private Handler handler;
    private int timer = 0;
    private int count = 0;
    protected boolean shutdown=false;
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

        handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(GameActivity.BROADCAST);
                intent.putExtra("timer", timer);
                sendBroadcast(intent);
                //count++;
                timer++;
                Log.d("MyService", "run: TICK");
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable,1000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        shutdown=true;
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}