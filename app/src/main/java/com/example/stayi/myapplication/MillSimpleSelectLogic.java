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

        switch (id){
            case R.id.fix_but_Vc:
                getArrayOfRadBtn()[find_position_RadBtn_id(R.id.fix_but_Vc)].setChecked(true);
                getArrayOfRadBtn()[find_position_RadBtn_id(R.id.fix_but_rev)].setChecked(false);
                break;
            case R.id.fix_but_rev:
                getArrayOfRadBtn()[find_position_RadBtn_id(R.id.fix_but_Vc)].setChecked(false);
                getArrayOfRadBtn()[find_position_RadBtn_id(R.id.fix_but_rev)].setChecked(true);
                break;
            case R.id.fix_but_fz:
                getArrayOfRadBtn()[find_position_RadBtn_id(R.id.fix_but_fz)].setChecked(true);
                getArrayOfRadBtn()[find_position_RadBtn_id(R.id.fix_but_F)].setChecked(false);
                break;
            case R.id.fix_but_F:
                getArrayOfRadBtn()[find_position_RadBtn_id(R.id.fix_but_fz)].setChecked(false);
                getArrayOfRadBtn()[find_position_RadBtn_id(R.id.fix_but_F)].setChecked(true);
                break;
        }
    }
}