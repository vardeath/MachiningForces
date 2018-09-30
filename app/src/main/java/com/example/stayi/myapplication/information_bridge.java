package com.example.stayi.myapplication;

import android.widget.TextView;


public class information_bridge {
    String zero = "0";
    private int[] ITW_IDes; // Массив с ID Полей TextView, служащими для выбора и хранения значений расчета.
    private TextView[] ITW_DATA; //Массив с полями TextView, служащими для выбора и хранения значений расчета.
    private TextView[] FIX_index_tw; //Массив с полями TextView, служащими для индикации фиксации поля выбора позиции.
    private boolean[] FIX_val;//Массив с значенями полей, служащими для фиксации поля выбора позиции.

    private int selected_pos = 0;
    private TextView TW;
    private int arr_length;

    //Конструктор Класса
    public information_bridge(int FR_ID, int[] IDes, TextView[] Arr, TextView[] Fixindex, boolean[] fixvals) {
        ITW_IDes = IDes;
        ITW_DATA = Arr;
        arr_length = ITW_DATA.length;
        FIX_index_tw = Fixindex;
        FIX_val = fixvals;
    }

    //Очистить все поля ввода данных.
    void clear_all_fields(){
        for (TextView aITW_DATA : ITW_DATA) {
            aITW_DATA.setText(zero);
        }
    }

    //Установить селект на выбранном поле ввода.
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
    //Возвращает массив полей ввода Textview.
    TextView[] get_TextView_Array(){
        return ITW_DATA;
    }
    //Возвращает массив ID полей ввода Textview.
    int[] get_TextView_ID_Array(){
        return ITW_IDes;
    }

    boolean[] get_fixed_values(){
        return FIX_val;
    }
}