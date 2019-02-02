package com.example.stayi.MachiningForces.CustomView;

import android.content.Context;
import com.example.stayi.MachiningForces.Enumerations.FieldType;

/**
 * Обьект класса содержит значения, используемые при инициализации обьекта класса CustomView.
 */
class CustomViewValuesObject {
    private Context cont; //Контекст приложения.
    private FieldType FieldType; //Логический тип инициализируемого поля ввода (диаметр, скорость, подача и тд).
    private String Description; //Обозначение поля ввода.
    private String Unit; //Единицы измерения.

    CustomViewValuesObject(Context context, FieldType Ftype, int Description_ID, int Unit_ID) {
        cont = context;
        FieldType = Ftype;
        Description = getStringValue(Description_ID);
        Unit = getStringValue(Unit_ID);
    }

    private String getStringValue(int index) {
        return cont.getString(index);
    }

    FieldType getFieldType() {
        return FieldType;
    }

    String getDescription() {
        return Description;
    }

    String getUnit() {
        return Unit;
    }
}