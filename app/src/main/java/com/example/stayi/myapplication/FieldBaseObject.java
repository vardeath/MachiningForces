package com.example.stayi.myapplication;

import com.example.stayi.myapplication.FragmentField.FieldConversePrecision;
import com.example.stayi.myapplication.FragmentField.FieldDataType;
import com.example.stayi.myapplication.FragmentField.FieldLength;
import com.example.stayi.myapplication.FragmentField.FieldType;

public class FieldBaseObject {
    private int FieldId;
    private FieldType FieldTypeValue;
    private FieldDataType FieldFieldDataTypeValue;
    private FieldLength FieldLengthValue;
    private FieldConversePrecision FieldConversePrecisionValue;

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

    public int getFieldId() {
        return FieldId;
    }

    public FieldDataType getFieldFieldDataTypeValue() {
        return FieldFieldDataTypeValue;
    }

    public FieldType getFieldTypeValue() {
        return FieldTypeValue;
    }

    public FieldLength getFieldLengthValue() {
        return FieldLengthValue;
    }

    public FieldConversePrecision getFieldConversePrecisionValue() {
        return FieldConversePrecisionValue;
    }
}