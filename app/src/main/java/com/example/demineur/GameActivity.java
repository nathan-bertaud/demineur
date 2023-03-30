package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TableRow;

import com.example.demineur.databinding.ActivityGameBinding;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private SquareFragment squareTab[][]=new SquareFragment[10][10];
    private int nrow=4;
    private int ncol=5;
    private int tableRow[]=new int[4];

    private ActivityGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tableRow[0]=R.id.tableRow1;
        tableRow[1]=R.id.tableRow2;
        tableRow[2]=R.id.tableRow3;
        tableRow[3]=R.id.tableRow4;
        loadFragments();
    }

    protected void loadFragments(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                squareTab[i][j]=SquareFragment.newInstance(0);
                ft.add(tableRow[i],squareTab[i][j]);
            }
        }
        checkBombs();
        ft.commit();
    }

    protected void checkBombs(){
        int nBomb=0;
        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){

                if(j>0){
                    if (squareTab[i][j-1].isBomb()){ nBomb++;}
                }
                if(j<ncol-1){
                    if (squareTab[i][j+1].isBomb()){ nBomb++;}
                }
                if(i>0){
                    if (squareTab[i-1][j].isBomb()){ nBomb++;}
                    if(j>0){
                        if (squareTab[i-1][j-1].isBomb()){ nBomb++;}
                    }
                    if(j<ncol-1){
                        if (squareTab[i-1][j+1].isBomb()){ nBomb++;}
                    }
                }
                if(i<nrow-1){
                    if (squareTab[i+1][j].isBomb()){ nBomb++;}
                    if(j>0){
                        if (squareTab[i+1][j-1].isBomb()){ nBomb++;}
                    }
                    if(j<ncol-1){
                        if (squareTab[i+1][j+1].isBomb()){ nBomb++;}
                    }
                }
                squareTab[i][j].setnBombNeighbor(nBomb);
            }
        }
    }

        
}


