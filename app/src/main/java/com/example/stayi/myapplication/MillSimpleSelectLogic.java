package com.example.stayi.myapplication;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MillSimpleSelectLogic extends FragmentOnClickListener {

    private RadioButton[] Radbtnarr;
    public MillSimpleSelectLogic(int fragment_id, View view, int[] ArrIdOfTextViews, int[] ArrIdOfRadbuttons){
        super(fragment_id, view, ArrIdOfTextViews, ArrIdOfRadbuttons);
        Radbtnarr = getArrayOfRadBtn();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        for (int i = 0; i < getArrayIdTextViews().length; ++i) {
            if (getArrayIdTextViews()[i] == id) getArrayOfTextViews()[i].setBackgroundResource(R.drawable.textstyle_selected);
            else getArrayOfTextViews()[i].setBackgroundResource(R.drawable.textstyle);
        }
        if (id == R.id.fix_but_Vc) {
            getArrayOfRadBtn()[1].setChecked(false);
        }
        if (id == R.id.fix_but_rev) {
            getArrayOfRadBtn()[0].setChecked(false);
        }
    }
}