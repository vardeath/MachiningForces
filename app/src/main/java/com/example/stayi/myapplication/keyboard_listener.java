package com.example.stayi.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

@SuppressLint("Registered")
public class keyboard_listener extends Activity implements View.OnClickListener {

    private TextView EDITABLE;
    private String Temp_val = "";
    private CharSequence zero = "0";
    private Fragment_Adaptor fAdaptor;
    //Conditions_Calc calc_cond;

    //конструктор по умолчанию
    public keyboard_listener(View view, Fragment_Adaptor fAdapt) {
        //Собираем массив ID кнопок виртуальной клавиатуры.
        int[] BUTTON_IDS = new int[]{R.id.SL_KEY_0, R.id.SL_KEY_1, R.id.SL_KEY_2, R.id.SL_KEY_3, R.id.SL_KEY_4,
                R.id.SL_KEY_5, R.id.SL_KEY_6, R.id.SL_KEY_7, R.id.SL_KEY_8, R.id.SL_KEY_9, R.id.SL_KEY_DOT, R.id.SL_KEY_UP,
                R.id.SL_KEY_DOWN, R.id.SL_KEY_DEL, R.id.SL_KEY_CLEAR, R.id.SL_KEY_CLEAR_ALL};
        //Собираем массив кнопок, вешаем на него слушатель.
        Button[] BUTTONS = new Button[BUTTON_IDS.length];
        for (int i = 0; i < BUTTONS.length; ++i ){
            BUTTONS[i] = (Button) view.findViewById(BUTTON_IDS[i]);
            BUTTONS[i].setOnClickListener(this);
        }
        fAdaptor = fAdapt;
        EDITABLE = fAdaptor.getSelectedTextView();
        //calc_cond = new Conditions_Calc(fListener.getFragmentId(), fListener);
    }

    private void refresh_editable_field(){
        EDITABLE = fAdaptor.getSelectedTextView();
    }

    private void changer_digit_value(int i){
        if (EDITABLE.getText().length() < fAdaptor.getSelectedMaxLength()){
            Temp_val = (String) EDITABLE.getText();
            if (Temp_val.contentEquals(zero)) Temp_val = "";
            Temp_val += i;
            EDITABLE.setText(Temp_val);
        }
    }

    private void changer_set_dot(){
        Temp_val = (String) EDITABLE.getText();
        CharSequence dot = ".";
        if (!Temp_val.contains(dot)) Temp_val += dot;
        EDITABLE.setText(Temp_val);
    }

    private void changer_clear(){
        EDITABLE.setText(zero);
    }

    private void changer_del(){
        Temp_val = (String) EDITABLE.getText();
        if (Temp_val.length() == 3 && Temp_val.contentEquals(".0")) {
            changer_clear();
        }else if (Temp_val.length() > 1) {
            StringBuilder temp_str_arr = new StringBuilder();
            for (int i = 0; i < Temp_val.length() - 1; ++i) {
                temp_str_arr.append(Temp_val.charAt(i));}
            EDITABLE.setText(temp_str_arr.toString());
        } else {
            changer_clear();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        refresh_editable_field();
        switch (view.getId()) {
            case R.id.SL_KEY_0:
                changer_digit_value(0);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_1:
                changer_digit_value(1);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_2:
                changer_digit_value(2);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_3:
                changer_digit_value(3);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_4:
                changer_digit_value(4);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_5:
                changer_digit_value(5);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_6:
                changer_digit_value(6);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_7:
                changer_digit_value(7);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_8:
                changer_digit_value(8);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_9:
                changer_digit_value(9);
                //calc_cond.calculate();
                break;
            case R.id.SL_KEY_DOT:
                //if (R.id.TW_n_teeth != fListener.getTextViewSelectedId()) changer_set_dot(); //Число зубов фрезы должно быть целым значением.
                break;
            case R.id.SL_KEY_UP:
                fAdaptor.decrementSelectedPosition();
                break;
            case R.id.SL_KEY_DOWN:
                fAdaptor.incrementSelectedPosition();
                break;
            case R.id.SL_KEY_DEL:
                changer_del();
                break;
            case R.id.SL_KEY_CLEAR:
                changer_clear();
                break;
            case R.id.SL_KEY_CLEAR_ALL:
                fAdaptor.makeSelectDefault();
                fAdaptor.setZeroValuesAll();
                break;
        }
    }
}
