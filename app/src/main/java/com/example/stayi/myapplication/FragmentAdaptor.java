package com.example.stayi.myapplication;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import com.example.stayi.myapplication.FragmentField.FieldHoldPosition;
import java.util.ArrayList;
import java.util.List;

public class FragmentAdaptor implements View.OnClickListener {
    private Context context;
    private FieldAdaptedObject[] FFarray;
    private View view;
    private int current_selected_position;
    private List<ButtonRelatives> ButtonRelatives = new ArrayList<ButtonRelatives>();

    private void setCurrentSelectedPosition(int pos){
        current_selected_position = pos;
    }

    TextView getSelectedTextView() {
        return FFarray[getCurrentSelectedPosition()].getText_view();
    }

    public FragmentAdaptor(List<FieldBaseObject> FieldBaseObject, View v, Context cont) {
        context = cont;
        FFarray = new FieldAdaptedObject[FieldBaseObject.size()];
        for (int i = 0; i < FFarray.length; ++i) {
            FFarray[i] = new FieldAdaptedObject(FieldBaseObject.get(i), v);
            FFarray[i].setAccessToSelectState(true);
            if (i == 0) {
                FFarray[i].setSelectedState(true);
                current_selected_position = 0;
            } else FFarray[i].setSelectedState(false);
        }
        view = v;

        TextView[] T_Arr = getTextViewsArray();
        for (TextView x : T_Arr) {x.setOnClickListener(this);}
    }

    private int getPositionById(int Id) {
        int i = 0;
        for (i = 0; i < FFarray.length; ++i) {
            if (FFarray[i].getTextViewId() == Id) break;
        }
        return i;
    }

    public void setRelativeButton(int ButtonID, int FirstFieldID, int SecondFieldID, FieldHoldPosition HoldedPositionDefault) {
        ButtonRelatives Object = new ButtonRelatives(ButtonID);
        Object.setFirstFieldPosition(getPositionById(FirstFieldID));
        Object.setSecondFieldPosition(getPositionById(SecondFieldID));
        ButtonRelatives.add(Object);

        switch (HoldedPositionDefault) {
            case One:
                FFarray[Object.getFirstFieldPosition()].setAccessToSelectState(false);
                refreshInputFields();
                break;
            case Two:
                FFarray[Object.getSecondFieldPosition()].setAccessToSelectState(false);
                refreshInputFields();
                break;
        }
        view.findViewById(ButtonID).setOnClickListener(this);
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
        for (FieldAdaptedObject x : FFarray) {
            x.setSelectedState(x.getSelectedState());
        }
    }

    private int getCurrentSelectedPosition() {
        return current_selected_position;
    }

    private boolean setSelectedView(int id) {
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

    private void doButtonAction(int id) {
        for (int i = 0; i < ButtonRelatives.size(); ++i) {
            if (ButtonRelatives.get(i).getButtonId() == id) {
                ReHold(ButtonRelatives.get(i).getFirstFieldPosition(), ButtonRelatives.get(i).getSecondFieldPosition());
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
        return FFarray[getCurrentSelectedPosition()].getBaseObject().getFieldLengthValue().getValue();
    }

    void setZeroValuesAll() {
        for (FieldAdaptedObject x : FFarray) {x.setZeroValue();}
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        setSelectedView(id);
        doButtonAction(id);
    }
}