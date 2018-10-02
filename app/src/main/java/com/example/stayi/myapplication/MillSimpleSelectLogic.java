package com.example.stayi.myapplication;

import android.view.View;

public class MillSimpleSelectLogic extends FragmentOnClickListener {

    public MillSimpleSelectLogic(int fragment_id, View view, int[] ArrIdOfTextViews, int[] ArrIdOfRadbuttons){
        super(fragment_id, view, ArrIdOfTextViews, ArrIdOfRadbuttons);
        setTextViewAllowToSelectState(R.id.TW_n_rev, false);
        setTextViewAllowToSelectState(R.id.TW_m_feed_editor, false);
        setSelectTextView(R.id.TW_Mill_Diameter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();

        switch (id){
            case R.id.fix_but_Vc:
                setAllowByChek(R.id.fix_but_Vc, R.id.fix_but_rev, R.id.TW_vc_speed, R.id.TW_n_rev);
                break;
            case R.id.fix_but_rev:
                setAllowByChek(R.id.fix_but_rev, R.id.fix_but_Vc, R.id.TW_n_rev, R.id.TW_vc_speed);
                break;
            case R.id.fix_but_fz:
                setAllowByChek(R.id.fix_but_fz, R.id.fix_but_F, R.id.TW_t_feed_editor, R.id.TW_m_feed_editor);
                break;
            case R.id.fix_but_F:
                setAllowByChek(R.id.fix_but_F, R.id.fix_but_fz, R.id.TW_m_feed_editor, R.id.TW_t_feed_editor);
                break;
        }
    }
}