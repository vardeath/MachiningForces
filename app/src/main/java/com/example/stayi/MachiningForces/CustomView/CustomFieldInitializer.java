package com.example.stayi.MachiningForces.CustomView;

import android.content.Context;

import com.example.stayi.MachiningForces.Enumerations.ButtonLockPosition;
import com.example.stayi.MachiningForces.Enumerations.FieldType;
import com.example.stayi.MachiningForces.Enumerations.ConditionsPreset;
import com.example.stayi.MachiningForces.ConditionsModule.FieldBaseObject;
import com.example.stayi.MachiningForces.ConditionsModule.HoldButtonRelatives;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, инициализирующий массив кастомных View с параметрами согласно пресету.
 */
public class CustomFieldInitializer {

    private List<CustomInputField> mCustomInputField; //Основной массив Custom Views - группы Text Views (поле ввода, обозначение, единицы измерения.

    private List<FieldBaseObject> mFieldBaseObject = new ArrayList<>(); //Массив базовых обьектов, с первичной информацией для FragmentAdaptor. Получает ID основного TextVIew из кастомноый группы.

    private List<HoldButtonRelatives> mHoldButtonRelatives = new ArrayList<>(); //Массив со связями ID Hold Button и IDes родственых полей ввода.

    private List<CustomOutputField> mCustomOutputFields = new ArrayList<>(); //Массив обьектов для вывода расчетов.

    private PrimaryValuesPreset PrimaryValuesPreset;

    private ConditionsPreset CondPreset;

    public CustomFieldInitializer(Context context, List<CustomInputField> CustomViewsArr, ConditionsPreset CondPreset) {
        mCustomInputField = CustomViewsArr;
        this.CondPreset = CondPreset;
        PrimaryValuesPreset = new PrimaryValuesPreset(context);
        List<PrimaryValue> mPrimaryValue = PrimaryValuesPreset.getFieldPreset(CondPreset);

        initInputFields(mPrimaryValue);
        for (int i = 0; i < CustomViewsArr.size(); ++i) {
            mFieldBaseObject.add(new FieldBaseObject(mCustomInputField.get(i).getMainTextViewId(), mCustomInputField.get(i).getMainTextViewFieldType()));
        }
    }

    public void setCustomOutputObjectsFields(List<CustomOutputField> customOutputField) {
        mCustomOutputFields = customOutputField;
        List<PrimaryValue> CurrentValues = PrimaryValuesPreset.getFieldOutputPreset(CondPreset);
        initOutputFields(CurrentValues);
    }

    private void initInputFields(List<PrimaryValue> ValuesObject) {
        for (int i = 0; i < mCustomInputField.size(); ++i) {
            mCustomInputField.get(i).setValues(ValuesObject.get(i));
        }
    }

    private void initOutputFields(List<PrimaryValue> ValuesObject) {
        for (int i = 0; i < mCustomOutputFields.size(); ++i) {
            mCustomOutputFields.get(i).setValues(ValuesObject.get(i));
        }
    }

    private int getFieldPositionInArray(FieldType ftype) {
        int x = 0;
        for (int i = 0; i < mCustomInputField.size(); ++i) {
            if (mCustomInputField.get(i).getMainTextViewFieldType() == ftype) x = i;

        }
        return x;
    }

    public void setRelativeButton(int ButtonID, FieldType field_1, FieldType field_2, ButtonLockPosition lockPosition) {
        int firstFieldPosition = getFieldPositionInArray(field_1);
        int secondFieldPosition = getFieldPositionInArray(field_2);
        mHoldButtonRelatives.add(new HoldButtonRelatives(ButtonID, firstFieldPosition, secondFieldPosition, lockPosition));
    }

    public List<FieldBaseObject> getBaseFieldObjects() {
        return mFieldBaseObject;
    }

    public List<HoldButtonRelatives> getRelativeButtonArr() {
        return mHoldButtonRelatives;
    }
}