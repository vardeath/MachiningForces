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
    private static TemplateType Template = null;

    private static FieldType[] MillDetailArray = new FieldType[]{
            MillDiameter, MillCuttingSpeed, MillRevolutionQuantity, MillTeethQuantity,
            MillToothFeed, MillMinuteFeed, MillRevolutionFeed, MillCuttingDepth, MillCuttingWidth, MillGeneralAngle,
            MillPathLength, MillToolLength, MillAttackAngle
    };

    private static FieldType[] MillSimpleArray = new FieldType[]{
            MillDiameter, MillCuttingSpeed, MillRevolutionQuantity, MillTeethQuantity,
            MillToothFeed, MillMinuteFeed
    };

    private List<TemplateField> TArray;

    public TemplateFieldArray(Context context, List<TemplateField> Array, TemplateType Ttype) {
        cont = context;
        TArray = Array;
        Template = Ttype;
        switch (Ttype) {
            case MillDetail:
                init(Array, MillDetailArray, Ttype);
                break;
            case MillSimple:
                init(Array, MillSimpleArray, Ttype);
                break;
        }
    }

    private void init(List<TemplateField> Array, FieldType[] Ftype, TemplateType Ttype){
        for (int i = 0; i < Array.size(); ++i) {
            Array.get(i).setGeneralTextViewFieldType(Ftype[i]);
            Array.get(i).setText(new TemplateStringPairs(cont, Ftype[i], Ttype));
        }
    }

    public int getFieldIdByType(FieldType ftype) {
        int x = 0;
        switch (Template) {
            case MillSimple:
                for (int i = 0; i < MillSimpleArray.length; ++i) {
                    if (TArray.get(i).getGeneralTextViewFieldType() == ftype) {x = TArray.get(i).getGeneralTextViewId();}
                }
                break;
            case MillDetail:
                for (int i = 0; i < MillDetailArray.length; ++i) {
                    if (TArray.get(i).getGeneralTextViewFieldType() == ftype) {x = TArray.get(i).getGeneralTextViewId();}
                }
                break;
        }
        return x;
    }
}