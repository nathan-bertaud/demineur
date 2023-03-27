package com.example.demineur;
import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demineur.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private Intent intentService;
    static public final String BROADCAST = "timer.projet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intentService = new Intent(this, MyService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("starting service");
        startService(intentService);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(BROADCAST));
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(intentService);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("received");
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                System.out.println("Timer value : " + String.valueOf(bundle.getInt("timer")));
                binding.timerTextview.setText(String.valueOf(bundle.getInt("timer")));
            }
        }
    };
}