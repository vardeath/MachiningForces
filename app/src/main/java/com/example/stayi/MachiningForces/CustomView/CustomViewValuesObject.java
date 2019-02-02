package com.example.stayi.MachiningForces.CustomView;

import android.content.Context;

import com.example.stayi.MachiningForces.Enumerations.FieldType;

public class CustomViewValuesObject {
    private Context cont;
    private FieldType FieldTyp;
    private String DedcriptionID;
    private String UnitID;

    CustomViewValuesObject(Context context, FieldType Ftype, int Description_ID, int Unit_ID) {
        cont = context;
        FieldTyp = Ftype;
        DedcriptionID = getStringValue(Description_ID);
        UnitID = getStringValue(Unit_ID);
    }

    private String getStringValue(int index) {
        return cont.getString(index);
    }

    FieldType getFieldType() {
        return FieldTyp;
    }

    String getDedcription() {
        return DedcriptionID;
    }

    String getUnit() {
        return UnitID;
    }

}