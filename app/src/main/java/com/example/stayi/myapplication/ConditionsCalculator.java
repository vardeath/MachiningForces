package com.example.stayi.myapplication;

import com.example.stayi.myapplication.FragmentField.FieldType;
import static com.example.stayi.myapplication.FragmentField.FieldType.CuttingSpeed;
import static com.example.stayi.myapplication.FragmentField.FieldType.Diameter;
import static com.example.stayi.myapplication.FragmentField.FieldType.MinuteFeed;
import static com.example.stayi.myapplication.FragmentField.FieldType.Revolution;
import static com.example.stayi.myapplication.FragmentField.FieldType.Teeth;
import static com.example.stayi.myapplication.FragmentField.FieldType.ToothFeed;

class ConditionsCalculator {

    private FragmentAdaptor FieldAadaptor;
    private CalculatingObject[] CalcObjects;

    ConditionsCalculator(FragmentAdaptor fieldAdaptor) {
        FieldAadaptor = fieldAdaptor;
        CalcObjects = fieldAdaptor.getCalculatingObjects();
    }

    void calculate() {
        int current_position = FieldAadaptor.getCurrentSelectedPosition();

        for (int i = current_position; i < CalcObjects.length; ++i) {
            if (CalcObjects[i].getFieldType() == Diameter || CalcObjects[i].getFieldType() == Teeth) continue;
            if (CalcObjects[i].getAccessPermission() && CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue() > 0) {
                if (CalcObjects[i].getFieldType() == CuttingSpeed) {
                    CalcObjects[findPositionOnCalcObjectsArray(Revolution)].setFieldDoubleValue(getCalculateRevolution());
                }
                if (CalcObjects[i].getFieldType() == Revolution) {
                    CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].setFieldDoubleValue(getCalculateCuttingSpeed());
                }
                if (CalcObjects[i].getFieldType() == ToothFeed) {
                    CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].setFieldDoubleValue(getCalculateMinuteFeed());
                }
                if (CalcObjects[i].getFieldType() == MinuteFeed) {
                    CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].setFieldDoubleValue(getCalculateToothFeed());
                }
            }
        }
    }

    private int findPositionOnCalcObjectsArray(FieldType type) {
        int pos = 0;
        for (int i = 0; i < CalcObjects.length; ++i) {
            if (CalcObjects[i].getFieldType() == type) pos = i;
        }
        return pos;
    }

    private double getCalculateCuttingSpeed() {
        return (Math.PI * CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue()) / 1000;
    }

    private double getCalculateRevolution() {
        return (CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].getFieldDoubleValue() * 1000) / (CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue() * Math.PI);
    }

    private double getCalculateMinuteFeed() {
        return CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(Teeth)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].getFieldDoubleValue();
    }

    private double getCalculateToothFeed() {
        return CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].getFieldDoubleValue() / (CalcObjects[findPositionOnCalcObjectsArray(Teeth)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue());
    }
}