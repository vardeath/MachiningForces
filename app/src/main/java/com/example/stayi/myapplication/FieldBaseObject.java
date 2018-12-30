package com.example.stayi.myapplication;

import com.example.stayi.myapplication.FragmentField.FieldConversePrecision;
import com.example.stayi.myapplication.FragmentField.FieldDataType;
import com.example.stayi.myapplication.FragmentField.FieldLength;
import com.example.stayi.myapplication.FragmentField.FieldType;

//Базовый обьект поля ввода, содержащий информацию о допустимых значениях параметров ввода.
public class FieldBaseObject {

    private int FieldId; // ID поля ввода
    private FieldType FieldTypeValue; //Тип вводимой информации (Диаметр инструмента, Скорость резания и тд).
    private FieldDataType FieldFieldDataTypeValue; //Тип данных в поле ввода (integer или float).
    private FieldLength FieldLengthValue; //Длина поля ввода (в символах).
    private FieldConversePrecision FieldConversePrecisionValue; //Точность преобразования посчитанного значения в поле ввода.

    public FieldBaseObject(int id, FieldType fieldTypeValue) {
        FieldId = id;
        FieldTypeValue = fieldTypeValue;

        switch (FieldTypeValue) {
            case Diameter:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Five;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                break;
            case CuttingSpeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                break;
            case Revolution:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                break;
            case Teeth:
                FieldFieldDataTypeValue = FieldDataType.INT;
                FieldLengthValue = FieldLength.Three;
                FieldConversePrecisionValue = FieldConversePrecision.None;
                break;
            case ToothFeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.High;
                break;
            case MinuteFeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                break;
        }
    }

    //get/set методы
    int getFieldId() {
        return FieldId;
    }

    public FieldDataType getFieldFieldDataTypeValue() {
        return FieldFieldDataTypeValue;
    }

    public FieldType getFieldTypeValue() {
        return FieldTypeValue;
    }

    FieldLength getFieldLengthValue() {
        return FieldLengthValue;
    }

    public FieldConversePrecision getFieldConversePrecisionValue() {
        return FieldConversePrecisionValue;
    }
}