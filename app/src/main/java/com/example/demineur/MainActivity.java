package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.demineur.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.button.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this,Options.class);
            startActivity(intent);
        });
        binding.button3.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this,Terrain.class);
            startActivity(intent);
        });
        setContentView(binding.getRoot());

    }
}