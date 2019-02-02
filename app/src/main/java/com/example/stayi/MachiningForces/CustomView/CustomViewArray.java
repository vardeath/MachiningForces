package com.example.stayi.MachiningForces.CustomView;

import android.content.Context;

import com.example.stayi.MachiningForces.Enumerations.ButtonLockPosition;
import com.example.stayi.MachiningForces.Enumerations.FieldType;
import com.example.stayi.MachiningForces.Enumerations.ConditionsPreset;
import com.example.stayi.MachiningForces.FieldBaseObject;
import com.example.stayi.MachiningForces.HoldButtonRelatives;
import java.util.ArrayList;
import java.util.List;

public class CustomViewArray {

    private List<CustomViewObject> customViewObjectArray;

    private List<FieldBaseObject> BaseFieldObject = new ArrayList<FieldBaseObject>();

    private List<HoldButtonRelatives> ButtonRelatives = new ArrayList<HoldButtonRelatives>();

    public CustomViewArray(Context context, List<CustomViewObject> Array, ConditionsPreset CondPreset) {
        customViewObjectArray = Array;

        CustomValuesPreset valuesPreset = new CustomValuesPreset(context);
        List<CustomViewValuesObject> CurrentValues = valuesPreset.getPreset(CondPreset);

        init(CurrentValues);
        for (int i = 0; i < Array.size(); ++i) {
            BaseFieldObject.add(new FieldBaseObject(customViewObjectArray.get(i).getMainTextViewId(), customViewObjectArray.get(i).getMainTextViewFieldType()));
        }
    }

    private void init(List<CustomViewValuesObject> ValuesObject) {
        for (int i = 0; i < customViewObjectArray.size(); ++i) {
            customViewObjectArray.get(i).setValues(ValuesObject.get(i));
        }
    }

    private int getFieldPositionInArray(FieldType ftype) {
        int x = 0;
        for (int i = 0; i < customViewObjectArray.size(); ++i) {
            if (customViewObjectArray.get(i).getMainTextViewFieldType() == ftype) x = i;

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