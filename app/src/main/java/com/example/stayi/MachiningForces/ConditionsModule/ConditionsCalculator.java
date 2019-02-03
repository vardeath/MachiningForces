package com.example.stayi.MachiningForces.ConditionsModule;

import android.content.Context;
import android.widget.Toast;

import com.example.stayi.MachiningForces.ConditionsModule.CalculatingObject;
import com.example.stayi.MachiningForces.ConditionsModule.FragmentAdaptor;
import com.example.stayi.MachiningForces.Enumerations.FieldType;

import static com.example.stayi.MachiningForces.Enumerations.FieldType.*;

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
            if (CalcObjects[i].getFieldType() == MillDiameter || CalcObjects[i].getFieldType() == MillTeethQuantity)
                continue;
            if (CalcObjects[i].getAccessPermission()) {
                if (CalcObjects[i].getFieldType() == MillCuttingSpeed) {
                    CalculateRevolution();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == MillRevolutionQuantity) {
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
        double MaxValue = getMaxValue(MillRevolutionQuantity);
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
        return (Math.PI * CalcObjects[findPositionOnCalcObjectsArray(MillRevolutionQuantity)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(MillDiameter)].getFieldDoubleValue()) / 1000;
    }

    private double getRevolution() {
        return (CalcObjects[findPositionOnCalcObjectsArray(MillCuttingSpeed)].getFieldDoubleValue() * 1000) / (CalcObjects[findPositionOnCalcObjectsArray(MillDiameter)].getFieldDoubleValue() * Math.PI);
    }

    private double getMinuteFeed() {
        return CalcObjects[findPositionOnCalcObjectsArray(MillRevolutionQuantity)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(MillTeethQuantity)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(MillToothFeed)].getFieldDoubleValue();
    }

    private double getToothFeed() {
        if (CalcObjects[findPositionOnCalcObjectsArray(MillRevolutionQuantity)].getFieldDoubleValue() == 0)
            return 0;
        return CalcObjects[findPositionOnCalcObjectsArray(MillMinuteFeed)].getFieldDoubleValue() / (CalcObjects[findPositionOnCalcObjectsArray(MillTeethQuantity)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(MillRevolutionQuantity)].getFieldDoubleValue());
    }

    private double getDiameter() {
        return CalcObjects[findPositionOnCalcObjectsArray(MillDiameter)].getFieldDoubleValue();
    }

    private void setRevolution(double value) {
        CalcObjects[findPositionOnCalcObjectsArray(MillRevolutionQuantity)].setFieldDoubleValue(value);
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