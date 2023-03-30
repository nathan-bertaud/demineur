package com.example.demineur;
import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demineur.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private Intent intentService;
    static public final String BROADCAST = "timer.projet";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intentService = new Intent(this, MyService.class);
        prefs = getSharedPreferences("MY_PREFS_NAME",MODE_PRIVATE);
        editor = prefs.edit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(intentService);
        update();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(BROADCAST));
        binding.imageView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO casser une case
                editor.putString("ANCIEN_SCORE", binding.timerTextview.getText().toString());
                editor.apply();
                update();
            }
        });


        binding.imageView3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO Mettre un drapeau sur la case
            }
        });
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

    private void update() {
        //TODO Faire quelque chose avec le score sauvegardé.
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                binding.timerTextview.setText(String.valueOf(bundle.getInt("timer")));
            }
        }
    };
}