package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.content.BroadcastReceiver;
import android.widget.TableRow;

import com.example.demineur.databinding.ActivityGameBinding;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private SquareFragment squareTab[][]=new SquareFragment[10][10];
    private int nrow=4;
    private int ncol=5;
    private int tableRow[]=new int[4];

    private ActivityGameBinding binding;
    private Intent intentService;
    static public final String BROADCAST = "timer.projet";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intentService = new Intent(this, MyService.class);
        prefs = getSharedPreferences("MY_PREFS_NAME",MODE_PRIVATE);
        editor = prefs.edit();
        tableRow[0]=R.id.tableRow1;
        tableRow[1]=R.id.tableRow2;
        tableRow[2]=R.id.tableRow3;
        tableRow[3]=R.id.tableRow4;
        loadFragments();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(intentService);
        //TODO Appeler fonction pour mettre à jour le score précédent update();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(BROADCAST));
        binding.imageView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO casser une case
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

    private void sauvegarderScore(){

        //Si gagnant sauvegarder le score de la partie précédente
        //editor.putString("ANCIEN_SCORE", binding.timerTextview.getText().toString());
        //editor.apply();
        // update();
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

    protected void loadFragments(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                this.squareTab[i][j]=new SquareFragment(false);
            }
        }
        this.squareTab[1][1].setBomb();
        this.squareTab[2][2].setBomb();
        this.squareTab[2][3].setBomb();

        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                ft.add(tableRow[i],this.squareTab[i][j]);
            }
        }
        ft.commit();
        checkBombs();
    }

    protected void checkBombs(){
        int nBomb=0;
        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                if(!this.squareTab[i][j].isBomb()){
                    nBomb=0;
                if(j>0){
                    if (this.squareTab[i][j-1].isBomb()){ nBomb++;}
                }
                if(j<ncol-1){
                    if (this.squareTab[i][j+1].isBomb()){ nBomb++;}
                }
                if(i>0){
                    if (this.squareTab[i-1][j].isBomb()){ nBomb++;}
                    if(j>0){
                        if (this.squareTab[i-1][j-1].isBomb()){ nBomb++;}
                    }
                    if(j<ncol-1){
                        if (this.squareTab[i-1][j+1].isBomb()){ nBomb++;}
                    }
                }
                if(i<nrow-1){
                    if (this.squareTab[i+1][j].isBomb()){ nBomb++;}
                    if(j>0){
                        if (this.squareTab[i+1][j-1].isBomb()){ nBomb++;}
                    }
                    if(j<ncol-1){
                        if (this.squareTab[i+1][j+1].isBomb()){ nBomb++;}
                    }
                }
                    this.squareTab[i][j].setnBombNeighbor(nBomb);
            }
        }}
    }

        
}


