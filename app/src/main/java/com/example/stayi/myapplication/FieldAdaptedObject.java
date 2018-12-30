package com.example.stayi.myapplication;

import android.view.View;
import android.widget.TextView;

class FieldAdaptedObject {
    //Набор базовых параметров элемента поля ввода.
    private View root_view;
    private FieldBaseObject BaseObject;

    private int TextView_id;
    private TextView Text_view;
    private int Max_lenght = 0;
    private String TextView_string_value;
    private Double TextView_double_value;
    private boolean SelectedState;
    private boolean AccessToSelect;

    FieldAdaptedObject(FieldBaseObject fieldBaseObject, View v){
        root_view = v;
        BaseObject = fieldBaseObject;
        TextView_id = BaseObject.getFieldId();
        Text_view = v.findViewById(TextView_id);
    }

    void setMaxLength(int len) {
        Max_lenght = len;
    }

    public int getMaxLength() {
        return Max_lenght;
    }

    String getTextViewStringValue(){
        TextView temp_view = root_view.findViewById(TextView_id);
        return temp_view.getText().toString();
    }

    Double getTextView_double_value(){
        return Double.valueOf(getTextViewStringValue());
    }

    void setTextViewStringValue(String text) {
        TextView_string_value = text;
        TextView temp_view = root_view.findViewById(TextView_id);
        temp_view.setText(TextView_string_value);
        refreshTextViewDoubleValue();
    }

    void refreshTextViewDoubleValue(){
        TextView_double_value = Double.valueOf(TextView_string_value);
    }

    void setTextView_double_value(Double val){
        TextView_double_value = val;
        setTextViewStringValue(String.valueOf(val));
    }

    void setSelectedState(boolean state){
        if (AccessToSelect) {
            SelectedState = state;
            if (state) Text_view.setBackgroundResource(R.drawable.textstyle_selected);
            else Text_view.setBackgroundResource(R.drawable.textstyle);
        } else Text_view.setBackgroundResource(R.drawable.textstyle_not_active);
    }
    //Меняем право допуска на изменение состояния выделения.
    void setAccessToSelectState(boolean state){
        AccessToSelect = state;
        if (!AccessToSelect) {
            SelectedState = false;} //Если поле запрещено к выделению, меняем состояние.
    }

    int getTextViewId(){
        return TextView_id;
    }

    boolean getSelectedState(){
        return SelectedState;
    }

    boolean isAllowedToSelect(){
        return AccessToSelect;
    }

    TextView getText_view(){
        return Text_view;
    }

    void setZeroValue(){
        setTextViewStringValue("0");
    }
}