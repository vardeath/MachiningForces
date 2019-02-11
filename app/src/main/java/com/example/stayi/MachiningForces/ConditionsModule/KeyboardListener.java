package com.example.stayi.MachiningForces.ConditionsModule;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stayi.MachiningForces.Enumerations.KeyDigit;
import com.example.stayi.MachiningForces.R;

import androidx.annotation.RequiresApi;

import static com.example.stayi.MachiningForces.Enumerations.FieldDataType.FLOAT;

@SuppressLint("Registered")
public class KeyboardListener extends Activity implements View.OnClickListener {

    private ScrollView CurrentScrollView = null;
    private TextView mEditable = null;
    private FieldAdaptedObject mSelectedField = null;
    private String mTextValue = "";
    private CharSequence mZero = "0";
    private FragmentAdaptor mFieldAdaptor = null;
    private Context context = null;
    private ConditionsCalculator mConditionsCalc = null;

    //конструктор по умолчанию
    public KeyboardListener(View view, FragmentAdaptor mFieldAdaptor, Context cont) {
        init(view, mFieldAdaptor, cont);
    }

    public KeyboardListener(View view, FragmentAdaptor mFieldAdaptor, Context cont, ScrollView ScView) {
        init(view, mFieldAdaptor, cont);
        CurrentScrollView = ScView;
    }

    private void init(View view, FragmentAdaptor fieldAdaptor, Context cont) {
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
        mFieldAdaptor = fieldAdaptor;
        mSelectedField = mFieldAdaptor.getSelectedFieldAdaptedObject();
        mEditable = mSelectedField.getField();
        mConditionsCalc = new ConditionsCalculator(mFieldAdaptor, context);
    }

    private void RefreshEditableField() {
        mFieldAdaptor.getSelectedFieldAdaptedObject().getFieldStringValue();
        mSelectedField = mFieldAdaptor.getSelectedFieldAdaptedObject();
        mEditable = mSelectedField.getField();
    }

    private void ChangeFieldValue(KeyDigit i) {
        if (mEditable.getText().length() < mFieldAdaptor.getSelectedMaxLength()) {
            mTextValue = (String) mEditable.getText();
            if (mTextValue.contentEquals(mZero))
                mTextValue = ""; //Убираем ноль перед вводом нового значения в строку.
            mTextValue += i.getValue();
            mEditable.setText(mTextValue);
        } else Toast.makeText(context, "Достигнут предел поля ввода", Toast.LENGTH_SHORT).show();
    }

    private void FieldValueAddDot() {
        mTextValue = (String) mEditable.getText();
        CharSequence dot = ".";
        if (!mTextValue.contains(dot)) mTextValue += dot;
        mEditable.setText(mTextValue);
    }

    private void FieldValueClear() {
        mEditable.setText(mZero);
    }

    private void FieldValueDelSymbol() {
        CharSequence SmallVal = "E";
        mTextValue = (String) mEditable.getText();
        if (mTextValue.contains(SmallVal)) {
            FieldValueClear();
        } else {
            if (mTextValue.length() == 3 && mTextValue.contentEquals(".0")) {
                FieldValueClear();
            } else if (mTextValue.length() > 1) {
                StringBuilder temp_str_arr = new StringBuilder();
                for (int i = 0; i < mTextValue.length() - 1; ++i) {
                    temp_str_arr.append(mTextValue.charAt(i));
                }
                mEditable.setText(temp_str_arr.toString());
            } else {
                FieldValueClear();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        RefreshEditableField();
        switch (view.getId()) {
            case R.id.SL_KEY_0:
                ChangeFieldValue(KeyDigit.Zero);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_1:
                ChangeFieldValue(KeyDigit.One);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_2:
                ChangeFieldValue(KeyDigit.Two);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_3:
                ChangeFieldValue(KeyDigit.Three);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_4:
                ChangeFieldValue(KeyDigit.Four);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_5:
                ChangeFieldValue(KeyDigit.Five);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_6:
                ChangeFieldValue(KeyDigit.Six);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_7:
                ChangeFieldValue(KeyDigit.Seven);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_8:
                ChangeFieldValue(KeyDigit.Eight);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_9:
                ChangeFieldValue(KeyDigit.Nine);
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_DOT:
                if (mSelectedField.getBaseObject().getFieldFieldDataTypeValue() == FLOAT)
                    FieldValueAddDot();
                break;
            case R.id.SL_KEY_UP:
                mFieldAdaptor.decrementSelectedPosition();
                if (CurrentScrollView != null) {
                    CurrentScrollView.scrollTo(0, 0);
                }
                break;
            case R.id.SL_KEY_DOWN:
                mFieldAdaptor.incrementSelectedPosition();
                if (CurrentScrollView != null) {
                    //CurrentScrollView.scrollTo(0, 300);
                    //CurrentScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    CurrentScrollView.endViewTransition(mFieldAdaptor.getSelectedFieldAdaptedObject().getField());
                }
                break;
            case R.id.SL_KEY_DEL:
                FieldValueDelSymbol();
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_CLEAR:
                FieldValueClear();
                mConditionsCalc.calculate();
                break;
            case R.id.SL_KEY_CLEAR_ALL:
                mFieldAdaptor.makeSelectDefault();
                mFieldAdaptor.setZeroValuesAll();
                break;
        }
        mFieldAdaptor.setStorageValues();
    }
}