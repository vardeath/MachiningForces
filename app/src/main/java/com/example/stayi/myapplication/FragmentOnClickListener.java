package com.example.stayi.myapplication;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class FragmentOnClickListener implements View.OnClickListener {
    private int FRAGMENT_ID; //ID фрагмента, вызвавшего функцию.
    Button_hold_adaptor ButAdaptor;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public FragmentOnClickListener(int fragment_id, Button_hold_adaptor adapt) {

        int count = 0;
        FRAGMENT_ID = fragment_id;
        ButAdaptor = adapt;
        TextView[] T_Arr = ButAdaptor.getTextViewsArray();
        Button[] B_Arr = ButAdaptor.getButtonArray();
        for (TextView x : T_Arr) {x.setOnClickListener(this);}
        for (Button x : B_Arr) {x.setOnClickListener(this);}

        /*Arrays_Length = ArrIdOfTextViews.length;
        ArrayOfTextViews = new TextView[Arrays_Length];
        ArrayOfRadioButtons = new RadioButton[Arrays_Length];
        TextViewsDoubleValues = new double[Arrays_Length];

        for (int i = 0; i < Arrays_Length; ++i) { //Инициализация массива обьектов TextView и слушателей к ним.
            ArrayOfTextViews[i] = view.findViewById(ArrIdOfTextViews[i]);
            ArrayOfTextViews[i].setOnClickListener(this);
        }

        for (int i = 0; i < Arrays_Length; ++i) { //Инициализация массива обьектов RadioButton и слушателей к ним.
            if (ArrIdOfRadbuttons[i] != 0) {
                ArrayOfRadioButtons[i] = view.findViewById(ArrIdOfRadbuttons[i]);
                ArrayOfRadioButtons[i].setOnClickListener(this);
                ++count;
            } else {
                ArrayOfRadioButtons[i] = null;
            }
        }

        RadioButtonsCounter = new  RadioButton[count];
        int counter = 0;
        for (int i = 0; i < Arrays_Length; ++i) {
            if (ArrayOfRadioButtons[i] != null) {
                RadioButtonsCounter[counter] = ArrayOfRadioButtons[i];
                ++counter;
            }
        }

        ViewsSelectStatus = new boolean[Arrays_Length];
        AllowedViewsToSelect = new boolean[Arrays_Length];

        for (int i = 0; i < Arrays_Length; ++i) {
            ViewsSelectStatus[i] = i == 0;
            if (ArrayOfRadioButtons[i] == null) {
                AllowedViewsToSelect[i] = true;
            } else
                AllowedViewsToSelect[i] = !Objects.requireNonNull(ArrayOfRadioButtons[i]).isChecked();
        }
        RefreshTextViewsSelect(); //Применить установленные параметры.*/
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        ButAdaptor.setSelectedView(id);
        ButAdaptor.doButton_action(id);
    }
/*
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (isTextViewContainsId(id)) setSelectTextView(id);
        if (isRadioButtonContainsId(id)) setSelectRadioButton(id);

    }

    double[] getTextViewDoubleValuesArray() {
        for (int i = 0; i < Arrays_Length; ++i) {
            TextViewsDoubleValues[i] = Double.valueOf(String.valueOf(getTextViewArray()[i].getText()));
        }
        return TextViewsDoubleValues;
    }

    int getFragmentId() {
        return FRAGMENT_ID;
    } //Получть ID фрагмента, вызвавшего функцию.

    private int getTextViewIdByPosition(int pos) {
        return ArrayOfTextViews[pos].getId();
    }  //Вернуть TextView ID по известной позиции в массиве.

    //Вернуть позицию TextView в массиве по известному ID.
    int getTextViewPositionById(int ID) {
        int found_position = -1; //Если нет такого ID, вернуть отрицательное число.
        for (int i = 0; i < Arrays_Length; ++i) {
            if (ArrayOfTextViews[i].getId() == ID) {
                found_position = i;
            }
        }
        return found_position;
    }

    private boolean isTextViewContainsId(int ID) {
        boolean contains = false; //Если нет такого ID, вернуть отрицательное число.
        for (int i = 0; i < Arrays_Length; ++i) {
            if (ArrayOfTextViews[i].getId() == ID) {
                contains = true;
            }
        }
        return contains;
    }

    private boolean isRadioButtonContainsId(int ID) {
        boolean contains = false; //Если нет такого ID, вернуть отрицательное число.
        for (int i = 0; i < Arrays_Length; ++i) {
            if (ArrayOfRadioButtons[i] != null) {
                if (ArrayOfRadioButtons[i].getId() == ID) {
                    contains = true;
                }
            }
        }
        return contains;
    }


    //Вернуть позицию обьекта RadioButton в массиве по известнмоу ID.
    private int getRadioButtonPositionById(int ID) {
        int found_position = -1;
        for (int i = 0; i < Arrays_Length; ++i) {
            if (ArrayOfRadioButtons[i] != null && ArrayOfRadioButtons[i].getId() == ID) {
                found_position = i;
            }
        }
        return found_position;
    }


    TextView[] getTextViewArray() {
        return ArrayOfTextViews;
    }

    //Вернуть обьект RadioButton по известному ID.
    private RadioButton getRadioButtonObjectById(int ID) {
        return ArrayOfRadioButtons[getRadioButtonPositionById(ID)];
    }

    void ClearAllTextViewsValues() {
        for (int i = 0; i < Arrays_Length; ++i) {
            ArrayOfTextViews[i].setText("0");
        }
    }

    //Получить позицию выделенного обьекта TextView.
    private int getSelectedTextViewPosition() {
        int pos = 0;
        for (int i = 0; i < Arrays_Length; ++i) {
            if (ViewsSelectStatus[i]) pos = i;
        }
        return pos;
    }

    int getTextViewSelectedId() {
        return ArrayOfTextViews[getSelectedTextViewPosition()].getId();
    }

    void setTextViewSelectZeroPosition() {
        setSelectTextView(getTextViewIdByPosition(0));
    }

    private void RefreshTextViewsSelect() {
        setSelectTextView(ArrayOfTextViews[getSelectedTextViewPosition()].getId());
    }

    private void setSelectTextView(int ID) {
        int position = getTextViewPositionById(ID);
        if (getTextViewAllowToSelectState(position)) {
            for (int i = 0; i < Arrays_Length; ++i) {
                if (i == position) {
                    ViewsSelectStatus[i] = true;
                    ArrayOfTextViews[i].setBackgroundResource(R.drawable.textstyle_selected);
                } else {
                    if (getTextViewAllowToSelectState(i)) {
                        ArrayOfTextViews[i].setBackgroundResource(R.drawable.textstyle);
                    } else
                        ArrayOfTextViews[i].setBackgroundResource(R.drawable.textstyle_not_active);
                    ViewsSelectStatus[i] = false;
                }
            }
        }
    }

    TextView getSelectedTextViewObject() {
        return ArrayOfTextViews[getSelectedTextViewPosition()];
    }

    private void setTextViewAllowToSelectState(int ID, boolean state) {
        AllowedViewsToSelect[getTextViewPositionById(ID)] = state;
    }

    boolean getTextViewAllowToSelectState(int pos) {
        return AllowedViewsToSelect[pos];
    }

    void incrementTextViewSelectedPosition() {
        int pos = getSelectedTextViewPosition();
        int min_pos = 0;
        int max_pos = (Arrays_Length - 1);
        ViewsSelectStatus[pos] = false;
        do {
            ++pos;
            if (pos > max_pos) pos = min_pos;
        } while (!getTextViewAllowToSelectState(pos));
        ViewsSelectStatus[pos] = true;
        setSelectTextView(getTextViewIdByPosition(pos));
    }

    void decrementTextViewSelectedPosition() {
        int pos = getSelectedTextViewPosition();
        int min_pos = 0;
        int max_pos = (Arrays_Length - 1);
        ViewsSelectStatus[pos] = false;
        do {
            --pos;
            if (pos < min_pos) pos = max_pos;
        } while (!getTextViewAllowToSelectState(pos));
        ViewsSelectStatus[pos] = true;
        setSelectTextView(getTextViewIdByPosition(pos));
    }


    private int getRadioButtonIdByPosition(int pos){

        return ArrayOfRadioButtons[pos].getId();
    }

    private int getNextRadioButtonId(int id){
        int position = 0;
        int divider = 2;
        for (int i = 0; i < RadioButtonsCounter.length; ++i) {
            if (RadioButtonsCounter[i].getId() == id) position = i;
        }
        if (position == 0) ++position;
        else if (position % divider == 0) ++position;
        else --position;
        return RadioButtonsCounter[position].getId();
    }

    private void setSelectRadioButton(int id_radbtn) {
        int nextRadioButnId = getNextRadioButtonId(id_radbtn);
        getRadioButtonObjectById(id_radbtn).setChecked(true);
        getRadioButtonObjectById(nextRadioButnId).setChecked(false);
        setTextViewAllowToSelectState(getTextViewIdByPosition(getRadioButtonPositionById(id_radbtn)), false);
        setTextViewAllowToSelectState(getTextViewIdByPosition(getRadioButtonPositionById(nextRadioButnId)), true);
        if (getSelectedTextViewPosition() == getRadioButtonPositionById(id_radbtn)) {
            setSelectTextView(getTextViewIdByPosition(getRadioButtonPositionById(nextRadioButnId)));
        }
        RefreshTextViewsSelect();
    }*/
}