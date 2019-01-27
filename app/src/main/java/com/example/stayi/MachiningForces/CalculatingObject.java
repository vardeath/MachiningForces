package com.example.stayi.MachiningForces;

import com.example.stayi.MachiningForces.FragmentField.FieldType;

class CalculatingObject {
    private FieldAdaptedObject FObject;

    CalculatingObject(FieldAdaptedObject AdaptedObject) {
        FObject = AdaptedObject;
    }

    double getFieldDoubleValue() {
        return FObject.getFieldDoubleValue();
    }

    void setFieldDoubleValue(double value){
        FObject.setFieldDoubleValue(value);
    }

    FieldType getFieldType() {
        return FObject.getBaseObject().getFieldTypeValue();
    }

    boolean getAccessPermission(){
        return FObject.getAllowedToSelectState();
    }

    int getFieldMaxValue(){
        return FObject.getBaseObject().getMaxFieldValue();
    }
}