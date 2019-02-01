package com.example.stayi.MachiningForces.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.stayi.MachiningForces.Enumerations.ButtonLockPosition;
import com.example.stayi.MachiningForces.Enumerations.FieldType;
import com.example.stayi.MachiningForces.Enumerations.PresetFieldsArrayType;
import com.example.stayi.MachiningForces.FieldBaseObject;
import com.example.stayi.MachiningForces.HoldButtonRelatives;

import java.util.ArrayList;
import java.util.List;

import static com.example.stayi.MachiningForces.Enumerations.FieldType.*;

public class CustomViewArray {

    @SuppressLint("StaticFieldLeak")
    private static Context cont = null;
    private static PresetFieldsArrayType Template = null;

    private static FieldType[] MillDetailArray = new FieldType[]{
            MillDiameter, MillCuttingSpeed, MillRevolutionQuantity, MillTeethQuantity,
            MillToothFeed, MillMinuteFeed, MillRevolutionFeed, MillCuttingDepth, MillCuttingWidth, MillGeneralAngle,
            MillPathLength, MillToolLength, MillAttackAngle
    };

    private static FieldType[] MillSimpleArray = new FieldType[]{
            MillDiameter, MillCuttingSpeed, MillRevolutionQuantity, MillTeethQuantity,
            MillToothFeed, MillMinuteFeed
    };

    private List<CustomView> CustomViewArray;

    List<FieldBaseObject> BaseFieldObject = new ArrayList<FieldBaseObject>();

    private List<HoldButtonRelatives> ButtonRelatives = new ArrayList<HoldButtonRelatives>();

    public CustomViewArray(Context context, List<CustomView> Array, PresetFieldsArrayType Fieldtype) {
        cont = context;
        CustomViewArray = Array;
        Template = Fieldtype;
        switch (Fieldtype) {
            case MillDetail:
                init(Array, MillDetailArray, Fieldtype);
                break;
            case MillSimple:
                init(Array, MillSimpleArray, Fieldtype);
                break;
        }
        for (int i = 0; i < Array.size(); ++i) {
            BaseFieldObject.add(new FieldBaseObject(CustomViewArray.get(i).getMainTextViewId(), CustomViewArray.get(i).getMainTextViewFieldType()));
        }
    }

    private void init(List<CustomView> Array, FieldType[] Ftype, PresetFieldsArrayType Ttype) {
        for (int i = 0; i < Array.size(); ++i) {
            Array.get(i).setGeneralTextViewFieldType(Ftype[i]);
            Array.get(i).setText(new CustomViewStringUnits(cont, Ftype[i], Ttype));
        }
    }

    public int getFieldPositionInArray(FieldType ftype) {
        int x = 0;
        for (int i = 0; i < MillSimpleArray.length; ++i) {
            if (CustomViewArray.get(i).getMainTextViewFieldType() == ftype) x = i;

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