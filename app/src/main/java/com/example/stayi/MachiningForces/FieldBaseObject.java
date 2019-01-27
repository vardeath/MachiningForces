package com.example.stayi.MachiningForces;

import com.example.stayi.MachiningForces.FragmentField.FieldConversePrecision;
import com.example.stayi.MachiningForces.FragmentField.FieldDataType;
import com.example.stayi.MachiningForces.FragmentField.FieldLength;
import com.example.stayi.MachiningForces.FragmentField.FieldType;

//Базовый обьект поля ввода, содержащий информацию о допустимых значениях параметров ввода.
public class FieldBaseObject {

    private int FieldId; // ID поля ввода
    private FieldType FieldTypeValue; //Тип вводимой информации (Диаметр инструмента, Скорость резания и тд).
    private FieldDataType FieldFieldDataTypeValue; //Тип данных в поле ввода (integer или float).
    private FieldLength FieldLengthValue; //Длина поля ввода (в символах).
    private FieldConversePrecision FieldConversePrecisionValue; //Точность преобразования посчитанного значения в поле ввода.
    private int MaxFieldValue;

    public FieldBaseObject(int id, FieldType fieldTypeValue) {
        FieldId = id;
        FieldTypeValue = fieldTypeValue;

        switch (FieldTypeValue) {
            case Diameter:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Five;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                break;
            case CuttingSpeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                break;
            case Revolution:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                break;
            case Teeth:
                FieldFieldDataTypeValue = FieldDataType.INT;
                FieldLengthValue = FieldLength.Three;
                FieldConversePrecisionValue = FieldConversePrecision.None;
                setMaxFieldValue();
                break;
            case ToothFeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.High;
                setMaxFieldValue();
                break;
            case MinuteFeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                break;
        }
    }

    //get/set методы
    int getFieldId() {
        return FieldId;
    }

    //Формируем максимальное допустимое значение поля ввода исходя из его длины.
    private void setMaxFieldValue() {
        String Nine = "9"; //Максимальное число в одной ячейке поля ввода.
        StringBuilder len = new StringBuilder();
        for (int i = 0; i < FieldLengthValue.getValue(); ++i) {
            len.append(Nine);
        }
        MaxFieldValue = Integer.valueOf(len.toString());
    }

    int getMaxFieldValue() {
        return MaxFieldValue;
    }

    FieldDataType getFieldFieldDataTypeValue() {
        return FieldFieldDataTypeValue;
    }

    FieldType getFieldTypeValue() {
        return FieldTypeValue;
    }

    FieldLength getFieldLengthValue() {
        return FieldLengthValue;
    }

    FieldConversePrecision getFieldConversePrecisionValue() {
        return FieldConversePrecisionValue;
    }
}