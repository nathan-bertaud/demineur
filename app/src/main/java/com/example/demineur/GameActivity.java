package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TableRow;

import com.example.demineur.databinding.ActivityGameBinding;

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
/*
        fragments = new ArrayList<>();
        fragments.add(SquareFragment.newInstance(1));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for(SquareFragment frag : fragments) {
            ft.add(R.id.fragmentLayout,frag);
        }
        ft.commit();

 */
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
                squareTab[i][j]=SquareFragment.newInstance(1);
                ft.add(tableRow[i],squareTab[i][j]);
            }
        }
        ft.commit();
    }

}