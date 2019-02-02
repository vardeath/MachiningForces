package com.example.stayi.MachiningForces;

import android.content.Context;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class FragmentAdaptor implements View.OnClickListener {
    public static final int Position_ONE = 0;
    public static final int Position_TWO = 1;

    private String TAG;
    private Context context;
    private FieldAdaptedObject[] FieldAdaptedObjects;
    private View view;
    private int current_selected_position;
    private List<HoldButtonRelatives> HoldButtonRelatives = new ArrayList<>();

    public FragmentAdaptor(List<FieldBaseObject> FieldBaseObject, View v, Context cont, String tag) {
        context = cont;
        TAG = tag;
        FieldAdaptedObjects = new FieldAdaptedObject[FieldBaseObject.size()];
        for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
            FieldAdaptedObjects[i] = new FieldAdaptedObject(FieldBaseObject.get(i), v, cont);
            FieldAdaptedObjects[i].setAccessToSelectState(true);
            if (i == 0) {
                FieldAdaptedObjects[i].setSelectedState(true);
                current_selected_position = 0;
            } else FieldAdaptedObjects[i].setSelectedState(false);
        }
        view = v;

        TextView[] T_Arr = getTextViewsArray();
        for (TextView x : T_Arr) {
            x.setOnClickListener(this);
        }
        setStorageValues();
    }

    private void setStorageValues() {
        nav_var_storage.init(context);
        if (nav_var_storage.getProperty(TAG, true)) {
            for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
                FieldAdaptedObjects[i].setFieldDoubleValue(Double.valueOf(nav_var_storage.getProperty(TAG + i)));
            }
        } else {
            nav_var_storage.addProperty(TAG, true);
            SaveInstanceState();
        }
    }

    CalculatingObject[] getCalculatingObjects(){
        CalculatingObject[] Calc = new CalculatingObject[FieldAdaptedObjects.length];
        for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
            Calc[i] = new CalculatingObject(FieldAdaptedObjects[i]);
        }
        return Calc;
    }

    public void SaveInstanceState() {
        nav_var_storage.init(context);
        for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
            nav_var_storage.addProperty(TAG + i, FieldAdaptedObjects[i].getFieldStringValue());
        }
    }

    private void setCurrentSelectedPosition(int pos){
        current_selected_position = pos;
    }

    int getCurrentSelectedPosition(){
        return current_selected_position;
    }

    public FieldAdaptedObject getSelectedFieldAdaptedObject() {
        return FieldAdaptedObjects[getCurrentSelectedPosition()];
    }

    private int getPositionById(int Id) {
        int i = 0;
        for (i = 0; i < FieldAdaptedObjects.length; ++i) {
            if (FieldAdaptedObjects[i].getFieldID() == Id) break;
        }
        return i;
    }

    /**
     * Назначает родственную кнопку ReHold для 2-х полей ввода.
     */

    public void setRelativeButton(List<HoldButtonRelatives> RelativeButtons) {
        HoldButtonRelatives = RelativeButtons;
        for (int i = 0; i < RelativeButtons.size(); ++i) {
            switch (RelativeButtons.get(i).getButtonPos()) {
                case ONE:
                    FieldAdaptedObjects[HoldButtonRelatives.get(i).getFirstFieldPosition()].setAccessToSelectState(false);
                    refreshInputFields();
                    break;
                case TWO:
                    FieldAdaptedObjects[HoldButtonRelatives.get(i).getSecondFieldPosition()].setAccessToSelectState(false);
                    refreshInputFields();
                    break;
            }
            view.findViewById(HoldButtonRelatives.get(i).getButtonId()).setOnClickListener(this);
        }
    }

    private TextView[] getTextViewsArray() {
        TextView[] Arr = new TextView[FieldAdaptedObjects.length];
        for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
            Arr[i] = FieldAdaptedObjects[i].getField();
        }
        return Arr;
    }

    private void refreshInputFields() {
        for (FieldAdaptedObject x : FieldAdaptedObjects) {
            x.setSelectedState(x.getSelectedState());
        }
    }

    private boolean setSelectedField(int id) {
        boolean result = false;
        for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
            if (FieldAdaptedObjects[i].getFieldID() == id) {
                if (FieldAdaptedObjects[i].getAllowedToSelectState()) {
                    FieldAdaptedObjects[getCurrentSelectedPosition()].setSelectedState(false);
                    FieldAdaptedObjects[i].setSelectedState(true);
                    setCurrentSelectedPosition(i);
                    result =  true;
                } else {
                    FieldAdaptedObjects[i].setSelectedState(false);
                }
            }
        }
        refreshInputFields();
        return result;
    }

    private void ReHold(int FieldFirstPosition, int FieldSecondPosition) {
        if (FieldAdaptedObjects[FieldFirstPosition].getAllowedToSelectState()) {
            FieldAdaptedObjects[FieldFirstPosition].setAccessToSelectState(false);
            FieldAdaptedObjects[FieldSecondPosition].setAccessToSelectState(true);
            if (getCurrentSelectedPosition() == FieldFirstPosition) {
                setSelectedField(FieldAdaptedObjects[FieldSecondPosition].getFieldID());
            }
        } else if (FieldAdaptedObjects[FieldSecondPosition].getAllowedToSelectState()) {
            FieldAdaptedObjects[FieldSecondPosition].setAccessToSelectState(false);
            FieldAdaptedObjects[FieldFirstPosition].setAccessToSelectState(true);
            if (getCurrentSelectedPosition() == FieldSecondPosition) {
                setSelectedField(FieldAdaptedObjects[FieldFirstPosition].getFieldID());
            }
        }
        refreshInputFields();
    }

    private void doButtonAction(int id) {
        for (int i = 0; i < HoldButtonRelatives.size(); ++i) {
            if (HoldButtonRelatives.get(i).getButtonId() == id) {
                ReHold(HoldButtonRelatives.get(i).getFirstFieldPosition(), HoldButtonRelatives.get(i).getSecondFieldPosition());
            }
        }
    }

    public void incrementSelectedPosition() {
        int pos = getCurrentSelectedPosition();
        int min_pos = 0;
        int max_pos = FieldAdaptedObjects.length - 1;
        while (true) {
            ++pos;
            if (pos > max_pos) pos = min_pos;
            boolean res = setSelectedField(FieldAdaptedObjects[pos].getFieldID());
            if (res) break;
        }
        refreshInputFields();
    }

    public void decrementSelectedPosition() {
        int pos = getCurrentSelectedPosition();
        int min_pos = 0;
        int max_pos = FieldAdaptedObjects.length - 1;
        while (true) {
            --pos;
            if (pos < min_pos) pos = max_pos;
            boolean res = setSelectedField(FieldAdaptedObjects[pos].getFieldID());
            if (res) break;
        }
        refreshInputFields();
    }

    public void makeSelectDefault() {
        setSelectedField(FieldAdaptedObjects[0].getFieldID());
    }

    public int getSelectedMaxLength() {
        return FieldAdaptedObjects[getCurrentSelectedPosition()].getBaseObject().getFieldLengthValue().getValue();
    }

    public void setZeroValuesAll() {
        for (FieldAdaptedObject x : FieldAdaptedObjects) {x.setZeroValue();}
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        setSelectedField(id);
        doButtonAction(id);
    }
}