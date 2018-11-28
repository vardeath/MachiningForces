package com.example.stayi.myapplication;

import android.widget.TextView;
import android.view.View;

public class Button_hold_adaptor {
    private Fragment_data[] Fr_array;
    private View view;

    public Button_hold_adaptor(int[] TextViews, View v) {
        Fr_array = new Fragment_data[TextViews.length];
        for (int i = 0; i < Fr_array.length; ++i) {
            Fr_array[i] = new Fragment_data(TextViews[i], v);
            Fr_array[i].setRelative_button_id(0);
            Fr_array[i].setAllowed_to_select(true);
            if (i == 0) Fr_array[i].setSelected_state(true);
            else Fr_array[i].setSelected_state(false);
        }

        view = v;
    }

    ;

    int getPositionById(int Id) {
        int i = 0;
        for (i = 0; i < Fr_array.length; ++i) {
            if (Fr_array[i].getTextView_id() == Id) break;
        }
        return i;
    }

    public void setRelativeButton(int buttin_id, int first_textview_id, int second_textview_id, int holded_position) {
        final int first_position_number = 1;
        final int second_position_number = 2;
        for (int i = 0; i < Fr_array.length; ++i) {
            if (Fr_array[i].getTextView_id() == first_textview_id || Fr_array[i].getTextView_id() == second_textview_id) {
                Fr_array[i].setRelative_button_id(buttin_id);
            }
            switch (holded_position) {
                case first_position_number:
                    Fr_array[getPositionById(first_textview_id)].setAllowed_to_select(false);
                    refresh_views();
                    break;
                case second_position_number:
                    Fr_array[getPositionById(second_textview_id)].setAllowed_to_select(false);
                    refresh_views();
                    break;
            }
        }
    }

    TextView[] getTextViewsArray() {
        TextView[] Arr = new TextView[Fr_array.length];
        for (int i = 0; i < Fr_array.length; ++i) {
            Arr[i] = Fr_array[i].getText_view();
        }
        return Arr;
    }

    void refresh_views() {
        for (Fragment_data x : Fr_array) {
            x.setSelected_state(x.getSelected_state());
        }
    }

    void setSelectedView(int id) {
        for (Fragment_data x : Fr_array) {
            if (x.getAllowed_to_select()) {
                if (x.getTextView_id() == id) {
                    x.setSelected_state(true);
                } else {
                    x.setSelected_state(false);
                }
            }
        }
    }
}