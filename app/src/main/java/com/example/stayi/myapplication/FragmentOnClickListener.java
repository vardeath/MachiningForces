package com.example.stayi.myapplication;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public abstract class FragmentOnClickListener implements View.OnClickListener {

    private int FRAGMENT_ID;
    private int[] ArrayIdTextViews;
    private int[] ArrayIdOfRadiobuttons;
    private TextView[] ArrayOfTextViews;
    private RadioButton[] ArrayOfRadBtn;

    private boolean[] View_Select_Status;
    private boolean[] View_Activation;

    FragmentOnClickListener(int fragment_id, View view, int[] ArrIdOfTextViews, int[] ArrIdOfRadbuttons) {
        FRAGMENT_ID = fragment_id;
        ArrayIdTextViews = ArrIdOfTextViews;
        ArrayIdOfRadiobuttons = ArrIdOfRadbuttons;
        ArrayOfTextViews = new TextView[ArrayIdTextViews.length];
        ArrayOfRadBtn = new RadioButton[ArrayIdOfRadiobuttons.length];

        for (int i = 0; i < ArrIdOfTextViews.length; ++i) {
            ArrayOfTextViews[i] = view.findViewById(ArrIdOfTextViews[i]);
            ArrayOfTextViews[i].setOnClickListener(this);
        }
        for (int i = 0; i < ArrIdOfRadbuttons.length; ++i) {
            ArrayOfRadBtn[i] = view.findViewById(ArrIdOfRadbuttons[i]);
            ArrayOfRadBtn[i].setOnClickListener(this);
        }

        View_Select_Status = new boolean[ArrIdOfTextViews.length];
        View_Activation = new boolean[ArrIdOfTextViews.length];

        for (int i = 0; i < ArrIdOfTextViews.length; ++i) {
            View_Select_Status[i] = true;
            View_Activation[i] = true;
        }
    }

    @Override
    public void onClick(View v) {

    }

    int[] getArrayIdTextViews() {
        return ArrayIdTextViews;
    }

    TextView[] getArrayOfTextViews() {
        return ArrayOfTextViews;
    }

    int[] getArrayIdOfRadiobuttons() {
        return ArrayIdOfRadiobuttons;
    }

    RadioButton[] getArrayOfRadBtn() {
        return ArrayOfRadBtn;
    }

    RadioButton get_RadBtn_by_ID(int ID) {
        return ArrayOfRadBtn[get_RadBtn_position_by_ID(ID)];
    }

    private int get_RadBtn_position_by_ID(int ID) {
        int found_position = -1;
        for (int i = 0; i < ArrayIdOfRadiobuttons.length; ++i) {
            if (ArrayIdOfRadiobuttons[i] == ID) {
                found_position = i;
            }
        }
        return found_position;
    }

    int get_view_position_by_ID(int ID) {
        int found_position = -1;
        for (int i = 0; i < ArrayIdTextViews.length; ++i) {
            if (ArrayIdTextViews[i] == ID) {
                found_position = i;
            }
        }
        return found_position;
    }

    int get_selected_view_position() {
        int pos = 0;
        for (int i = 0; i < View_Select_Status.length; ++i) {
            if (View_Select_Status[i]) pos = i;
        }
        return pos;
    }

    public void refresh_views_select(){
        set_view_select(ArrayIdTextViews[get_selected_view_position()]);
    }

    void set_view_select(int ID) {
        int position = get_view_position_by_ID(ID);
        if (get_view_activation_state(position)) {
            for (int i = 0; i < View_Select_Status.length; ++i) {
                if (i == position) {
                    View_Select_Status[i] = true;
                    ArrayOfTextViews[i].setBackgroundResource(R.drawable.textstyle_selected);
                } else {
                    if (get_view_activation_state(i)) {
                        ArrayOfTextViews[i].setBackgroundResource(R.drawable.textstyle);
                    } else
                        ArrayOfTextViews[i].setBackgroundResource(R.drawable.textstyle_not_active);
                    View_Select_Status[i] = false;
                }
            }
        }
    }

    private int get_view_ID_by_position(int pos) {
        return ArrayIdTextViews[pos];
    }

    public TextView get_Selected_View() {
        return ArrayOfTextViews[get_selected_view_position()];
    }

    void set_view_activation_state(int ID, boolean state) {
        View_Activation[get_view_position_by_ID(ID)] = state;
    }


    private boolean get_view_activation_state(int pos) {
        return View_Activation[pos];
    }

    void increment_view_selected_position() {
        int pos = get_selected_view_position();
        int min_pos = 0;
        int max_pos = (ArrayIdTextViews.length - 1);
        int increment = 1;
        View_Select_Status[pos] = false;
        if ((pos + increment) > max_pos) pos = min_pos;
        else {
            if ((pos + increment * 2) <= max_pos) {
                if (!get_view_activation_state(pos + increment)) increment = increment + 1;
            }
            pos = pos + increment;
        }
        View_Select_Status[pos] = true;
        set_view_select(get_view_ID_by_position(pos));
    }

    void decrement_view_selected_position() {
        int pos = get_selected_view_position();
        int min_pos = 0;
        int max_pos = (ArrayIdTextViews.length - 1);
        int increment = -1;
        View_Select_Status[pos] = false;
        if ((pos + increment) < min_pos) pos = max_pos;
        else {
            if ((pos + increment * 2) >= min_pos) {
                if (!get_view_activation_state(pos + increment)) increment = increment * 2;
            }
            pos = pos + increment;
        }
        View_Select_Status[pos] = true;
        set_view_select(get_view_ID_by_position(pos));
    }
}