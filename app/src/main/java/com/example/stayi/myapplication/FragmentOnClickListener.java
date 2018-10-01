package com.example.stayi.myapplication;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public abstract class FragmentOnClickListener implements View.OnClickListener {

    private int FRAGMENT_ID;
    private int[] ArrayIdTextViews;
    private int[] ArrayIdOfRadiobuttons;
    private TextView[] ArrayOfTextViews;
    private RadioButton[] ArrayOfRadBtn;

    public FragmentOnClickListener(int fragment_id, View view, int[] ArrIdOfTextViews, int[] ArrIdOfRadbuttons){
        FRAGMENT_ID = fragment_id;
        ArrayIdTextViews = ArrIdOfTextViews;
        ArrayIdOfRadiobuttons = ArrIdOfRadbuttons;
        ArrayOfTextViews = new TextView[ArrayIdTextViews.length];
        ArrayOfRadBtn = new RadioButton[ArrayIdOfRadiobuttons.length];

        for (int i = 0; i < ArrIdOfTextViews.length; ++i){
            ArrayOfTextViews[i] = view.findViewById(ArrIdOfTextViews[i]);
            ArrayOfTextViews[i].setOnClickListener(this);
        }
        for (int i = 0; i < ArrIdOfRadbuttons.length; ++i){
            ArrayOfRadBtn[i] = view.findViewById(ArrIdOfRadbuttons[i]);
            ArrayOfRadBtn[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

    }

    int[] getArrayIdTextViews(){
        return ArrayIdTextViews;
    }

    TextView[] getArrayOfTextViews(){
        return ArrayOfTextViews;
    }

    int[] getArrayIdOfRadiobuttons(){
        return ArrayIdOfRadiobuttons;
    }

    RadioButton[] getArrayOfRadBtn(){
        return ArrayOfRadBtn;
    }

    int find_position_RadBtn_id(int ID){
        int found_position = 0;
        for (int i = 0; i < ArrayIdOfRadiobuttons.length; ++i){
            if (ArrayIdOfRadiobuttons[i] == ID) {found_position = i; }
        }
        return found_position;
    }
}