package com.example.stayi.MachiningForces.ConditionsModule;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.stayi.MachiningForces.R;
import com.example.stayi.MachiningForces.Storage;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import static com.example.stayi.MachiningForces.Enumerations.ConditionsPreset.MillDetail;

public class FragmentAdaptor implements View.OnClickListener {

    /**
     * Класс обеспечивает слушателями все необходимые элементы фрагмента (кнопки и поля ввода),
     * и обеспечивает реакцию на их изменение пользователем как вручную, так и через кастомную клавиатуру.
     * Основные действия:
     * setOnClickListener() - поставить слушатель.
     * setSelectedState() - установить состояние выделения поля ввода.
     * setAccessToSelectState() - установить разрешение к изменению состояния выделения.
     **/
    private Context context;
    private View view;
    private String mTAG; //Уникальный тег, для генерации имен при записи/чтении в хранилище (Storage).
    private FieldAdaptedObject[] mFieldAdaptedObjects; //Массив обьектов с информацией о поле ввода, его состоянии выделения, дуступе к изменению состояния выделения, состоянии блокировки.
    private List<HoldButtonRelatives> mHoldButtonRelatives = new ArrayList<>(); //Массив с данными ID кнопки блокировки поля, и родственных полей ввода.
    private int current_selected_position; //Позиция текущего выделенного поля ввода.
    private BottomSheetBehavior behavior;

    public FragmentAdaptor(List<FieldBaseObject> FieldBaseObject, View v, Context context, String tag) {
        this.context = context;
        view = v;
        mTAG = tag;
        mFieldAdaptedObjects = new FieldAdaptedObject[FieldBaseObject.size()];
        for (int i = 0; i < mFieldAdaptedObjects.length; ++i) {
            mFieldAdaptedObjects[i] = new FieldAdaptedObject(FieldBaseObject.get(i), v, context); //Инициализация адаптированных обьектов.
            if (mFieldAdaptedObjects[i].isInput()) {
                mFieldAdaptedObjects[i].setAccessToSelectState(true);//Разрешаение доступа к выделению поля ввода.
                mFieldAdaptedObjects[i].getField().setOnClickListener(this);
            }
            if (i == 0) {
                mFieldAdaptedObjects[i].setSelectedState(true); //Первое поле ввода выделено по умолчанию.
                current_selected_position = 0;
            } else mFieldAdaptedObjects[i].setSelectedState(false); //Поле ввода не выделено.
        }
        initializeBottomSheet();
        restoreStorageValues();
    }

