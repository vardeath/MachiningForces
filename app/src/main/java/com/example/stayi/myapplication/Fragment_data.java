package com.example.stayi.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class Fragment_data {
    //Набор базовых параметров элемента поля ввода.
    View root_view;

    int TextView_id;
    String TextView_string_value;
    Double TextView_double_value;
    boolean selected_state;
    boolean allowed_to_select;

    Fragment_data(int TextV_id, View v){
        root_view = v;
        TextView_id = TextV_id;
    }

    String getTextView_string_value(){
        TextView temp_view = root_view.findViewById(TextView_id);
        return temp_view.getText().toString();
    }
}