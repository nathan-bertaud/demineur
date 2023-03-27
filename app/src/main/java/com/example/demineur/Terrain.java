package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.demineur.databinding.ActivityMainBinding;
import com.example.demineur.databinding.ActivityTerrainBinding;

public class Terrain extends AppCompatActivity {


    private ActivityTerrainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTerrainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}