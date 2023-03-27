package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.demineur.databinding.ActivityOptionsBinding;
import com.example.demineur.databinding.ActivityTerrainBinding;

public class Options extends AppCompatActivity {

    private ActivityOptionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOptionsBinding.inflate(getLayoutInflater());
        binding.button2.setOnClickListener(view ->{
            Intent intent = new Intent(Options.this,MainActivity.class);
            Profil profil =  new Profil(this.binding.editTextTextPersonName.getText().toString(),
                    this.binding.editTextTextPersonName.getText().toString(), Difficulte.NOMRAL,0);
            startActivity(intent);
        });
        setContentView(binding.getRoot());
    }
}