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
    private int skin[]=new int[10];

    public SquareFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SquareFragment newInstance(int state) {

        SquareFragment fragment = new SquareFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, state);
        fragment.setArguments(args);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSquareBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void updateAppearance(){

    }


}