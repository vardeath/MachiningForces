package com.example.stayi.MachiningForces.ConditionsModule;

import com.example.stayi.MachiningForces.Enumerations.FieldType;

class CalculatingObject {
    private FieldAdaptedObject mFieldAdaptedObject;

    CalculatingObject(FieldAdaptedObject AdaptedObject) {
        mFieldAdaptedObject = AdaptedObject;
    }

    double getFieldDoubleValue() {
        return mFieldAdaptedObject.getFieldDoubleValue();
    }

    void setFieldDoubleValue(double value){
        mFieldAdaptedObject.setFieldDoubleValue(value);
    }

    FieldType getFieldType() {
        return mFieldAdaptedObject.getBaseObject().getFieldTypeValue();
    }

    int getFieldMaxValue(){
        return mFieldAdaptedObject.getBaseObject().getMaxFieldValue();
    }

    boolean isLocked() {
        return mFieldAdaptedObject.getAllowedToSelectState();
    }
}