package com.example.stayi.myapplication;

import android.widget.TextView;

public class information_bridge {
    private int[] ITW_IDes;
    private TextView[] ITW_DATA;
    private int selected_pos = 0;
    private TextView TW;

    public information_bridge(int[] IDes, TextView[] Arr){
        ITW_IDes = IDes;
        ITW_DATA = Arr;
    }
    public void set_selected_pos(int pos){
        selected_pos = pos;
        TW = ITW_DATA[selected_pos];
    }
    public TextView get_selected_view(){
        return TW;
    }
}