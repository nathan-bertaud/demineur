package com.example.demineur;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demineur.databinding.FragmentSquareBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SquareFragment extends Fragment{

    private FragmentSquareBinding binding;
    private int nBombNeighbor;
    private boolean mIsBomb;
    private boolean mIsEmpty;
    private boolean mIsFlag;
    private boolean mIsUndiscovered;
    private static int skin[]=new int[13];
    SquareFragmentInterface mInterface;

    public SquareFragment(boolean isBomb) {
        this.mIsBomb=isBomb;
        this.mIsEmpty=!isBomb;
        this.mIsUndiscovered=true;
        this.mIsFlag=false;

        skin[0]=R.drawable.vide;
        skin[1]=R.drawable.numero_1;
        skin[2]=R.drawable.numero_2;
        skin[3]=R.drawable.numero_3;
        skin[4]=R.drawable.numero_4;
        skin[5]=R.drawable.numero_5;
        skin[6]=R.drawable.numero_6;
        skin[7]=R.drawable.numero_7;
        skin[8]=R.drawable.numero_8;
        skin[9]=R.drawable.numero_9;
        skin[10]=R.drawable.drapeau;
        skin[11]=R.drawable.pixil_frame_0;
        skin[12]=R.drawable.cachee;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.imageViewSquare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isClicked();
            }
        });

        binding.imageViewSquare.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {mIsFlag=true;updateSkin(); return true;}
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSquareBinding.inflate(inflater, container, false);
        updateSkin();
        return binding.getRoot();
    }

    public void setClickableFalse(){
        this.binding.imageViewSquare.setEnabled(false);
    }

    public void setInterface(SquareFragmentInterface xInterface) {
        this.mInterface = xInterface;
    }

    public void updateSkin(){
        if(this.isUndiscovered()){
            binding.imageViewSquare.setImageResource(skin[12]);
        }
        if(this.isEmpty()&&!this.isUndiscovered()){
            binding.imageViewSquare.setImageResource(skin[nBombNeighbor]);
        }
        if(this.isBomb()&&!this.isUndiscovered()){
            binding.imageViewSquare.setImageResource(skin[11]);
        }
        if(this.mIsFlag&&this.isUndiscovered()){
            binding.imageViewSquare.setImageResource(skin[10]);
        }
    }

    public boolean isBomb(){
        return mIsBomb;
    }
    public void setBomb(){
        mIsBomb=true;
    }

    public boolean isEmpty(){
        if (mIsEmpty){
            return true;
        }
        return false;
    }

    public void setUndiscovered(boolean b){
        mIsUndiscovered=b;
        updateSkin();
    }
    public boolean isUndiscovered(){
        if (mIsUndiscovered){
            return true;
        }
        return false;
    }
    public void isClicked(){
        mIsFlag = false;
        mIsUndiscovered=false;
        updateSkin();
        mInterface.squareClicked();
    }

    protected void setnBombNeighbor(int nBomb){
        nBombNeighbor=nBomb;
    }


}