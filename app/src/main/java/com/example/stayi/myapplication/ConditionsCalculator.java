package com.example.stayi.myapplication;

import android.content.Context;
import android.widget.Toast;
import com.example.stayi.myapplication.FragmentField.FieldType;
import static com.example.stayi.myapplication.FragmentField.FieldType.CuttingSpeed;
import static com.example.stayi.myapplication.FragmentField.FieldType.Diameter;
import static com.example.stayi.myapplication.FragmentField.FieldType.MinuteFeed;
import static com.example.stayi.myapplication.FragmentField.FieldType.Revolution;
import static com.example.stayi.myapplication.FragmentField.FieldType.Teeth;
import static com.example.stayi.myapplication.FragmentField.FieldType.ToothFeed;

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
            if (CalcObjects[i].getFieldType() == Diameter || CalcObjects[i].getFieldType() == Teeth) continue;
            if (CalcObjects[i].getAccessPermission()) {
                if (CalcObjects[i].getFieldType() == CuttingSpeed) {
                    CalculateRevolution();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == Revolution) {
                    CalculateCuttingSpeed();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == ToothFeed) {
                    CalculateMinuteFeed();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == MinuteFeed) {
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
        double MaxValue = getMaxValue(Revolution);
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
        double MaxValue = getMaxValue(CuttingSpeed);
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
        double MaxValue = getMaxValue(MinuteFeed);
        if (getMinuteFeed() > MaxValue) {
            setMinuteFeed(MaxValue);
            setToothFeed(getToothFeed());
            Toast.makeText(context, "Достигнут предел по минутной подаче, значение подачи на зуб изменено в соответствии с пределом минутной подачи", Toast.LENGTH_SHORT).show();
        } else setMinuteFeed(getMinuteFeed());
    }

    private void CalculateToothFeed(){
        double MaxValue = getMaxValue(ToothFeed);
        if (getToothFeed() > MaxValue) {
            setToothFeed(MaxValue);
            setMinuteFeed(getMinuteFeed());
            Toast.makeText(context, "Достигнут предел по подаче на зуб, значение минутной подачи изменено в соответствии с пределом подачи на зуб", Toast.LENGTH_SHORT).show();
        } else setToothFeed(getToothFeed());
    }

    private double getCuttingSpeed() {
        return (Math.PI * CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue()) / 1000;
    }

    private double getRevolution() {
        return (CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].getFieldDoubleValue() * 1000) / (CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue() * Math.PI);
    }

    private double getMinuteFeed() {
        return CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(Teeth)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].getFieldDoubleValue();
    }

    private double getToothFeed() {
        if (CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue() == 0) return 0;
        return CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].getFieldDoubleValue() / (CalcObjects[findPositionOnCalcObjectsArray(Teeth)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue());
    }

    private double getDiameter() {
        return CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue();
    }

    private void setRevolution(double value) {
        CalcObjects[findPositionOnCalcObjectsArray(Revolution)].setFieldDoubleValue(value);
    }

    private void setCuttingSpeed(double value) {
        CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].setFieldDoubleValue(value);
    }

    private void setToothFeed(double value) {
        CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].setFieldDoubleValue(value);
    }

    private void setMinuteFeed(double value) {
        CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].setFieldDoubleValue(value);
    }

    private double getMaxValue(FieldType fieldType) {
        return CalcObjects[findPositionOnCalcObjectsArray(fieldType)].getFieldMaxValue();
    }
}