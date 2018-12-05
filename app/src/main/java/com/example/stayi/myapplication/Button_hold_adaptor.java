package com.example.stayi.myapplication;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Button_hold_adaptor {
    Context context;
    private Fragment_data[] Fr_array;
    private View view;

    public Button_hold_adaptor(int[] TextViews, View v, Context cont) {
        context = cont;
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

    private boolean isContainsSameId(List<Integer> list, Integer id) {
        boolean result = false;
        for (Integer x : list) if (x.equals(id)) {result = true;}
        return result;
    }

    Button[] getButtonArray(){
        List<Integer> Ids = getButtonIdArray();
        Button[] Arr = new Button[Ids.size()];
        for (int i = 0; i < Arr.length; ++i) {
            Arr[i] = view.findViewById(Ids.get(i));
        }
        return Arr;
    }

    private List<Integer> getButtonIdArray() {
        int length = 0;
        List<Integer> x = new ArrayList<Integer>();
        for (int i = 0; i < Fr_array.length; ++i) {
            int current_id;
            if (Fr_array[i].getRelative_button_id() != 0) {
                current_id = Fr_array[i].getRelative_button_id();
                if (!isContainsSameId(x, current_id)) {x.add(current_id);}
            }
        }
        return x;
    }

    void refresh_views() {
        for (Fragment_data x : Fr_array) {
            x.setSelected_state(x.getSelected_state());
        }
    }

    private int getCurrentSelectedPosition(){
        int pos = 0;
        for (int i = 0; i < Fr_array.length; ++i) {
            if (Fr_array[i].getSelected_state()) pos = i;
        }
        return pos;
    }

    void setSelectedView(int id) {
        for (int i = 0; i < Fr_array.length; ++i) {
            if (Fr_array[i].getTextView_id() == id) {
                if (Fr_array[i].is_allowed_to_select()){
                    Fr_array[getCurrentSelectedPosition()].setSelected_state(false);
                    Fr_array[i].setSelected_state(true);
                    refresh_views();
                }
            }
        }
    }

    List<TextView> getRelativeTextViewArray(int button_id) {
        List<TextView> x = new ArrayList<TextView>();
        for (int i = 0; i < Fr_array.length; ++i) {
            if (Fr_array[i].getRelative_button_id() == button_id) {
                x.add(Fr_array[i].getText_view());
            }
        }
        return x;
    }

    void doButton_action(int id) {
        List<Integer> x = getButtonIdArray();

        /*for (Integer i : x) if (i.equals(id)) {
            Toast.makeText(context, "pressed", Toast.LENGTH_SHORT).show();
        }*/
    }
}