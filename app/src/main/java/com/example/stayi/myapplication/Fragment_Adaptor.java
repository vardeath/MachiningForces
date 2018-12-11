package com.example.stayi.myapplication;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Adaptor {
    Context context;
    private Fragment_data[] Fr_array;
    private View view;
    private int current_selected_position;
    private List<ButtonRelatives> ButRel_List = new ArrayList<ButtonRelatives>();

    private void setCurrent_selected_position(int pos){
        current_selected_position = pos;
    }

    TextView getSelectedTextView() {
        return Fr_array[getCurrentSelectedPosition()].getText_view();
    }

    public Fragment_Adaptor(int[] TextViews, int[] Max_lenght, View v, Context cont) {
        context = cont;
        Fr_array = new Fragment_data[TextViews.length];
        for (int i = 0; i < Fr_array.length; ++i) {
            Fr_array[i] = new Fragment_data(TextViews[i], v);
            Fr_array[i].setAccess_to_select(true);
            Fr_array[i].setMax_lenght(Max_lenght[i]);
            if (i == 0) {
                Fr_array[i].setSelected_state(true);
                current_selected_position = 0;
            } else Fr_array[i].setSelected_state(false);
        }
        view = v;
    }

    private int getPositionById(int Id) {
        int i = 0;
        for (i = 0; i < Fr_array.length; ++i) {
            if (Fr_array[i].getTextView_id() == Id) break;
        }
        return i;
    }

    public void setRelativeButton(int buttin_id, int first_textview_id, int second_textview_id, int holded_position) {
        final int first_position_number = 1;
        final int second_position_number = 2;
        ButtonRelatives B = new ButtonRelatives(buttin_id);
        B.setTw_1_position(getPositionById(first_textview_id));
        B.setTw_2_position(getPositionById(second_textview_id));
        ButRel_List.add(B);

        switch (holded_position) {
            case first_position_number:
                Fr_array[B.getTw_1_position()].setAccess_to_select(false);
                refresh_views();
                break;
            case second_position_number:
                Fr_array[B.getTw_2_position()].setAccess_to_select(false);
                refresh_views();
                break;
        }
    }

    TextView[] getTextViewsArray() {
        TextView[] Arr = new TextView[Fr_array.length];
        for (int i = 0; i < Fr_array.length; ++i) {
            Arr[i] = Fr_array[i].getText_view();
        }
        return Arr;
    }

    Button[] getButtonArray() {
        Button[] Arr = new Button[ButRel_List.size()];
        for (int i = 0; i < Arr.length; ++i) {
            Arr[i] = view.findViewById(ButRel_List.get(i).getButtonId());
        }
        return Arr;
    }

    private void refresh_views() {
        for (Fragment_data x : Fr_array) {
            x.setSelected_state(x.getSelected_state());
        }
    }

    private int getCurrentSelectedPosition() {
        return current_selected_position;
    }

    boolean setSelectedView(int id) {
        boolean result = false;
        for (int i = 0; i < Fr_array.length; ++i) {
            if (Fr_array[i].getTextView_id() == id) {
                if (Fr_array[i].is_allowed_to_select()) {
                    Fr_array[getCurrentSelectedPosition()].setSelected_state(false);
                    Fr_array[i].setSelected_state(true);
                    setCurrent_selected_position(i);
                    result =  true;
                } else {
                    Fr_array[i].setSelected_state(false);
                }
            }
        }
        refresh_views();
        return result;
    }

    private void ReHold(int TextView_1_pos, int TextView_2_pos) {
        if (Fr_array[TextView_1_pos].is_allowed_to_select()) {
            Fr_array[TextView_1_pos].setAccess_to_select(false);
            Fr_array[TextView_2_pos].setAccess_to_select(true);
            if (getCurrentSelectedPosition() == TextView_1_pos) {
                setSelectedView(Fr_array[TextView_2_pos].getTextView_id());
            }
        } else if (Fr_array[TextView_2_pos].is_allowed_to_select()) {
            Fr_array[TextView_2_pos].setAccess_to_select(false);
            Fr_array[TextView_1_pos].setAccess_to_select(true);
            if (getCurrentSelectedPosition() == TextView_2_pos) {
                setSelectedView(Fr_array[TextView_1_pos].getTextView_id());
            }
        }
        refresh_views();
    }

    void doButton_action(int id) {
        for (int i = 0; i < ButRel_List.size(); ++i) {
            if (ButRel_List.get(i).getButtonId() == id) {
                ReHold(ButRel_List.get(i).getTw_1_position(), ButRel_List.get(i).getTw_2_position());
            }
        }
    }

    void incrementSelectedPosition(){
        int pos = getCurrentSelectedPosition();
        int min_pos = 0;
        int max_pos = Fr_array.length - 1;
        while (true) {
            ++pos;
            if (pos > max_pos) pos = min_pos;
            boolean res = setSelectedView(Fr_array[pos].getTextView_id());
            if (res) break;
        }
        refresh_views();
    }

    void decrementSelectedPosition(){
        int pos = getCurrentSelectedPosition();
        int min_pos = 0;
        int max_pos = Fr_array.length - 1;
        while (true) {
            --pos;
            if (pos < min_pos) pos = max_pos;
            boolean res = setSelectedView(Fr_array[pos].getTextView_id());
            if (res) break;
        }
        refresh_views();
    }

    void makeSelectDefault(){
        setSelectedView(Fr_array[0].getTextView_id());
    }

    int getSelectedMaxLength() {
        return Fr_array[getCurrentSelectedPosition()].getMax_lenght();
    }

    void setZeroValuesAll() {
        for (Fragment_data x : Fr_array) {x.setZeroValue();}
    }
}