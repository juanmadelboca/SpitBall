package com.kalantos.spitball.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalantos.spitball.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseDifficultyFragment extends Fragment {


    public ChooseDifficultyFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_difficulty, container, false);
    }

}
