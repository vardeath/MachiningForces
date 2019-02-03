package com.example.stayi.MachiningForces.ConditionsModule;

import android.content.Context;
import android.view.View;

import com.example.stayi.MachiningForces.Storage;
import java.util.ArrayList;
import java.util.List;

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
    private String TAG; //Уникальный тег, для генерации имен при записи/чтении в хранилище (Storage).
    private FieldAdaptedObject[] FieldAdaptedObjects; //Массив обьектов с информацией о поле ввода, его состоянии выделения, дуступе к изменению состояния выделения, состоянии блокировки.
    private List<HoldButtonRelatives> HoldButtonRelatives = new ArrayList<>(); //Массив с данными ID кнопки блокировки поля, и родственных полей ввода.
    private int current_selected_position; //Позиция текущего выделенного поля ввода.

    public FragmentAdaptor(List<FieldBaseObject> FieldBaseObject, View v, Context cont, String tag) {
        context = cont;
        view = v;
        TAG = tag;
        FieldAdaptedObjects = new FieldAdaptedObject[FieldBaseObject.size()];
        for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
            FieldAdaptedObjects[i] = new FieldAdaptedObject(FieldBaseObject.get(i), v, cont); //Инициализация адаптированных обьектов.
            FieldAdaptedObjects[i].setAccessToSelectState(true); //Разрешаение доступа к выделению поля ввода.
            FieldAdaptedObjects[i].getField().setOnClickListener(this); //Слушатель поля ввода.
            if (i == 0) {
                FieldAdaptedObjects[i].setSelectedState(true); //Первое поле ввода выделено по умолчанию.
                current_selected_position = 0;
            } else FieldAdaptedObjects[i].setSelectedState(false); //Поле ввода не выделено.
        }
        restoreStorageValues();
    }

    private void restoreStorageValues() { //Восстановить значения полей ввода из хранилища.
        Storage.init(context);
        if (Storage.getProperty(TAG, true)) {
            for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
                FieldAdaptedObjects[i].setFieldDoubleValue(Double.valueOf(Storage.getProperty(TAG + i)));
            }
        } else {
            Storage.addProperty(TAG, true); //Первичная инициализация переменных в хранилище.
            setStorageValues();
        }
    }

    CalculatingObject[] getCalculatingObjects() { //Обьект передает набор параметров для математического расчета.
        CalculatingObject[] Calc = new CalculatingObject[FieldAdaptedObjects.length];
        for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
            Calc[i] = new CalculatingObject(FieldAdaptedObjects[i]);
        }
        return Calc;
    }

    void setStorageValues() { //Записать значения в хранилище.
        Storage.init(context);
        for (int i = 0; i < FieldAdaptedObjects.length; ++i) {
            Storage.addProperty(TAG + i, FieldAdaptedObjects[i].getFieldStringValue());
        }
    }

    private void setCurrentSelectedPosition(int pos){
        current_selected_position = pos;
    }

    int getCurrentSelectedPosition(){
        return current_selected_position;
    }

    FieldAdaptedObject getSelectedFieldAdaptedObject() {
        return FieldAdaptedObjects[getCurrentSelectedPosition()];
    }

    /**
     * Назначает родственную кнопку ReHold для 2-х полей ввода.
     */
    public void setRelativeButton(List<HoldButtonRelatives> RelativeButtons) {
        HoldButtonRelatives = RelativeButtons;
        for (int i = 0; i < RelativeButtons.size(); ++i) {
            switch (RelativeButtons.get(i).getLockedFieldPosition()) {
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

    private void refreshInputFields() { //Обновить экран.
        for (FieldAdaptedObject x : FieldAdaptedObjects) {
            x.setSelectedState(x.getSelectedState());
        }
    }

    private boolean setSelectedField(int id) { //Выделить поле ввода.
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

    void incrementSelectedPosition() {
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

    void decrementSelectedPosition() {
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

    void makeSelectDefault() {
        setSelectedField(FieldAdaptedObjects[0].getFieldID());
    }

    int getSelectedMaxLength() {
        return FieldAdaptedObjects[getCurrentSelectedPosition()].getBaseObject().getFieldLengthValue().getValue();
    }

    void setZeroValuesAll() {
        for (FieldAdaptedObject x : FieldAdaptedObjects) {x.setZeroValue();}
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        setSelectedField(id);
        doButtonAction(id);
    }
}