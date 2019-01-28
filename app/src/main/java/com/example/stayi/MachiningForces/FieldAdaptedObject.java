package com.example.stayi.MachiningForces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import static java.lang.String.format;

class FieldAdaptedObject {
    private Context context;
    //Набор базовых параметров элемента поля ввода.
    private View root_view; //Корневой View поля ввода.
    private FieldBaseObject BaseObject; //Обьект с базовой информацией о поле воода.
    private int TextView_id;
    private TextView Text_view;
    private String TextView_string_value;
    private boolean SelectedState; //Состояние выделения пользователем поля ввода: выделено/не выделено.
    private boolean AccessToSelect; //Представляет разрешение для выделения поля ввода.

    //Набор значений, для расчета числа знаков после запятой числа с плавающей точкой при округлении.
    private final int stageONE; //Степень округления - до целого числа.
    private final int stageTEN; //Степень округления - до числа с 1 знаком после запятой.
    private final int stageHUNDRED; //Степень округления - до числа с 2 знаками после запятой.
    private final int stageTHOUSAND; //Степень округления - до числа с 3 знаками после запятой.
    private final int stageTEN_THOUSAND; //Степень округления - до числа с 4 знаками после запятой.

    private final String Zero;

    FieldAdaptedObject(FieldBaseObject fieldBaseObject, View v, Context cont) {
        context = cont;
        root_view = v;
        BaseObject = fieldBaseObject;
        TextView_id = BaseObject.getFieldId();
        Text_view = v.findViewById(TextView_id);

        stageONE = 1;
        stageTEN = 10;
        stageHUNDRED = 100;
        stageTHOUSAND = 1000;
        stageTEN_THOUSAND = 10000;
        Zero = "0";
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
        setTextViewStringValue(ConverseToString(val));
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

    private String reformatStringValue(String val) { //Функция предотвращает ошибку парсера при чтении форматированного значения из String в Double.
        String toReturn = "";
        for (int i = 0; i < val.length(); ++i) {
            Character x;
            if (val.charAt(i) == ',') {
                x = '.';
            } else x = val.charAt(i);
            toReturn += x;
        }
        return toReturn;
    }

    @SuppressLint("DefaultLocale")
    private String RoundingValue(double value, int stage) { //Округляет значение до требуемой точности и переводит в строку.
        int res = (int) Math.round(value * stage);
        if (stage != stageTEN_THOUSAND) return String.valueOf((double) res / (double) stage);
        else {
            if ((double) res / (double) stage == 0) return Zero;
            else {
                String temp = format("%.4f", (double) res / (double) stage);
                return reformatStringValue(temp);
            }
        }
    }

    private String ZeroRemovedValue(String S) { // Удаляет символы ".0" из строки.
        String dotzero = ".0";
        if (S.contains(dotzero) && S.charAt(S.length() - 1) == dotzero.charAt(dotzero.length() - 1)) {
            S = S.substring(0, S.length() - dotzero.length());
        }
        return S;
    }

    private String ConverseToString(double val) {

        String Result;

        //Пределы значений для округления числа с заданной точностью.
        final int LowPrecisionUpLimit = 10;
        final int LowPrecisionDownLimit = 1;
        final double HighPrecisionUpLimit = 1;
        final double HighPrecisionMiddleLimit = 0.1;
        final double HighPrecisionDownLimit = 0.001;

        switch (getBaseObject().getFieldConversePrecisionValue()) {
            case Low:
                if (val > LowPrecisionUpLimit) {
                    Result = RoundingValue(val, stageONE);
                } else if (val > LowPrecisionDownLimit) {
                    Result = RoundingValue(val, stageTEN);
                } else {
                    Result = RoundingValue(val, stageHUNDRED);
                }
                return ZeroRemovedValue(Result);
            case High:
                if (val > HighPrecisionUpLimit) {
                    Result = RoundingValue(val, stageTEN);
                } else if (val > HighPrecisionMiddleLimit) {
                    Result = RoundingValue(val, stageHUNDRED);
                } else if (val > HighPrecisionDownLimit) {
                    Result = RoundingValue(val, stageTHOUSAND);
                } else {
                    Result = RoundingValue(val, stageTEN_THOUSAND);
                }
                return ZeroRemovedValue(Result);
            default:
                Result = RoundingValue(val, stageONE);
                return ZeroRemovedValue(Result);
        }
    }
}