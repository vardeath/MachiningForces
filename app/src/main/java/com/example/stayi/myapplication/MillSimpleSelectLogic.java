package com.example.stayi.myapplication;

import android.view.View;
import android.widget.TextView;

public class MillSimpleSelectLogic extends FragmentOnClickListener {

    public MillSimpleSelectLogic(int fragment_id, View view, int[] ArrIdOfTextViews, int[] ArrIdOfRadbuttons){
        super(fragment_id, view, ArrIdOfTextViews, ArrIdOfRadbuttons);
        set_view_activation_state(R.id.TW_n_rev, false);
        set_view_activation_state(R.id.TW_m_feed_editor, false);
        set_view_select(R.id.TW_Mill_Diameter);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        int selected_view_position = get_view_position_by_ID(id);
        if (selected_view_position >= 0) {
            set_view_select(id);
        }

        switch (id){
            case R.id.fix_but_Vc:
                get_RadBtn_by_ID(R.id.fix_but_Vc).setChecked(true);
                get_RadBtn_by_ID(R.id.fix_but_rev).setChecked(false);
                break;
            case R.id.fix_but_rev:
                get_RadBtn_by_ID(R.id.fix_but_Vc).setChecked(false);
                get_RadBtn_by_ID(R.id.fix_but_rev).setChecked(true);
                break;
            case R.id.fix_but_fz:
                get_RadBtn_by_ID(R.id.fix_but_fz).setChecked(true);
                get_RadBtn_by_ID(R.id.fix_but_F).setChecked(false);
                break;
            case R.id.fix_but_F:
                get_RadBtn_by_ID(R.id.fix_but_fz).setChecked(false);
                get_RadBtn_by_ID(R.id.fix_but_F).setChecked(true);
                break;
        }
    }
}