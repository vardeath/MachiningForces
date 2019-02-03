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
public class CustomViewArray {

    private List<CustomViewObject> CustomViews; //Основной массив Custom Views - группы Text Views (поле ввода, обозначение, единицы измерения.

    private List<FieldBaseObject> BaseFieldObject = new ArrayList<FieldBaseObject>(); //Массив базовых обьектов, с первичной информацией для FragmentAdaptor. Получает ID основного TextVIew из кастомноый группы.

    private List<HoldButtonRelatives> ButtonRelatives = new ArrayList<HoldButtonRelatives>(); //Массив со связями ID Hold Button и IDes родственых полей ввода.

    public CustomViewArray(Context context, List<CustomViewObject> CustomViewsArr, ConditionsPreset CondPreset) {
        CustomViews = CustomViewsArr;

        CustomValuesPreset valuesPreset = new CustomValuesPreset(context);
        List<CustomViewValuesObject> CurrentValues = valuesPreset.getPreset(CondPreset);

        init(CurrentValues);
        for (int i = 0; i < CustomViewsArr.size(); ++i) {
            BaseFieldObject.add(new FieldBaseObject(CustomViews.get(i).getMainTextViewId(), CustomViews.get(i).getMainTextViewFieldType()));
        }
    }

    private void init(List<CustomViewValuesObject> ValuesObject) {
        for (int i = 0; i < CustomViews.size(); ++i) {
            CustomViews.get(i).setValues(ValuesObject.get(i));
        }
    }

    private int getFieldPositionInArray(FieldType ftype) {
        int x = 0;
        for (int i = 0; i < CustomViews.size(); ++i) {
            if (CustomViews.get(i).getMainTextViewFieldType() == ftype) x = i;

        }
        return x;
    }

    public void setRelativeButton(int ButtonID, FieldType field_1, FieldType field_2, ButtonLockPosition lockPosition) {
        int firstFieldPosition = getFieldPositionInArray(field_1);
        int secondFieldPosition = getFieldPositionInArray(field_2);
        ButtonRelatives.add(new HoldButtonRelatives(ButtonID, firstFieldPosition, secondFieldPosition, lockPosition));
    }

    public List<FieldBaseObject> getBaseFieldObjects() {
        return BaseFieldObject;
    }

    public List<HoldButtonRelatives> getRelativeButtonArr() {
        return ButtonRelatives;
    }
}