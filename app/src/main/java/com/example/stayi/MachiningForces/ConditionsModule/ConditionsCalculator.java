package com.example.stayi.MachiningForces.ConditionsModule;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.stayi.MachiningForces.Enumerations.FieldType;
import java.util.concurrent.atomic.AtomicInteger;
import androidx.annotation.NonNull;
import static com.example.stayi.MachiningForces.Enumerations.FieldType.*;

class ConditionsCalculator {
    /**
     * Класс вычисляет double значения режимов обработки, и возвращает в виде double.
     */
    private Context context;
    private FragmentAdaptor mFieldAdaptor; //Подключаем фрагмент адаптор. Нужен для получения массива вычисляемых обьектов.
    private CalculatingObject[] mCalcObjects; //Массив обьектов для вычисления.

    ConditionsCalculator(@NonNull FragmentAdaptor fragmentAdaptor, Context context) {
        this.context = context;
        mFieldAdaptor = fragmentAdaptor;
        mCalcObjects = fragmentAdaptor.getCalculatingObjects();
    }

    void calculate() {
        int currentPosition = mFieldAdaptor.getCurrentSelectedPosition();

        for (int i = currentPosition; i < mCalcObjects.length; ++i) {
            //getMillAverageChipWidth();
            if (mCalcObjects[i].getFieldType() == MillDiameter || mCalcObjects[i].getFieldType() == MillTeethQuantity)
                continue;
            if (mCalcObjects[i].isLocked()) {
                if (mCalcObjects[i].getFieldType() == MillCuttingSpeed) {
                    calculateMillRevolution();
                    continue;
                }
                if (mCalcObjects[i].getFieldType() == MillRevolutionQuantity) {
                    calculateMillCuttingSpeed();
                    continue;
                }
                if (mCalcObjects[i].getFieldType() == MillToothFeed) {
                    calculateMillMinuteFeed();
                    continue;
                }
                if (mCalcObjects[i].getFieldType() == MillMinuteFeed) {
                    calculateMillToothFeed();
                    continue;
                }
                if (mCalcObjects[i].getFieldType() == MillRevolutionFeed) {
                    calculateMillRevolutionFeed();
                    continue;
                }
            }
            if (mCalcObjects[i].getFieldType() == MillCuttingWidth)
                calculateMillSpecificMaterialRemoval();
            if (mCalcObjects[i].getFieldType() == MillGeneralAngle) calculateMillAverageChipWidth();
            if (mCalcObjects[i].getFieldType() == MillPathLength) calculateMillCuttingTime();
        }
    }

    private int findPositionOnCalcObjectsArray(FieldType type) {
        AtomicInteger pos = new AtomicInteger();
        for (int i = 0; i < mCalcObjects.length; ++i) {
            if (mCalcObjects[i].getFieldType() == type) pos.set(i);
        }
        return pos.get();
    }

    private void calculateMillRevolution() {
        double maxValue = getMaxValue(MillRevolutionQuantity);
        if (getMillDiameter() > 0) {
            //Проверка на максимально допустимое значение.
            if (getMillRevolution() > maxValue) {
                setMillRevolution(maxValue);
                setMillCuttingSpeed(getMillCuttingSpeed());
                Toast.makeText(context, "Достигнут предел по оборотам, значение скорости резания изменено в соответствии с пределом по оборотам", Toast.LENGTH_SHORT).show();
            } else setMillRevolution(getMillRevolution());
        } else setMillRevolution(0);
    }

    private void calculateMillCuttingSpeed() {
        double maxValue = getMaxValue(MillCuttingSpeed);
        if (getMillDiameter() > 0) {
            //Проверка на максимально допустимое значение.
            if (getMillCuttingSpeed() > maxValue) {
                setMillCuttingSpeed(maxValue);
                setMillRevolution(getMillRevolution());
                Toast.makeText(context, "Достигнут предел по скорости резания, значение оборотов изменено в соответствии с пределом по скорости", Toast.LENGTH_SHORT).show();
            } else setMillCuttingSpeed(getMillCuttingSpeed());
        } else {
            setMillCuttingSpeed(0);
        }
    }

    private void calculateMillMinuteFeed() {
        double maxValue = getMaxValue(MillMinuteFeed);
        if (getMillMinuteFeed() > maxValue) {
            setMillMinuteFeed(maxValue);
            setMillToothFeed(getMillToothFeed());
            Toast.makeText(context, "Достигнут предел по минутной подаче, значение подачи на зуб изменено в соответствии с пределом минутной подачи", Toast.LENGTH_SHORT).show();
        } else setMillMinuteFeed(getMillMinuteFeed());
    }

    private void calculateMillToothFeed() {
        if (getMillToothFeed() == 0) return;
        double maxValue = getMaxValue(MillToothFeed);
        if (getMillToothFeed() > maxValue) {
            setMillToothFeed(maxValue);
            setMillMinuteFeed(getMillMinuteFeed());
            Toast.makeText(context, "Достигнут предел по подаче на зуб, значение минутной подачи изменено в соответствии с пределом подачи на зуб", Toast.LENGTH_SHORT).show();
        } else setMillToothFeed(getMillToothFeed());
    }

