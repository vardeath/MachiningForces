package com.example.stayi.myapplication;

import android.view.View;
import android.widget.TextView;

class Fragment_data {
    //Набор базовых параметров элемента поля ввода.
    private View root_view;

    private int TextView_id;
    private TextView Text_view;
    private int Max_lenght = 0;
    private String TextView_string_value;
    private Double TextView_double_value;
    private boolean Selected_state;
    private boolean Allowed_to_select;

    Fragment_data(int TextV_id, View v){
        root_view = v;
        TextView_id = TextV_id;
        Text_view = v.findViewById(TextView_id);
    }

    void setMax_lenght(int len) {
        Max_lenght = len;
    }

    public int getMax_lenght() {
        return Max_lenght;
    }

    String getTextView_string_value(){
        TextView temp_view = root_view.findViewById(TextView_id);
        return temp_view.getText().toString();
    }

    Double getTextView_double_value(){
        return Double.valueOf(getTextView_string_value());
    }

    void setTextView_string_value(String text) {
        TextView_string_value = text;
        TextView temp_view = root_view.findViewById(TextView_id);
        temp_view.setText(TextView_string_value);
        refreshTextView_double_value();
    }

    void refreshTextView_double_value(){
        TextView_double_value = Double.valueOf(TextView_string_value);
    }

    void setTextView_double_value(Double val){
        TextView_double_value = val;
        setTextView_string_value(String.valueOf(val));
    }

    void setSelected_state(boolean state){
        if (Allowed_to_select) {
            Selected_state = state;
            if (state) Text_view.setBackgroundResource(R.drawable.textstyle_selected);
            else Text_view.setBackgroundResource(R.drawable.textstyle);
        } else Text_view.setBackgroundResource(R.drawable.textstyle_not_active);
    }
    //Меняем право допуска на изменение состояния выделения.
    void setAccess_to_select(boolean state){
        Allowed_to_select = state;
        if (!Allowed_to_select) {Selected_state = false;} //Если поле запрещено к выделению, меняем состояние.
    }

    int getTextView_id(){
        return TextView_id;
    }

    boolean getSelected_state(){
        return Selected_state;
    }

    boolean is_allowed_to_select(){
        return Allowed_to_select;
    }

    TextView getText_view(){
        return Text_view;
    }

    void setZeroValue(){
        setTextView_string_value("0");
    }
}