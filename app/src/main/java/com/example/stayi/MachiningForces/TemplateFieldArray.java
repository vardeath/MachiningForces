package com.example.stayi.MachiningForces;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.stayi.MachiningForces.Enumerations.FieldType;
import com.example.stayi.MachiningForces.Enumerations.TemplateType;

import java.util.List;

import static android.app.PendingIntent.getActivity;
import static com.example.stayi.MachiningForces.Enumerations.FieldType.*;
import static java.security.AccessController.getContext;

public class TemplateFieldArray {

    @SuppressLint("StaticFieldLeak")
    private static Context cont = null;

    private static FieldType[] MillDetailArray = new FieldType[]{
            MillDiameter, MillCuttingSpeed, MillRevolutionQuantity, MillTeethQuantity,
            MillToothFeed, MillMinuteFeed, MillRevolutionFeed, MillCuttingDepth, MillCuttingWidth, MillGeneralAngle,
            MillPathLength, MillToolLength, MillAttackAngle
    };

    List<TemplateField> TArray;

    public TemplateFieldArray(Context context, List<TemplateField> Array, TemplateType Ttype) {
        cont = context;
        TArray = Array;
        switch (Ttype) {
            case MillDetail:
                for (int i = 0; i < Array.size(); ++i) {
                    Array.get(i).setGeneralTextViewFieldType(MillDetailArray[i]);
                    Array.get(i).setText(new TemplateStringPairs(cont, MillDetailArray[i], Ttype));
                }
                break;
        }
    }

    public int getFieldPositionByType(FieldType ftype) {
        int x = 0;
        for (int i = 0; i < MillDetailArray.length; ++i) {
            if (MillDetailArray[i] == ftype) x = i;
            break;
        }
        return x;
    }
}
