package com.example.stayi.myapplication;

import android.widget.TextView;

class Conditions_Calc {
    private int fragment_ID; //id фрагмента, для которого идет расчет.
    private FragmentOnClickListener i_br; //Экземпляр посредника между слушателями клавиатуры и полей ввода фрагмента.
    private int selected_pos_ID; //Текущее выделенное поле ввода.

    private TextView[] Work_TW_arr;
    private int[] Work_ID_arr;
    private double[] Number_values;
    //private TextView[] Work_FIXED_Arr;
    private boolean[] Work_FIXED_values_Arr;

    Conditions_Calc(int id, FragmentOnClickListener br) {
        fragment_ID = id;
        i_br = br;
        Work_TW_arr = br.getTextViewArray();
        Work_ID_arr = br.getTextViewArrayID();
        Number_values = new double[Work_TW_arr.length];
        //Work_FIXED_Arr = br.get_fixed_TextView_Array();
        //Work_FIXED_values_Arr = br.get_fixed_values();
    }

    private void convers_arr_Str_to_Double() {
        for (int i = 0; i < Work_TW_arr.length; ++i) {
            Number_values[i] = Double.valueOf((String) Work_TW_arr[i].getText());
        }
    }

    private String reverse_arr_Double_to_STR(Double val) {
        //Округляем значение до сотых.
        Double value = val * 100;
        int result = (int) Math.round(value);
        float result2 = (float) result / 100;
        if (result2 != 0.0) return String.valueOf(result2);
        return "0";
    }

    private void get_position_ID() {
        selected_pos_ID = i_br.getTextViewSelectedId();
    }

    void calculate() {
        switch (fragment_ID) {
            case (R.id.MILL_calc_simple):
                calculate_simple_mill();
                break;
        }
    }

    private void calculate_simple_Mill_speed() {
        Number_values[1] = (Math.PI * Number_values[2] * Number_values[0]) / 1000;
        Work_TW_arr[1].setText(reverse_arr_Double_to_STR(Number_values[1]));
    }

    private void calculate_simple_Mill_rev() {
        Number_values[2] = (Number_values[1] * 1000) / (Number_values[0] * Math.PI);
        Work_TW_arr[2].setText(reverse_arr_Double_to_STR(Number_values[2]));
    }

    private void calculate_simple_Mill_fmin() {
        Number_values[5] = Number_values[2] * Number_values[3] * Number_values[4];
        Work_TW_arr[5].setText(reverse_arr_Double_to_STR(Number_values[5]));
    }

    private void calculate_simple_Mill_fz() {
        Number_values[4] = Number_values[5] / (Number_values[3] * Number_values[2]);
        Work_TW_arr[4].setText(reverse_arr_Double_to_STR(Number_values[4]));
    }

    private void calculate_simple_mill() {
        get_position_ID();
        convers_arr_Str_to_Double();
        Double Diameter = Number_values[0];
        Double Vc = Number_values[1];
        Double Rev = Number_values[2];
        Double Teeth = Number_values[3];
        Double Fz = Number_values[4];
        Double Fmin = Number_values[5];

        if (Work_ID_arr[0] == selected_pos_ID) {
            if (Diameter > 0) {
                if (Work_FIXED_values_Arr[0]) {
                    calculate_simple_Mill_rev();
                } else calculate_simple_Mill_speed();
                if (Teeth > 0) {
                    if (Work_FIXED_values_Arr[2]) {
                        calculate_simple_Mill_fmin();
                    } else {
                        calculate_simple_Mill_fz();
                    }
                }
            }
        }
        if (Work_ID_arr[1] == selected_pos_ID) {
            if (Diameter > 0) {
                calculate_simple_Mill_rev();
            }
            if (Teeth > 0) {
                if (Work_FIXED_values_Arr[2]) {
                    calculate_simple_Mill_fmin();
                } else {
                    calculate_simple_Mill_fz();
                }
            }
        }
        if (Work_ID_arr[2] == selected_pos_ID) {
            if (Diameter > 0) {
                calculate_simple_Mill_speed();
            }
            if (Teeth > 0) {
                if (Work_FIXED_values_Arr[2]) {
                    calculate_simple_Mill_fmin();
                } else {
                    calculate_simple_Mill_fz();
                }
            }
        }
        if (Work_ID_arr[3] == selected_pos_ID) {
            if (Teeth > 0) {
                if (Work_FIXED_values_Arr[2]) {
                    calculate_simple_Mill_fmin();
                } else {
                    calculate_simple_Mill_fz();
                }
            }
        }
        if (Work_ID_arr[4] == selected_pos_ID) {
            if (Teeth > 0 && Rev > 0) {
                calculate_simple_Mill_fmin();
            }
        }
        if (Work_ID_arr[5] == selected_pos_ID) {
            if (Teeth > 0 && Rev > 0) {
                calculate_simple_Mill_fz();
            }
        }
    }
}