package com.example.demineur;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demineur.databinding.FragmentSquareBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SquareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SquareFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "isBomb";
    private FragmentSquareBinding binding;
    // TODO: Rename and change types of parameters
    private int nBombNeighbor;
    private boolean mIsBomb;
    private boolean mIsEmpty;
    private boolean mIsUndiscovered;
    private static int skin[]=new int[13];
    public SquareFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SquareFragment newInstance(boolean isBomb) {

        SquareFragment fragment = new SquareFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isBomb);
        fragment.setArguments(args);
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
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIsBomb = getArguments().getBoolean(ARG_PARAM1);
        }
        System.out.println(this.mIsBomb);
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.imageViewSquare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println(nBombNeighbor);
                isClicked();
            }
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



    public void updateSkin(){
        if(this.isEmpty()&&!this.isUndiscovered()){
            binding.imageViewSquare.setImageResource(skin[nBombNeighbor]);
        }
        if(this.isBomb()&&!this.isUndiscovered()){
            binding.imageViewSquare.setImageResource(skin[11]);
        }

    }

    public boolean isBomb(){
        if (mIsBomb){
            return true;
        }
        return false;
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

    public boolean isUndiscovered(){
        if (mIsUndiscovered){
            return true;
        }
        return false;
    }
    public void isClicked(){
        if ((this.isEmpty())){
            updateSkin();
        }
        if ((this.isBomb())){
            updateSkin();
        }
    }


    protected void setnBombNeighbor(int nBomb){
        nBombNeighbor=nBomb;
    }

}