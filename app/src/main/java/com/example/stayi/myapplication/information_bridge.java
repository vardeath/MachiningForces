package com.example.stayi.myapplication;

import android.widget.TextView;


public class information_bridge {
    String zero = "0";
    private int[] ITW_IDes;
    private TextView[] ITW_DATA;
    private int selected_pos = 0;
    private TextView TW;
    private int arr_length = 0;

    public information_bridge(int[] IDes, TextView[] Arr) {
        ITW_IDes = IDes;
        ITW_DATA = Arr;
        arr_length = ITW_DATA.length;
    }

    void clear_all_fields(){
        for (TextView aITW_DATA : ITW_DATA) {
            aITW_DATA.setText(zero);
        }
    }
    public void set_selected_pos(int pos){
        selected_pos = pos;
        TW = ITW_DATA[selected_pos];
    }

    void decrement_position(){
        if (selected_pos  == 0) selected_pos = arr_length-1;
        else --selected_pos;
    }

    void increment_position(){
        if ((selected_pos + 1) == arr_length) selected_pos = 0;
        else ++selected_pos;
    }

    TextView get_selected_view(){
        set_selected_pos(selected_pos);
        return TW;
    }
}