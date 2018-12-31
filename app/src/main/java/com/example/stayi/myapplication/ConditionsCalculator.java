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
                    CalculateCuttingSpeed();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == Revolution) {
                    CalculateRevolution();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == ToothFeed) {
                    CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].setFieldDoubleValue(getMinuteFeedValue());
                    CalculateToothFeed();
                    continue;
                }
                if (CalcObjects[i].getFieldType() == MinuteFeed) {
                    CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].setFieldDoubleValue(getToothFeedValue());
                    CalculateMinuteFeed();
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

    private void CalculateCuttingSpeed() {
        if (CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue() > 0) {
            CalcObjects[findPositionOnCalcObjectsArray(Revolution)].setFieldDoubleValue(getRevolutionValue());
            //Проверка на максимально допустимое значение.
            if (getRevolutionValue() > CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldMaxValue()) {
                CalcObjects[findPositionOnCalcObjectsArray(Revolution)].setFieldDoubleValue((double) CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldMaxValue());
                CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].setFieldDoubleValue(getCuttingSpeedValue());
                Toast.makeText(context, "Достигнут предел по оборотам, значение скорости резания изменено в соответствии с пределом по оборотам", Toast.LENGTH_SHORT).show();
            }
        } else CalcObjects[findPositionOnCalcObjectsArray(Revolution)].setFieldDoubleValue(0);
    }

    private void CalculateRevolution() {
        if (CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue() > 0) {
            CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].setFieldDoubleValue(getCuttingSpeedValue());
            //Проверка на максимально допустимое значение.
            if (getCuttingSpeedValue() > CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].getFieldMaxValue()) {
                CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].setFieldDoubleValue((double) CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].getFieldMaxValue());
                CalcObjects[findPositionOnCalcObjectsArray(Revolution)].setFieldDoubleValue(getRevolutionValue());
                Toast.makeText(context, "Достигнут предел по скорости резания, значение оборотов изменено в соответствии с пределом по скорости", Toast.LENGTH_SHORT).show();
            }
        } else CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].setFieldDoubleValue(0);
    }

    private void CalculateToothFeed() {
        if (getMinuteFeedValue() > CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].getFieldMaxValue()) {
            CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].setFieldDoubleValue((double) CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].getFieldMaxValue());
            CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].setFieldDoubleValue(getToothFeedValue());
            Toast.makeText(context, "Достигнут предел по минутной подаче, значение подачи на зуб изменено в соответствии с пределом минутной подачи", Toast.LENGTH_SHORT).show();
        }
    }

    private void CalculateMinuteFeed(){
        if (getToothFeedValue() > CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].getFieldMaxValue()) {
            CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].setFieldDoubleValue((double) CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].getFieldMaxValue());
            CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].setFieldDoubleValue(getMinuteFeedValue());
            Toast.makeText(context, "Достигнут предел по подаче на зуб, значение минутной подачи изменено в соответствии с пределом подачи на зуб", Toast.LENGTH_SHORT).show();
        }
    }

    private double getCuttingSpeedValue() {
        return (Math.PI * CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue()) / 1000;
    }

    private double getRevolutionValue() {
        return (CalcObjects[findPositionOnCalcObjectsArray(CuttingSpeed)].getFieldDoubleValue() * 1000) / (CalcObjects[findPositionOnCalcObjectsArray(Diameter)].getFieldDoubleValue() * Math.PI);
    }

    private double getMinuteFeedValue() {
        return CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(Teeth)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(ToothFeed)].getFieldDoubleValue();
    }

    private double getToothFeedValue() {
        return CalcObjects[findPositionOnCalcObjectsArray(MinuteFeed)].getFieldDoubleValue() / (CalcObjects[findPositionOnCalcObjectsArray(Teeth)].getFieldDoubleValue() * CalcObjects[findPositionOnCalcObjectsArray(Revolution)].getFieldDoubleValue());
    }
}