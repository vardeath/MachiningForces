package com.example.stayi.MachiningForces.CustomView;

import android.content.Context;

import com.example.stayi.MachiningForces.Enumerations.ConditionsPreset;
import com.example.stayi.MachiningForces.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.stayi.MachiningForces.Enumerations.FieldType.*;

class CustomValuesPreset {

    private Context cont;
    private static List<CustomViewValuesObject> MillSimpleValuesArray = new ArrayList<CustomViewValuesObject>();
    private static List<CustomViewValuesObject> MillDetailValuesArray = new ArrayList<CustomViewValuesObject>();

    CustomValuesPreset(Context context) {
        cont = context;
        initMillSimpleArray();
        initMillDetailArray();
    }

    private void initMillSimpleArray() {
        MillSimpleValuesArray.add(new CustomViewValuesObject(cont, MillDiameter, R.string.Mill_Diameter_Description, R.string.Mill_DIameter_unit));
        MillSimpleValuesArray.add(new CustomViewValuesObject(cont, MillCuttingSpeed, R.string.Mill_Cutting_Speed_Description, R.string.Mill_Cutting_Speed_Unit));
        MillSimpleValuesArray.add(new CustomViewValuesObject(cont, MillRevolutionQuantity, R.string.Mill_Revolution_Description, R.string.Mill_Revolution_Unit));
        MillSimpleValuesArray.add(new CustomViewValuesObject(cont, MillTeethQuantity, R.string.Mill_Teeth_Quantity_Description, R.string.Mill_Teeth_Unit));
        MillSimpleValuesArray.add(new CustomViewValuesObject(cont, MillToothFeed, R.string.Mill_Feed_Per_Tooth_Description, R.string.Mill_Feed_Per_Tooth_Unit));
        MillSimpleValuesArray.add(new CustomViewValuesObject(cont, MillMinuteFeed, R.string.Mill_Minute_Feed_Description, R.string.Mill_Minute_Feed_Unit));
    }

    private void initMillDetailArray() {
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillDiameter, R.string.Mill_Diameter_Description, R.string.Mill_DIameter_unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillCuttingSpeed, R.string.Mill_Cutting_Speed_Description, R.string.Mill_Cutting_Speed_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillRevolutionQuantity, R.string.Mill_Revolution_Description, R.string.Mill_Revolution_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillTeethQuantity, R.string.Mill_Teeth_Quantity_Description, R.string.Mill_Teeth_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillToothFeed, R.string.Mill_Feed_Per_Tooth_Description, R.string.Mill_Feed_Per_Tooth_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillMinuteFeed, R.string.Mill_Minute_Feed_Description, R.string.Mill_Minute_Feed_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillRevolutionFeed, R.string.Mill_Revolution_Feed_Description, R.string.Mill_Revolution_Feed_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillCuttingDepth, R.string.Mill_Cutting_Depth_Description, R.string.Mill_Cutting_Depth_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillCuttingWidth, R.string.Mill_Cutting_Width_Description, R.string.Mill_Cutting_Width_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillGeneralAngle, R.string.Mill_General_Angle_Description, R.string.Mill_General_Angle_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillPathLength, R.string.Mill_Path_Length_Description, R.string.Mill_Path_Length_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillToolLength, R.string.Mill_Tool_Length_Description, R.string.Mill_Tool_Length_Unit));
        MillDetailValuesArray.add(new CustomViewValuesObject(cont, MillAttackAngle, R.string.Mill_Front_Angle_Description, R.string.Mill_Front_Angle_Unit));
    }

    List<CustomViewValuesObject> getPreset(ConditionsPreset PresetType) {
        switch (PresetType) {
            case MillSimple:
                return MillSimpleValuesArray;
            case MillDetail:
                return MillDetailValuesArray;
        }
        return null;
    }
}