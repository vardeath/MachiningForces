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
        for(int x = 0; x < arr_length; ++x){
            if (x != selected_pos) ITW_DATA[x].setBackgroundResource(R.drawable.textstyle);
        }
        TW.setBackgroundResource(R.drawable.textstyle_selected);
    }

    //Переместить выеделение на поле ввода(TextView) выше текущего выделенного.
    void decrement_position(){
        if (selected_pos  == 0) selected_pos = arr_length-1;
        else --selected_pos;
    }
    //Переместить выеделение на поле ввода(TextView) ниже текущего выделенного.
    void increment_position(){
        if ((selected_pos + 1) == arr_length) selected_pos = 0;
        else ++selected_pos;
    }
    //Возвращает текущее выделенное поле ввода (TextView).
    TextView get_selected_view(){
        set_selected_pos(selected_pos);
        return TW;
    }
    //Возвращает id выделенного Textview.
    int get_selected_id(){
        return ITW_IDes[selected_pos];
    }

    TextView[] get_TextView_Array(){
        return ITW_DATA;
    }

    int[] get_TextView_ID_Array(){
        return ITW_IDes;
    }
}