    private void calculateMillRevolutionFeed() {
        double maxValue = getMaxValue(MillRevolutionFeed);
        if (getMillRevolutionFeed() > maxValue) {
            setMillRevolutionFeed(maxValue);
        } else setMillRevolutionFeed(getMillRevolutionFeed());
    }

    private void calculateMillSpecificMaterialRemoval() {
        setMillSpecificMaterialRemoval(getMillSpecificMaterialRemoval());
    }

    private void calculateMillAverageChipWidth() {
        //getMillAverageChipWidth();
        setMillAverageChipWidth(getMillAverageChipWidth());
    }

    private void  calculateMillCuttingTime() {
        setMillCuttingTime(getMillCuttingTime());
    }

    private double getDoubleValue(FieldType fieldType) {
        return mCalcObjects[findPositionOnCalcObjectsArray(fieldType)].getFieldDoubleValue();
    }

    private void setDoubleValue(FieldType fieldType, double value) {
        mCalcObjects[findPositionOnCalcObjectsArray(fieldType)].setFieldDoubleValue(value);
    }

    private double getMillCuttingSpeed() {
        int divider = 1000;
        return (Math.PI * getDoubleValue(MillRevolutionQuantity) * getMillDiameter()) / divider;
    }

    private double getMillRevolution() {
        int multiplier = 1000;
        return (getDoubleValue(MillCuttingSpeed) * multiplier) / (getMillDiameter() * Math.PI);
    }

    private double getMillMinuteFeed() {
        return getDoubleValue(MillRevolutionQuantity) * getDoubleValue(MillTeethQuantity) * getDoubleValue(MillToothFeed);
    }

    private double getMillToothFeed() {
        if (getDoubleValue(MillRevolutionQuantity) == 0)
            return 0;
        return getDoubleValue(MillMinuteFeed) / (getDoubleValue(MillTeethQuantity) * getDoubleValue(MillRevolutionQuantity));
    }

    private double getMillRevolutionFeed() {
        return getDoubleValue(MillTeethQuantity) * getDoubleValue(MillToothFeed);
    }

    private double getMillSpecificMaterialRemoval() {
        int divider = 1000;
        return (getMillCuttingDepth() * getMillCuttingWidth() * getDoubleValue(MillMinuteFeed)) / divider;
    }

    private double getMillAverageChipWidth() {
        double mDefaultCoefficent = 114.7;
        double mDefauiltAngle = 90;
        String tag = "CALC";
        double tempVal = Math.sin(Math.toRadians(getDoubleValue(MillGeneralAngle)));
        Log.d(tag, ""+tempVal);
        double value = (mDefaultCoefficent * getDoubleValue(MillToothFeed) * Math.sin(Math.toRadians(getDoubleValue(MillGeneralAngle))) *
                (getDoubleValue(MillCuttingWidth) / getMillDiameter())) / (mDefauiltAngle + Math.toDegrees(Math.asin((getDoubleValue(MillCuttingWidth) - (getMillDiameter() / 2)) / (getMillDiameter() / 2))));
        //Toast.makeText(context, "" + value, Toast.LENGTH_SHORT).show();
        return value;
    }

    private double getMillCuttingTime() {
        return getDoubleValue(MillPathLength) / getDoubleValue(MillMinuteFeed);
    }

    private double getMillDiameter() {
        return getDoubleValue(MillDiameter);
    }

    private double getMillCuttingDepth() {
        return getDoubleValue(MillCuttingDepth);
    }

    private double getMillCuttingWidth() {
        return getDoubleValue(MillCuttingWidth);
    }

    private double getMillGeneralAngle() {
        return getDoubleValue(MillGeneralAngle);
    }

    private double getMillPathLength() {
        return getDoubleValue(MillPathLength);
    }

    private double getMillToolLength() {
        return getDoubleValue(MillToolLength);
    }

    private double getMillAttackAngle() {
        return getDoubleValue(MillAttackAngle);
    }

    private void setMillRevolution(double value) {
        setDoubleValue(MillRevolutionQuantity, value);
    }

    private void setMillCuttingSpeed(double value) {
        setDoubleValue(MillCuttingSpeed, value);
    }

    private void setMillToothFeed(double value) {
        setDoubleValue(MillToothFeed, value);
    }

    private void setMillMinuteFeed(double value) {
        setDoubleValue(MillMinuteFeed, value);
    }

    private void setMillRevolutionFeed(double value) {
        setDoubleValue(MillRevolutionFeed, value);
    }

    private void setMillSpecificMaterialRemoval(double value) {
        setDoubleValue(MillSpecificMaterialRemoval, value);
    }

    private void setMillAverageChipWidth(double value) {
        setDoubleValue(MillAverageChipWidth, value);
    }

    private void setMillCuttingTime (double value) {
        setDoubleValue(MillCuttingTime, value);
    }

    private double getMaxValue(FieldType fieldType) {
        return mCalcObjects[findPositionOnCalcObjectsArray(fieldType)].getFieldMaxValue();
    }
}