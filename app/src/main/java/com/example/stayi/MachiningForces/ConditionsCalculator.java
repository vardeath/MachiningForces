package com.example.stayi.MachiningForces;

import android.content.Context;
import android.widget.Toast;

import com.example.stayi.MachiningForces.FragmentField.FieldType;

import static com.example.stayi.MachiningForces.FragmentField.FieldType.MillCuttingSpeed;
import static com.example.stayi.MachiningForces.FragmentField.FieldType.MillDiameter;
import static com.example.stayi.MachiningForces.FragmentField.FieldType.MillMinuteFeed;
import static com.example.stayi.MachiningForces.FragmentField.FieldType.MillRevolution;
import static com.example.stayi.MachiningForces.FragmentField.FieldType.MillTeeth;
import static com.example.stayi.MachiningForces.FragmentField.FieldType.MillToothFeed;

class ConditionsCalculator {
    private Context context;
    private FragmentAdaptor FieldAadaptor;
    private CalculatingObject[] CalcObjects;

    ConditionsCalculator(FragmentAdaptor fieldAdaptor, Context cont) {
        context = cont;
        FieldAadaptor = fieldAdaptor;
        CalcObjects = fieldAdaptor.getCalculatingObjects();
    }

    void calculate() {
        int current_position = FieldAadaptor.getCurrentSelectedPosition();

        for (int i = current_position; i < CalcObjects.length; ++i) {
            if (CalcObjects[i].getFieldType() == MillDiameter || CalcObjects[i].getFieldType() == MillTeeth) continue;
            if (CalcObjects[i].getAccessPermission()) {
                if (CalcObjects[i].getFieldType() == MillCuttingSpeed) {
                    CalculateRevolution();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == MillRevolution) {
                    CalculateCuttingSpeed();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == MillToothFeed) {
                    CalculateMinuteFeed();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == MillMinuteFeed) {
                    CalculateToothFeed();
                    continue;
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

    private void CalculateRevolution() {
        double MaxValue = getMaxValue(MillRevolution);
        if (getDiameter() > 0) {
            //Проверка на максимально допустимое значение.
            if (getRevolution() > MaxValue) {
                setRevolution(MaxValue);
                setCuttingSpeed(getCuttingSpeed());
                Toast.makeText(context, "Достигнут предел по оборотам, значение скорости резания изменено в соответствии с пределом по оборотам", Toast.LENGTH_SHORT).show();
            } else setRevolution(getRevolution());
        } else setRevolution(0);
    }

    private void CalculateCuttingSpeed() {
        double MaxValue = getMaxValue(MillCuttingSpeed);
        if (getDiameter() > 0) {
            //Проверка на максимально допустимое значение.
            if (getCuttingSpeed() > MaxValue) {
                setCuttingSpeed(MaxValue);
                setRevolution(getRevolution());
                Toast.makeText(context, "Достигнут предел по скорости резания, значение оборотов изменено в соответствии с пределом по скорости", Toast.LENGTH_SHORT).show();
            } else setCuttingSpeed(getCuttingSpeed());
        } else setCuttingSpeed(0);
    }

    private void CalculateMinuteFeed() {
        double MaxValue = getMaxValue(MillMinuteFeed);
        if (getMinuteFeed() > MaxValue) {
            setMinuteFeed(MaxValue);
            setToothFeed(getToothFeed());
            Toast.makeText(context, "Достигнут предел по минутной подаче, значение подачи на зуб изменено в соответствии с пределом минутной подачи", Toast.LENGTH_SHORT).show();
        } else setMinuteFeed(getMinuteFeed());
    }

    private void CalculateToothFeed(){
        double MaxValue = getMaxValue(MillToothFeed);
        if (getToothFeed() > MaxValue) {
            setToothFeed(MaxValue);
            setMinuteFeed(getMinuteFeed());
            Toast.makeText(context, "Достигнут предел по подаче на зуб, значение минутной подачи изменено в соответствии с пределом подачи на зуб", Toast.LENGTH_SHORT).show();
        } else setToothFeed(getToothFeed());
    }

    private double getCuttingSpeed() {
        return (Math.PI * CalcObjects[findPositionOnCalcObjectsArray(MillRevolution)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(MillDiameter)].getFieldDoubleValue()) / 1000;
    }

    private double getRevolution() {
        return (CalcObjects[findPositionOnCalcObjectsArray(MillCuttingSpeed)].getFieldDoubleValue() * 1000) / (CalcObjects[findPositionOnCalcObjectsArray(MillDiameter)].getFieldDoubleValue() * Math.PI);
    }

    private double getMinuteFeed() {
        return CalcObjects[findPositionOnCalcObjectsArray(MillRevolution)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(MillTeeth)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(MillToothFeed)].getFieldDoubleValue();
    }

    private double getToothFeed() {
        if (CalcObjects[findPositionOnCalcObjectsArray(MillRevolution)].getFieldDoubleValue() == 0) return 0;
        return CalcObjects[findPositionOnCalcObjectsArray(MillMinuteFeed)].getFieldDoubleValue() / (CalcObjects[findPositionOnCalcObjectsArray(MillTeeth)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(MillRevolution)].getFieldDoubleValue());
    }

    private double getDiameter() {
        return CalcObjects[findPositionOnCalcObjectsArray(MillDiameter)].getFieldDoubleValue();
    }

    private void setRevolution(double value) {
        CalcObjects[findPositionOnCalcObjectsArray(MillRevolution)].setFieldDoubleValue(value);
    }

    private void setCuttingSpeed(double value) {
        CalcObjects[findPositionOnCalcObjectsArray(MillCuttingSpeed)].setFieldDoubleValue(value);
    }

    private void setToothFeed(double value) {
        CalcObjects[findPositionOnCalcObjectsArray(MillToothFeed)].setFieldDoubleValue(value);
    }

    private void setMinuteFeed(double value) {
        CalcObjects[findPositionOnCalcObjectsArray(MillMinuteFeed)].setFieldDoubleValue(value);
    }

    private double getMaxValue(FieldType fieldType) {
        return CalcObjects[findPositionOnCalcObjectsArray(fieldType)].getFieldMaxValue();
    }
}