package com.example.stayi.MachiningForces.ConditionsModule;

import com.example.stayi.MachiningForces.Enumerations.FieldConversePrecision;
import com.example.stayi.MachiningForces.Enumerations.FieldDataType;
import com.example.stayi.MachiningForces.Enumerations.FieldLength;
import com.example.stayi.MachiningForces.Enumerations.FieldMode;
import com.example.stayi.MachiningForces.Enumerations.FieldType;

import static com.example.stayi.MachiningForces.Enumerations.FieldMode.*;

//Базовый обьект поля ввода, содержащий информацию о допустимых значениях параметров ввода.
public class FieldBaseObject {

    private int FieldId; // ID поля ввода
    private FieldType FieldTypeValue; //Тип вводимой информации (Диаметр инструмента, Скорость резания и тд).
    private FieldDataType FieldFieldDataTypeValue; //Тип данных в поле ввода (integer или float).
    private FieldLength FieldLengthValue; //Длина поля ввода (в символах).
    private FieldConversePrecision FieldConversePrecisionValue; //Точность преобразования посчитанного значения в поле ввода.
    private FieldMode mFieldMode;
    private int MaxFieldValue;

    public FieldBaseObject(int id, FieldType fieldTypeValue) {
        FieldId = id;
        FieldTypeValue = fieldTypeValue;

        switch (FieldTypeValue) {
            case MillDiameter:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Five;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillCuttingSpeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillRevolutionQuantity:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillTeethQuantity:
                FieldFieldDataTypeValue = FieldDataType.INT;
                FieldLengthValue = FieldLength.Three;
                FieldConversePrecisionValue = FieldConversePrecision.None;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillToothFeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.High;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillMinuteFeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillGeneralAngle:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Three;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillAttackAngle:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Three;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillPathLength:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillToolLength:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Three;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillRevolutionFeed:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillCuttingDepth:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Three;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            case MillCuttingWidth:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Three;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = INPUT;
                break;
            default:
                FieldFieldDataTypeValue = FieldDataType.FLOAT;
                FieldLengthValue = FieldLength.Six;
                FieldConversePrecisionValue = FieldConversePrecision.Low;
                setMaxFieldValue();
                mFieldMode = OUTPUT;
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

    FieldMode getmFieldMode() {
        return mFieldMode;
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

    FieldConversePrecision getConversePrecisionLevel() {
        return FieldConversePrecisionValue;
    }
}