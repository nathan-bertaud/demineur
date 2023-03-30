package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.demineur.databinding.ActivityOptionsBinding;
import com.example.demineur.databinding.ActivityTerrainBinding;

public class Options extends AppCompatActivity {

    private ActivityOptionsBinding binding;

    private Difficulte difficulte = Difficulte.FACILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOptionsBinding.inflate(getLayoutInflater());
        binding.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if(this.binding.radioButton.isChecked() ){
                this.difficulte = Difficulte.FACILE;
            }
            if(this.binding.radioButton2.isChecked()){
                this.difficulte = Difficulte.NOMRAL;
            }
            if(this.binding.radioButton3.isChecked()){
                this.difficulte = Difficulte.DIFFICILE;
            }
        });
        binding.button2.setOnClickListener(view ->{
            Intent intent = new Intent(Options.this,MainActivity.class);
            Profil profil =  new Profil(this.binding.editTextTextPersonName.getText().toString(),
                    this.binding.editTextTextPersonName.getText().toString(), this.difficulte,0);
            Bundle bundle = new Bundle();
            bundle.putSerializable("PROFIL",profil);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        setContentView(binding.getRoot());
    }
}