    private void initializeBottomSheet() {
        Activity activity = (Activity) context;
        LinearLayout llBottomSheet = activity.findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(llBottomSheet);
        if (String.valueOf(MillDetail).equals(mTAG))  behavior.setPeekHeight(0);
        else behavior.setPeekHeight(700);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void restoreStorageValues() { //Восстановить значения полей ввода из хранилища.
        Storage.init(context);
        if (Storage.getProperty(mTAG, true)) {
            for (int i = 0; i < mFieldAdaptedObjects.length; ++i) {
                mFieldAdaptedObjects[i].setFieldDoubleValue(Double.valueOf(Storage.getProperty(mTAG + i)));
            }
        } else {
            Storage.addProperty(mTAG, true); //Первичная инициализация переменных в хранилище.
            setStorageValues();
        }
    }

    CalculatingObject[] getCalculatingObjects() { //Обьект передает набор параметров для математического расчета.
        CalculatingObject[] Calc = new CalculatingObject[mFieldAdaptedObjects.length];
        for (int i = 0; i < mFieldAdaptedObjects.length; ++i) {
            Calc[i] = new CalculatingObject(mFieldAdaptedObjects[i]);
        }
        return Calc;
    }

    void setStorageValues() { //Записать значения в хранилище.
        Storage.init(context);
        for (int i = 0; i < mFieldAdaptedObjects.length; ++i) {
            Storage.addProperty(mTAG + i, mFieldAdaptedObjects[i].getFieldStringValue());
        }
    }

    private void setCurrentSelectedPosition(int pos){
        current_selected_position = pos;
    }

    int getCurrentSelectedPosition(){
        return current_selected_position;
    }

    FieldAdaptedObject getSelectedFieldAdaptedObject() {
        return mFieldAdaptedObjects[getCurrentSelectedPosition()];
    }

    /**
     * Назначает родственную кнопку ReHold для 2-х полей ввода.
     */
    public void setRelativeButton(List<HoldButtonRelatives> RelativeButtons) {
        mHoldButtonRelatives = RelativeButtons;
        for (int i = 0; i < RelativeButtons.size(); ++i) {
            switch (RelativeButtons.get(i).getLockedFieldPosition()) {
                case ONE:
                    mFieldAdaptedObjects[mHoldButtonRelatives.get(i).getFirstFieldPosition()].setAccessToSelectState(false);
                    refreshInputFields();
                    break;
                case TWO:
                    mFieldAdaptedObjects[mHoldButtonRelatives.get(i).getSecondFieldPosition()].setAccessToSelectState(false);
                    refreshInputFields();
                    break;
            }
            view.findViewById(mHoldButtonRelatives.get(i).getButtonId()).setOnClickListener(this);
        }
    }

    private void refreshInputFields() { //Обновить экран.
        for (FieldAdaptedObject x : mFieldAdaptedObjects) {
            x.setSelectedState(x.getSelectedState());
        }
    }

    private boolean setSelectedField(int id) { //Выделить поле ввода.
        boolean result = false;
        for (int i = 0; i < mFieldAdaptedObjects.length; ++i) {
            if (mFieldAdaptedObjects[i].getFieldID() == id) {
                if (!mFieldAdaptedObjects[i].isInput()) return false;
                if (mFieldAdaptedObjects[i].getAllowedToSelectState()) {
                    mFieldAdaptedObjects[getCurrentSelectedPosition()].setSelectedState(false);
                    mFieldAdaptedObjects[i].setSelectedState(true);
                    setCurrentSelectedPosition(i);
                    result =  true;
                } else {
                    mFieldAdaptedObjects[i].setSelectedState(false);
                }
            }
        }
        refreshInputFields();
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        return result;
    }

    private void ReHold(int FieldFirstPosition, int FieldSecondPosition) {
        if (mFieldAdaptedObjects[FieldFirstPosition].getAllowedToSelectState()) {
            mFieldAdaptedObjects[FieldFirstPosition].setAccessToSelectState(false);
            mFieldAdaptedObjects[FieldSecondPosition].setAccessToSelectState(true);
            if (getCurrentSelectedPosition() == FieldFirstPosition) {
                setSelectedField(mFieldAdaptedObjects[FieldSecondPosition].getFieldID());
            }
        } else if (mFieldAdaptedObjects[FieldSecondPosition].getAllowedToSelectState()) {
            mFieldAdaptedObjects[FieldSecondPosition].setAccessToSelectState(false);
            mFieldAdaptedObjects[FieldFirstPosition].setAccessToSelectState(true);
            if (getCurrentSelectedPosition() == FieldSecondPosition) {
                setSelectedField(mFieldAdaptedObjects[FieldFirstPosition].getFieldID());
            }
        }
        refreshInputFields();
    }

    private void doButtonAction(int id) {
        for (int i = 0; i < mHoldButtonRelatives.size(); ++i) {
            if (mHoldButtonRelatives.get(i).getButtonId() == id) {
                ReHold(mHoldButtonRelatives.get(i).getFirstFieldPosition(), mHoldButtonRelatives.get(i).getSecondFieldPosition());
            }
        }
    }

    void incrementSelectedPosition() {
        int pos = getCurrentSelectedPosition();
        int min_pos = 0;
        int max_pos = mFieldAdaptedObjects.length - 1;
        while (true) {
            ++pos;
            if (pos > max_pos) pos = min_pos;
            boolean res = setSelectedField(mFieldAdaptedObjects[pos].getFieldID());
            if (res) break;
        }
        refreshInputFields();
    }

    void decrementSelectedPosition() {
        int pos = getCurrentSelectedPosition();
        int min_pos = 0;
        int max_pos = mFieldAdaptedObjects.length - 1;
        while (true) {
            --pos;
            if (pos < min_pos) pos = max_pos;
            boolean res = setSelectedField(mFieldAdaptedObjects[pos].getFieldID());
            if (res) break;
        }
        refreshInputFields();
    }

    void makeSelectDefault() {
        setSelectedField(mFieldAdaptedObjects[0].getFieldID());
    }

    int getSelectedMaxLength() {
        return mFieldAdaptedObjects[getCurrentSelectedPosition()].getBaseObject().getFieldLengthValue().getValue();
    }

    void setZeroValuesAll() {
        for (FieldAdaptedObject x : mFieldAdaptedObjects) {
            x.setZeroValue();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        setSelectedField(id);
        doButtonAction(id);
    }
}