package com.example.stayi.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.stayi.myapplication.FragmentField.KeyDigit;
import androidx.annotation.RequiresApi;
import static com.example.stayi.myapplication.FragmentField.FieldDataType.FLOAT;

@SuppressLint("Registered")
public class KeyboardListener extends Activity implements View.OnClickListener {

    private TextView EDITABLE;
    private FieldAdaptedObject SelectedField;
    private String EmptyChar = "";
    private CharSequence Zero = "0";
    private FragmentAdaptor FieldAdaptor;
    private Context context;
    private ConditionsCalculator ConditionsCalc;

    //конструктор по умолчанию
    public KeyboardListener(View view, FragmentAdaptor fieldAdaptor, Context cont) {
        context = cont;
        //Собираем массив ID кнопок виртуальной клавиатуры.
        int[] BUTTON_IDS = new int[]{R.id.SL_KEY_0, R.id.SL_KEY_1, R.id.SL_KEY_2, R.id.SL_KEY_3, R.id.SL_KEY_4,
                R.id.SL_KEY_5, R.id.SL_KEY_6, R.id.SL_KEY_7, R.id.SL_KEY_8, R.id.SL_KEY_9, R.id.SL_KEY_DOT, R.id.SL_KEY_UP,
                R.id.SL_KEY_DOWN, R.id.SL_KEY_DEL, R.id.SL_KEY_CLEAR, R.id.SL_KEY_CLEAR_ALL};
        //Собираем массив кнопок, вешаем на него слушатель.
        Button[] BUTTONS = new Button[BUTTON_IDS.length];
        for (int i = 0; i < BUTTONS.length; ++i) {
            BUTTONS[i] = (Button) view.findViewById(BUTTON_IDS[i]);
            BUTTONS[i].setOnClickListener(this);
        }
        FieldAdaptor = fieldAdaptor;
        SelectedField = FieldAdaptor.getSelectedFieldAdaptedObject();
        EDITABLE = SelectedField.getField();
        ConditionsCalc = new ConditionsCalculator(FieldAdaptor, context);
    }

    private void RefreshEditableField() {
        FieldAdaptor.getSelectedFieldAdaptedObject().getFieldStringValue();
        SelectedField = FieldAdaptor.getSelectedFieldAdaptedObject();
        EDITABLE = SelectedField.getField();
    }

    private void ChangeFieldValue(KeyDigit i) {
        if (EDITABLE.getText().length() < FieldAdaptor.getSelectedMaxLength()) {
            EmptyChar = (String) EDITABLE.getText();
            if (EmptyChar.contentEquals(Zero)) EmptyChar = "";
            EmptyChar += i.getValue();
            EDITABLE.setText(EmptyChar);
        } else Toast.makeText(context, "Достигнут предел поля ввода", Toast.LENGTH_SHORT).show();
    }

    private void FieldValueAddDot() {
        EmptyChar = (String) EDITABLE.getText();
        CharSequence dot = ".";
        if (!EmptyChar.contains(dot)) EmptyChar += dot;
        EDITABLE.setText(EmptyChar);
    }

    private void FieldValueClear() {
        EDITABLE.setText(Zero);
    }

    private void FieldValueDelSymbol() {
        EmptyChar = (String) EDITABLE.getText();
        if (EmptyChar.length() == 3 && EmptyChar.contentEquals(".0")) {
            FieldValueClear();
        } else if (EmptyChar.length() > 1) {
            StringBuilder temp_str_arr = new StringBuilder();
            for (int i = 0; i < EmptyChar.length() - 1; ++i) {
                temp_str_arr.append(EmptyChar.charAt(i));
            }
            EDITABLE.setText(temp_str_arr.toString());
        } else {
            FieldValueClear();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        RefreshEditableField();
        switch (view.getId()) {
            case R.id.SL_KEY_0:
                ChangeFieldValue(KeyDigit.Zero);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_1:
                ChangeFieldValue(KeyDigit.One);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_2:
                ChangeFieldValue(KeyDigit.Two);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_3:
                ChangeFieldValue(KeyDigit.Three);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_4:
                ChangeFieldValue(KeyDigit.Four);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_5:
                ChangeFieldValue(KeyDigit.Five);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_6:
                ChangeFieldValue(KeyDigit.Six);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_7:
                ChangeFieldValue(KeyDigit.Seven);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_8:
                ChangeFieldValue(KeyDigit.Eight);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_9:
                ChangeFieldValue(KeyDigit.Nine);
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_DOT:
                if (SelectedField.getBaseObject().getFieldFieldDataTypeValue() == FLOAT) FieldValueAddDot();
                break;
            case R.id.SL_KEY_UP:
                FieldAdaptor.decrementSelectedPosition();
                break;
            case R.id.SL_KEY_DOWN:
                FieldAdaptor.incrementSelectedPosition();
                break;
            case R.id.SL_KEY_DEL:
                FieldValueDelSymbol();
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_CLEAR:
                FieldValueClear();
                ConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_CLEAR_ALL:
                FieldAdaptor.makeSelectDefault();
                FieldAdaptor.setZeroValuesAll();
                break;
        }
    }
}