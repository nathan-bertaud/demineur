package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.demineur.databinding.ActivityOptionsBinding;
import com.example.demineur.databinding.ActivityTerrainBinding;

public class Options extends AppCompatActivity {

    private ActivityOptionsBinding binding;

    private Difficulte difficulte = Difficulte.FACILE;
    private String nom = "1";
    private String prenom = "joueur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOptionsBinding.inflate(getLayoutInflater());
        Intent lastIntent = getIntent();
        Bundle bundle2 = lastIntent.getExtras();
        Profil p = (Profil) bundle2.getSerializable("PROFIL");
        this.prenom = p.getPrenom();
        this.nom = p.getNom();
        this.difficulte = p.getDifficulte();
        binding.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if(this.binding.radioButton.isChecked() ){
                this.difficulte = Difficulte.FACILE;
            }
            if(this.binding.radioButton2.isChecked()){
                this.difficulte = Difficulte.NORMAL;
            }
            if(this.binding.radioButton3.isChecked()){
                this.difficulte = Difficulte.DIFFICILE;
            }
        });
        binding.button2.setOnClickListener(view ->{
            Intent intent = new Intent(Options.this,MainActivity.class);
            if(this.binding.editTextTextPersonName.getText().equals("")){
                this.nom = this.binding.editTextTextPersonName.getText().toString();
            }
            if(this.binding.editTextTextPersonName2.getText().equals("")){
                this.prenom = this.binding.editTextTextPersonName2.getText().toString();
            }
            Profil profil =  new Profil(this.nom,this.prenom, this.difficulte,0);
            Bundle bundle = new Bundle();
            bundle.putSerializable("PROFIL",profil);
            intent.putExtras(bundle);
            intent.putExtra("ActivityName", "Options");
            startActivity(intent);
        });
        setContentView(binding.getRoot());
    }
}