package com.example.stayi.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

class FieldAdaptedObject {
    Context context;
    //Набор базовых параметров элемента поля ввода.
    private View root_view;
    private FieldBaseObject BaseObject;
    private int TextView_id;
    private TextView Text_view;
    private String TextView_string_value;
    private boolean SelectedState;
    private boolean AccessToSelect;

    FieldAdaptedObject(FieldBaseObject fieldBaseObject, View v, Context cont) {
        context = cont;
        root_view = v;
        BaseObject = fieldBaseObject;
        TextView_id = BaseObject.getFieldId();
        Text_view = v.findViewById(TextView_id);
    }

    FieldBaseObject getBaseObject() {
        return BaseObject;
    }

    String getFieldStringValue() {
        TextView temp_view = root_view.findViewById(TextView_id);
        return temp_view.getText().toString();
    }

    Double getFieldDoubleValue() {
        return Double.valueOf(getFieldStringValue());
    }

    private void setTextViewStringValue(String text) {
        TextView_string_value = text;
        TextView temp_view = root_view.findViewById(TextView_id);
        temp_view.setText(TextView_string_value);
    }

    void setFieldDoubleValue(Double val) {
        String temp;
        temp = ConverseToString(val);
        /*if (temp.length() > getBaseObject().getFieldLengthValue().getValue()) {Toast.makeText(context, ""+temp.length(), Toast.LENGTH_SHORT).show();
        }*/
        setTextViewStringValue(temp);
    }

    void setSelectedState(boolean state) {
        if (AccessToSelect) {
            SelectedState = state;
            if (state) Text_view.setBackgroundResource(R.drawable.textstyle_selected);
            else Text_view.setBackgroundResource(R.drawable.textstyle);
        } else Text_view.setBackgroundResource(R.drawable.textstyle_not_active);
    }

    //Меняем право допуска на изменение состояния выделения.
    void setAccessToSelectState(boolean state) {
        AccessToSelect = state;
        if (!AccessToSelect) {
            SelectedState = false;
        } //Если поле запрещено к выделению, меняем состояние.
    }

    int getFieldID() {
        return TextView_id;
    }

    boolean getSelectedState() {
        return SelectedState;
    }

    boolean getAllowedToSelectState() {
        return AccessToSelect;
    }

    TextView getField() {
        return Text_view;
    }

    void setZeroValue() {
        setTextViewStringValue("0");
    }

    private String ConverseToString(Double val) {

        Double value = val;
        int FirstRangeLimitLowPrecision = 10;
        int SecondRangeLimitLowPrecision = 1;
        double FirstRangeLimitHighPrecision = 1;
        double SecondRangeLimitHighPrecision = 0.1;

        switch (getBaseObject().getFieldConversePrecisionValue()) {
            case Low:
                if (val > FirstRangeLimitLowPrecision) {
                    int result = (int) Math.round(value);
                    float result2 = (float) result;
                    if (result2 != 0.0) return String.valueOf(result);
                } else if (val > SecondRangeLimitLowPrecision) {
                    value = value * 10;
                    int result = (int) Math.round(value);
                    float result2 = (float) result / 10;
                    int temp = (int) result2;
                    if (result2 != 0.0) {
                       if (temp != result2) return String.valueOf(result2);
                       else return String.valueOf(temp);
                    }
                } else {
                    value = value * 100;
                    int result = (int) Math.round(value);
                    float result2 = (float) result / 100;
                    if (result2 != 0.0) return String.valueOf(result2);
                }
                break;
            case High:
                if (val > FirstRangeLimitHighPrecision) {
                    value = value * 10;
                    int result = (int) Math.round(value);
                    float result2 = (float) result / 10;
                    int temp = (int) result2;
                    if (result2 != 0.0) {
                        if (temp != result2) return String.valueOf(result2);
                        else return String.valueOf(temp);
                    }
                } else if (val > SecondRangeLimitHighPrecision) {
                    value = value * 100;
                    int result = (int) Math.round(value);
                    float result2 = (float) result / 100;
                    if (result2 != 0.0) return String.valueOf(result2);
                } else {
                    value = value * 1000;
                    int result = (int) Math.round(value);
                    float result2 = (float) result / 1000;
                    if (result2 != 0.0) return String.valueOf(result2);
                }
                break;
            default:
                value = val;
                int result = (int) Math.round(value);
                float result2 = (float) result;
                if (result2 != 0.0) return String.valueOf(result);
        }
        return "0";
    }
}