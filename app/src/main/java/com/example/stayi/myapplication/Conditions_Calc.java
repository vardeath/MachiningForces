package com.example.stayi.myapplication;

import android.widget.TextView;

public class Conditions_Calc {
    private int fragment_ID; //id фрагмента, для которого идет расчет.
    private information_bridge i_br; //Экземпляр посредника между слушателями клавиатуры и полей ввода фрагмента.
    private int selected_pos_ID; //Текущее выделенное поле ввода.

    private TextView[] Work_TW_arr;
    private int[] Work_ID_arr;
    private double[] Number_values;

    Conditions_Calc(int id, information_bridge br){
        fragment_ID = id;
        i_br = br;
        Work_TW_arr = br.get_TW_array();
        Work_ID_arr = br.get_ID_array();
        Number_values = new double[Work_TW_arr.length];
    }

    private void convers_arr_Str_to_Int(){
        for (int i = 0; i < Work_TW_arr.length; ++i){
            Number_values[i] = Double.valueOf((String) Work_TW_arr[i].getText());
        }
    }

    private void reverse_arr_INT_to_STR(){
        for (int i =0; i < Work_TW_arr.length; ++i){
            String temp = String.valueOf(Number_values[i]);
            StringBuilder temp2 = new StringBuilder();
            int len = temp.length();
            if (temp.charAt(len-1) == '0' && temp.charAt(len-2) == '.') {
                for (int x = 0; x < (len-2); ++x){
                    temp2.append(temp.charAt(x));
                }
            }
            Work_TW_arr[i].setText(temp2.toString());
        }
    }

    private void get_position_ID(){
        selected_pos_ID = i_br.get_selected_id();
    }

    void calculate(){
        switch (fragment_ID){
            case (R.id.MILL_calc_simple):
                calculate_simple_mill();
                break;
        }
    }

    private void calculate_simple_mill(){
        get_position_ID();
        convers_arr_Str_to_Int();
        if (Work_ID_arr[0] == selected_pos_ID){
            if (Number_values[1] == 0) {
                Number_values[1] = (Math.PI*Number_values[2]*Number_values[0])/1000;
            } else if (Number_values[2] == 0) {
                Number_values[2] = (Number_values[1]*1000) / (Number_values[0]*Math.PI);
            }
        }
        reverse_arr_INT_to_STR();
    }
}