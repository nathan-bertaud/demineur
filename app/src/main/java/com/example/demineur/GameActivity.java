package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.content.BroadcastReceiver;
import android.widget.TableRow;

import com.example.demineur.databinding.ActivityGameBinding;
import com.example.demineur.databinding.ActivityOptionsBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements SquareFragmentInterface{

    private SquareFragment squareTab[][]=new SquareFragment[10][10];
    private int nrow=4;
    private int ncol=5;
    private int tableRow[]=new int[4];

    private ActivityGameBinding binding;
    private Intent intentService;

    private MediaPlayer mediaPlayer;
    static public final String BROADCAST = "timer.projet";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private String prenom;
    private String nom;
    private Difficulte difficulte;

    private int timer;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent lastIntent = getIntent();
        Bundle bundle2 = lastIntent.getExtras();
        Profil p = (Profil) bundle2.getSerializable("PROFIL");
        this.prenom = p.getPrenom();
        this.nom = p.getNom();
        this.difficulte = p.getDifficulte();
        intentService = new Intent(this, MyService.class);
        prefs = getSharedPreferences("SCORE_DATA",MODE_PRIVATE);
        editor = prefs.edit();
        tableRow[0]=R.id.tableRow1;
        tableRow[1]=R.id.tableRow2;
        tableRow[2]=R.id.tableRow3;
        tableRow[3]=R.id.tableRow4;
        loadFragments();
        mediaPlayer = MediaPlayer.create(this, R.raw.musique);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(intentService);
        mediaPlayer.start();
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
        mediaPlayer.stop();
        mediaPlayer.release();
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
                timer=bundle.getInt("timer");
                binding.timerTextview.setText(String.valueOf(timer));
            }
        }
    };

    private void savePreferences(int score){
        Gson gson = new Gson();
        String jsonGet = prefs.getString("LIST","");
        List<Profil> list = gson.fromJson(jsonGet,new TypeToken<ArrayList<Profil>>(){}.getType() );
        list.add(new Profil(this.nom,this.prenom,this.difficulte,score));
        String jsonPut = gson.toJson(list);
        editor.putString("LIST",jsonPut);
        editor.apply();
    }

    protected void loadFragments(){
        int bombsGenerated[];
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                this.squareTab[i][j]=new SquareFragment(false,i,j);
                this.squareTab[i][j].setInterface(this);
            }
        }
        for(int nbBombs = 0; nbBombs < 3; nbBombs++){
            bombsGenerated = this.generateBombs();
            this.squareTab[bombsGenerated[0]][bombsGenerated[1]].setBomb();
        }

        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                ft.add(tableRow[i],this.squareTab[i][j]);
            }
        }
        ft.commit();
        checkBombs();
    }

    private int[] generateBombs(){
        int i = new Random().nextInt(4);
        int j = new Random().nextInt(5);
        return new int[]{i, j};
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

    protected boolean isWon() {
        int EmptyCasesCount = 0;
        int EmptyCasesDiscoveredCount = 0;
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (!this.squareTab[i][j].isBomb()) {
                    EmptyCasesCount++;
                    if (!this.squareTab[i][j].isUndiscovered()) {
                        EmptyCasesDiscoveredCount++;
                    }
                }
            }
        }
        if (EmptyCasesCount==EmptyCasesDiscoveredCount){
            return true;
        }
        return false;
    }

    protected boolean isLost() {
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (this.squareTab[i][j].isBomb()&&!this.squareTab[i][j].isUndiscovered()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void endOfGame(){
        if(isWon()||isLost()) {
            score = timer;
            if (isWon()) {
                binding.textViewGameState.setText("WON ! Score is " + score);
            }
            if (isLost()) {
                binding.textViewGameState.setText("LOST !");
            }
            for (int i = 0; i < nrow; i++) {
                for (int j = 0; j < ncol; j++) {
                    squareTab[i][j].setClickableFalse();
                }
            }
        }

    }
    @Override
    public void squareClicked(){
        endOfGame();
    }

    @Override
    public void squareEmptyClicked(int row, int col) {
        if (!squareTab[row][col].isBomb() && squareTab[row][col].getMIsUndiscovered()) {
            squareTab[row][col].setmIsUndiscovered(false);
            squareTab[row][col].updateSkin();
            if(squareTab[row][col].getnBombNeighbor() != 0)
            {
                return;
            }
                if (col > 0) {
                        squareEmptyClicked(row, col - 1);
                }
                if (col < ncol - 1) {
                        squareEmptyClicked(row, col + 1);
                }
                if (row > 0) {
                        squareEmptyClicked(row - 1, col);
                    if (col > 0) {
                            squareEmptyClicked(row - 1, col - 1);
                    }
                    if (col < ncol - 1) {
                            squareEmptyClicked(row - 1, col + 1);
                    }
                }
                if (row < nrow - 1) {
                        squareEmptyClicked(row + 1, col);
                    if (col > 0) {
                            squareEmptyClicked(row + 1, col - 1);
                    }
                    if (col < ncol - 1) {
                            squareEmptyClicked(row + 1, col + 1);
                    }
                }
            }
        }
}


