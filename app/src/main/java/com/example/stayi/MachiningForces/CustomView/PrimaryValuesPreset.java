package com.example.stayi.MachiningForces.CustomView;

import android.content.Context;

import com.example.stayi.MachiningForces.Enumerations.ConditionsPreset;
import com.example.stayi.MachiningForces.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.stayi.MachiningForces.Enumerations.FieldType.*;

class PrimaryValuesPreset {

    private Context cont;
    private static List<PrimaryValue> MillSimpleValuesArray = new ArrayList<PrimaryValue>();
    private static List<PrimaryValue> MillDetailValuesArray = new ArrayList<PrimaryValue>();

    private static List<PrimaryValue> MillDetailOutputValuesArray = new ArrayList<>();

    PrimaryValuesPreset(Context context) {
        cont = context;
        initMillSimpleArray();
        initMillDetailArray();
        initMillDetailOutputValuesArray();
    }

    private void initMillSimpleArray() {
        MillSimpleValuesArray.add(new PrimaryValue(cont, MillDiameter, R.string.Mill_Diameter_Description, R.string.Mill_DIameter_unit));
        MillSimpleValuesArray.add(new PrimaryValue(cont, MillCuttingSpeed, R.string.Mill_Cutting_Speed_Description, R.string.Mill_Cutting_Speed_Unit));
        MillSimpleValuesArray.add(new PrimaryValue(cont, MillRevolutionQuantity, R.string.Mill_Revolution_Description, R.string.Mill_Revolution_Unit));
        MillSimpleValuesArray.add(new PrimaryValue(cont, MillTeethQuantity, R.string.Mill_Teeth_Quantity_Description, R.string.Mill_Teeth_Unit));
        MillSimpleValuesArray.add(new PrimaryValue(cont, MillToothFeed, R.string.Mill_Feed_Per_Tooth_Description, R.string.Mill_Feed_Per_Tooth_Unit));
        MillSimpleValuesArray.add(new PrimaryValue(cont, MillMinuteFeed, R.string.Mill_Minute_Feed_Description, R.string.Mill_Minute_Feed_Unit));
    }

    private void initMillDetailArray() {
        MillDetailValuesArray.add(new PrimaryValue(cont, MillDiameter, R.string.Mill_Diameter_Description, R.string.Mill_DIameter_unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillCuttingSpeed, R.string.Mill_Cutting_Speed_Description, R.string.Mill_Cutting_Speed_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillRevolutionQuantity, R.string.Mill_Revolution_Description, R.string.Mill_Revolution_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillTeethQuantity, R.string.Mill_Teeth_Quantity_Description, R.string.Mill_Teeth_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillToothFeed, R.string.Mill_Feed_Per_Tooth_Description, R.string.Mill_Feed_Per_Tooth_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillMinuteFeed, R.string.Mill_Minute_Feed_Description, R.string.Mill_Minute_Feed_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillRevolutionFeed, R.string.Mill_Revolution_Feed_Description, R.string.Mill_Revolution_Feed_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillCuttingDepth, R.string.Mill_Cutting_Depth_Description, R.string.Mill_Cutting_Depth_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillCuttingWidth, R.string.Mill_Cutting_Width_Description, R.string.Mill_Cutting_Width_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillGeneralAngle, R.string.Mill_General_Angle_Description, R.string.Mill_General_Angle_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillPathLength, R.string.Mill_Path_Length_Description, R.string.Mill_Path_Length_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillToolLength, R.string.Mill_Tool_Length_Description, R.string.Mill_Tool_Length_Unit));
        MillDetailValuesArray.add(new PrimaryValue(cont, MillAttackAngle, R.string.Mill_Front_Angle_Description, R.string.Mill_Front_Angle_Unit));
    }

    private void initMillDetailOutputValuesArray() {
        MillDetailOutputValuesArray.add(new PrimaryValue(cont, MillAverageChipWidth, R.string.Mill_Average_Chip_Width));
        MillDetailOutputValuesArray.add(new PrimaryValue(cont, MillSpecificMaterialRemoval, R.string.Mill_Specific_Material_Removal_Description));
        MillDetailOutputValuesArray.add(new PrimaryValue(cont, MillCuttingTime, R.string.Mill_Cutting_Time_Description));
        MillDetailOutputValuesArray.add(new PrimaryValue(cont, MillMoment, R.string.Mill_Moment_Description));
        MillDetailOutputValuesArray.add(new PrimaryValue(cont, MillPower, R.string.Mill_Power_Description));
    }

    List<PrimaryValue> getFieldPreset(ConditionsPreset PresetType) {
        switch (PresetType) {
            case MillSimple:
                return MillSimpleValuesArray;
            case MillDetail:
                return MillDetailValuesArray;
        }
        return null;
    }

    List<PrimaryValue> getFieldOutputPreset(ConditionsPreset PresetType) {
        switch (PresetType) {
            case MillDetail:
                return MillDetailOutputValuesArray;
        }
        return null;
    }
}