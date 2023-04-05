package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import com.example.demineur.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Difficulte difficulte = Difficulte.FACILE;
    private String prenom = "joueur";
    private String nom = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        Intent lastIntent = getIntent();
        Bundle bundle = lastIntent.getExtras();
        if(bundle  != null){
            String ativityName = bundle.getString("ActivityName");
            if(ativityName.equals("Options")){
                Profil p = (Profil) bundle.getSerializable("PROFIL");
                binding.textView5.setText(p.getPrenom());
                this.prenom = p.getPrenom();
                this.nom = p.getNom();
                binding.textView6.setText(p.getNom());
                this.difficulte = p.getDifficulte();
                binding.textDif.setText(this.difficulte.toString());
            }

        }

        binding.button.setOnClickListener(view ->{
            Intent intentOptions = new Intent(MainActivity.this,Options.class);
            Profil profil =  new Profil(this.nom,this.prenom, this.difficulte,0);
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("PROFIL",profil);s
            intentOptions.putExtras(bundle2);
            startActivity(intentOptions);
        });
        binding.button3.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this,GameActivity.class);
            startActivity(intent);
        });

        setContentView(binding.getRoot());
    }
}