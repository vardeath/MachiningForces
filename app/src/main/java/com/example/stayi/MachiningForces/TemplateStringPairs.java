package com.example.stayi.MachiningForces;

import android.content.Context;

import com.example.stayi.MachiningForces.Enumerations.FieldType;
import com.example.stayi.MachiningForces.Enumerations.TemplateType;

class TemplateStringPairs {

    private Context context = null;
    private String first = null;
    private String second = null;

    TemplateStringPairs(Context cont, FieldType fType, TemplateType tType) {
        context = cont;
        switch (tType) {
            case MillDetail:
                setMillDetailValues(fType);
                break;
            case MillSimple:
                setMillSimpleValues(fType);
                break;
        }
    }

    private void setMillDetailValues(FieldType ftype) {
        switch (ftype) {
            case MillDiameter:
                applyValues(getStringValue(R.string.Mill_Diameter_Description), getStringValue(R.string.Mill_DIameter_unit));
                break;
            case MillCuttingSpeed:
                applyValues(getStringValue(R.string.Mill_Cutting_Speed_Description), getStringValue(R.string.Mill_Cutting_Speed_Unit));
                break;
            case MillRevolutionQuantity:
                applyValues(getStringValue(R.string.Mill_Revolution_Description), getStringValue(R.string.Mill_Revolution_Unit));
                break;
            case MillTeethQuantity:
                applyValues(getStringValue(R.string.Mill_Teeth_Quantity_Description), getStringValue(R.string.Mill_Teeth_Unit));
                break;
            case MillToothFeed:
                applyValues(getStringValue(R.string.Mill_Feed_Per_Tooth_Description), getStringValue(R.string.Mill_Feed_Per_Tooth_Unit));
                break;
            case MillMinuteFeed:
                applyValues(getStringValue(R.string.Mill_Minute_Feed_Description), getStringValue(R.string.Mill_Minute_Feed_Unit));
                break;
            case MillRevolutionFeed:
                applyValues(getStringValue(R.string.Mill_Revolution_Feed_Description), getStringValue(R.string.Mill_Revolution_Feed_Unit));
                break;
            case MillCuttingDepth:
                applyValues(getStringValue(R.string.Mill_Cutting_Depth_Description), getStringValue(R.string.Mill_Cutting_Depth_Unit));
                break;
            case MillCuttingWidth:
                applyValues(getStringValue(R.string.Mill_Cutting_Width_Description), getStringValue(R.string.Mill_Cutting_Width_Unit));
                break;
            case MillGeneralAngle:
                applyValues(getStringValue(R.string.Mill_General_Angle_Description), getStringValue(R.string.Mill_General_Angle_Unit));
                break;
            case MillPathLength:
                applyValues(getStringValue(R.string.Mill_Path_Length_Description), getStringValue(R.string.Mill_Path_Length_Unit));
                break;
            case MillToolLength:
                applyValues(getStringValue(R.string.Mill_Tool_Length_Description), getStringValue(R.string.Mill_Tool_Length_Unit));
                break;
            case MillAttackAngle:
                applyValues(getStringValue(R.string.Mill_Front_Angle_Description), getStringValue(R.string.Mill_Front_Angle_Unit));
                break;
        }
    }

    private void setMillSimpleValues(FieldType ftype) {
        switch (ftype) {
            case MillDiameter:
                applyValues(getStringValue(R.string.Mill_Diameter_Description), getStringValue(R.string.Mill_DIameter_unit));
                break;
            case MillCuttingSpeed:
                applyValues(getStringValue(R.string.Mill_Cutting_Speed_Description), getStringValue(R.string.Mill_Cutting_Speed_Unit));
                break;
            case MillRevolutionQuantity:
                applyValues(getStringValue(R.string.Mill_Revolution_Description), getStringValue(R.string.Mill_Revolution_Unit));
                break;
            case MillTeethQuantity:
                applyValues(getStringValue(R.string.Mill_Teeth_Quantity_Description), getStringValue(R.string.Mill_Teeth_Unit));
                break;
            case MillToothFeed:
                applyValues(getStringValue(R.string.Mill_Feed_Per_Tooth_Description), getStringValue(R.string.Mill_Feed_Per_Tooth_Unit));
                break;
            case MillMinuteFeed:
                applyValues(getStringValue(R.string.Mill_Minute_Feed_Description), getStringValue(R.string.Mill_Minute_Feed_Unit));
                break;
        }
    }

    private void applyValues(String value1, String value2) {
        first = value1;
        second = value2;
    }

    private String getStringValue(int index) {
        return context.getString(index);
    }

    String getFirstValue() {
        return first;
    }

    String getSecondValue() {
        return second;
    }
}