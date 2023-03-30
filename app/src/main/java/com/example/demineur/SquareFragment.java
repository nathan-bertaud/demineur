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
    private static final String ARG_PARAM1 = "param1";
    private FragmentSquareBinding binding;
    // TODO: Rename and change types of parameters
    private int mState;
    private int nBombNeighbor;
    private static int skin[]=new int[12];
    public SquareFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SquareFragment newInstance(int state) {

        SquareFragment fragment = new SquareFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, state);
        fragment.setArguments(args);

        skin[0]=R.drawable.case_vide;
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
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_PARAM1);
        }
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
        updateAppearance();
        return binding.getRoot();
    }



    public void updateAppearance(){
        binding.imageViewSquare.setImageResource(skin[mState]);
    }

    public boolean isBomb(){
        if (mState==11){
            return true;
        }
        return false;
    }

    public void isClicked(){
        if (mState==0){
            this.setState(nBombNeighbor);
            updateAppearance();
        }
    }

    protected void setState(int state){
        mState=state;
    }

    protected void setnBombNeighbor(int nBomb){
        nBombNeighbor=nBomb;
    }

}