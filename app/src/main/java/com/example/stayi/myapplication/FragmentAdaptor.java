package com.example.stayi.myapplication;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class FragmentAdaptor {
    Context context;
    private FragmentFieldObject[] FFarray;
    private View view;
    private int current_selected_position;
    private List<ButtonRelatives> ButtonRelatives = new ArrayList<ButtonRelatives>();

    private void setCurrentSelectedPosition(int pos){
        current_selected_position = pos;
    }

    TextView getSelectedTextView() {
        return FFarray[getCurrentSelectedPosition()].getText_view();
    }

    public FragmentAdaptor(int[] TextViewId, InputFieldLength[] InputFieldLength, View v, Context cont) {
        context = cont;
        FFarray = new FragmentFieldObject[TextViewId.length];
        for (int i = 0; i < FFarray.length; ++i) {
            FFarray[i] = new FragmentFieldObject(TextViewId[i], v);
            FFarray[i].setAccessToSelectState(true);
            FFarray[i].setMaxLenght(InputFieldLength[i].getVal());
            if (i == 0) {
                FFarray[i].setSelectedState(true);
                current_selected_position = 0;
            } else FFarray[i].setSelectedState(false);
        }
        view = v;
    }

    private int getPositionById(int Id) {
        int i = 0;
        for (i = 0; i < FFarray.length; ++i) {
            if (FFarray[i].getTextViewId() == Id) break;
        }
        return i;
    }

    public void setRelativeButton(int buttin_id, int first_textview_id, int second_textview_id, int holded_position) {
        final int first_position_number = 1;
        final int second_position_number = 2;
        ButtonRelatives B = new ButtonRelatives(buttin_id);
        B.setTw_1_position(getPositionById(first_textview_id));
        B.setTw_2_position(getPositionById(second_textview_id));
        ButtonRelatives.add(B);

        switch (holded_position) {
            case first_position_number:
                FFarray[B.getFirstPosition()].setAccessToSelectState(false);
                refreshInputFields();
                break;
            case second_position_number:
                FFarray[B.getSecondPosition()].setAccessToSelectState(false);
                refreshInputFields();
                break;
        }
    }

    TextView[] getTextViewsArray() {
        TextView[] Arr = new TextView[FFarray.length];
        for (int i = 0; i < FFarray.length; ++i) {
            Arr[i] = FFarray[i].getText_view();
        }
        return Arr;
    }

    Button[] getButtonArray() {
        Button[] Arr = new Button[ButtonRelatives.size()];
        for (int i = 0; i < Arr.length; ++i) {
            Arr[i] = view.findViewById(ButtonRelatives.get(i).getButtonId());
        }
        return Arr;
    }

    private void refreshInputFields() {
        for (FragmentFieldObject x : FFarray) {
            x.setSelectedState(x.getSelectedState());
        }
    }

    private int getCurrentSelectedPosition() {
        return current_selected_position;
    }

    boolean setSelectedView(int id) {
        boolean result = false;
        for (int i = 0; i < FFarray.length; ++i) {
            if (FFarray[i].getTextViewId() == id) {
                if (FFarray[i].isAllowedToSelect()) {
                    FFarray[getCurrentSelectedPosition()].setSelectedState(false);
                    FFarray[i].setSelectedState(true);
                    setCurrentSelectedPosition(i);
                    result =  true;
                } else {
                    FFarray[i].setSelectedState(false);
                }
            }
        }
        refreshInputFields();
        return result;
    }

    private void ReHold(int TextViewFirstPosition, int TextViewSecondPosition) {
        if (FFarray[TextViewFirstPosition].isAllowedToSelect()) {
            FFarray[TextViewFirstPosition].setAccessToSelectState(false);
            FFarray[TextViewSecondPosition].setAccessToSelectState(true);
            if (getCurrentSelectedPosition() == TextViewFirstPosition) {
                setSelectedView(FFarray[TextViewSecondPosition].getTextViewId());
            }
        } else if (FFarray[TextViewSecondPosition].isAllowedToSelect()) {
            FFarray[TextViewSecondPosition].setAccessToSelectState(false);
            FFarray[TextViewFirstPosition].setAccessToSelectState(true);
            if (getCurrentSelectedPosition() == TextViewSecondPosition) {
                setSelectedView(FFarray[TextViewFirstPosition].getTextViewId());
            }
        }
        refreshInputFields();
    }

    void doButtonAction(int id) {
        for (int i = 0; i < ButtonRelatives.size(); ++i) {
            if (ButtonRelatives.get(i).getButtonId() == id) {
                ReHold(ButtonRelatives.get(i).getFirstPosition(), ButtonRelatives.get(i).getSecondPosition());
            }
        }
    }

    void incrementSelectedPosition(){
        int pos = getCurrentSelectedPosition();
        int min_pos = 0;
        int max_pos = FFarray.length - 1;
        while (true) {
            ++pos;
            if (pos > max_pos) pos = min_pos;
            boolean res = setSelectedView(FFarray[pos].getTextViewId());
            if (res) break;
        }
        refreshInputFields();
    }

    void decrementSelectedPosition(){
        int pos = getCurrentSelectedPosition();
        int min_pos = 0;
        int max_pos = FFarray.length - 1;
        while (true) {
            --pos;
            if (pos < min_pos) pos = max_pos;
            boolean res = setSelectedView(FFarray[pos].getTextViewId());
            if (res) break;
        }
        refreshInputFields();
    }

    void makeSelectDefault(){
        setSelectedView(FFarray[0].getTextViewId());
    }

    int getSelectedMaxLength() {
        return FFarray[getCurrentSelectedPosition()].getMaxLenght();
    }

    void setZeroValuesAll() {
        for (FragmentFieldObject x : FFarray) {x.setZeroValue();}
    }
}