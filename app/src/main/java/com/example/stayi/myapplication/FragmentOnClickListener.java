package com.example.stayi.myapplication;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public abstract class FragmentOnClickListener implements View.OnClickListener {

    private int FRAGMENT_ID; //ID фрагмента, вызвавшего функцию.

    private int TextViewArrLength; //Длина массива с TextViews.

    private int RadioButtonArrLength; //Длина массива с RadioButtons.

    private int[] ArrayIdViews; //Массив ID views, имеющихся в фрагменте.

    private int[] ArrayIdOfRadioButtons; //Массив ID RadioButtons, имеющихся в фрагменте.

    private TextView[] ArrayOfTextViews; //Массив инициализированных обьектов TextView со слушателями.

    private RadioButton[] ArrayOfRadBtn; //Массив инициализированных обьектов RadioButton со слушателями.

    private boolean[] ViewsSelectStatus; //Массив данных о выделенных поизиях TextView.

    private boolean[] AllowedViewsToSelect; //Массив позиций, разрешенных/запрещнных к выделению.

    private double[] TextViewsDoubleValues; //Массив числовых значение TextView для передачи в модуль расчета.

    FragmentOnClickListener(int fragment_id, View view, int[] ArrIdOfTextViews, int[] ArrIdOfRadbuttons) {
        FRAGMENT_ID = fragment_id;
        TextViewArrLength = ArrIdOfTextViews.length;
        RadioButtonArrLength = ArrIdOfRadbuttons.length;
        ArrayIdViews = ArrIdOfTextViews;
        ArrayIdOfRadioButtons = ArrIdOfRadbuttons;
        ArrayOfTextViews = new TextView[TextViewArrLength];
        ArrayOfRadBtn = new RadioButton[RadioButtonArrLength];
        TextViewsDoubleValues = new double[TextViewArrLength];

        for (int i = 0; i < TextViewArrLength; ++i) {
            ArrayOfTextViews[i] = view.findViewById(ArrIdOfTextViews[i]);
            ArrayOfTextViews[i].setOnClickListener(this);
        }
        for (int i = 0; i < RadioButtonArrLength; ++i) {
            ArrayOfRadBtn[i] = view.findViewById(ArrIdOfRadbuttons[i]);
            ArrayOfRadBtn[i].setOnClickListener(this);
        }

        ViewsSelectStatus = new boolean[TextViewArrLength];
        AllowedViewsToSelect = new boolean[TextViewArrLength];

        for (int i = 0; i < TextViewArrLength; ++i) {
            ViewsSelectStatus[i] = false; // В массиве в момент инициализации нет выделенных view.
            AllowedViewsToSelect[i] = true; // В массиве в момент инициализации доступны к выделению все view.
        }
    }

    double[] getTextViewDoubleValuesArray(){
        for (int i = 0; i < TextViewArrLength; ++i) {
            TextViewsDoubleValues[i] = Double.valueOf(String.valueOf(getTextViewArray()[i].getText()));
        }
        return TextViewsDoubleValues;
    }

    int getFragmentId(){ return FRAGMENT_ID;} //Получть ID фрагмента, вызвавшего функцию.

    int[] getTextViewArrayID() { return ArrayIdViews;} //Получить массив ID TextViews.

    private int getTextViewIdByPosition(int pos) {return ArrayIdViews[pos];}  //Вернуть TextView ID по известной позиции в массиве.

    //Вернуть позицию TextView в массиве по известному ID.
    int getTextViewPositionById(int ID) {
        int found_position = -1; //Если нет такого ID, вернуть отрицательное число.
        for (int i = 0; i < TextViewArrLength; ++i) {
            if (ArrayIdViews[i] == ID) {
                found_position = i;
            }
        }
        return found_position;
    }

    //Вернуть позицию обьекта RadioButton в массиве по известнмоу ID.
    private int getRadioButtonPositionById(int ID) {
        int found_position = -1;
        for (int i = 0; i < RadioButtonArrLength; ++i) {
            if (ArrayIdOfRadioButtons[i] == ID) {
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
        return ArrayOfRadBtn[getRadioButtonPositionById(ID)];
    }

    void ClearAllTextViewsValues(){
        for (int i = 0; i < TextViewArrLength; ++i) {
            ArrayOfTextViews[i].setText("0");
        }
    }
    //Получить позицию выделенного обьекта TextView.
    private int getSelectedTextViewPosition() {
        int pos = 0;
        for (int i = 0; i < TextViewArrLength; ++i) {
            if (ViewsSelectStatus[i]) pos = i;
        }
        return pos;
    }

    int getTextViewSelectedId(){
        return ArrayIdViews[getSelectedTextViewPosition()];
    }
    void setTextViewSelectByPosition(int pos){
        setSelectTextView(getTextViewIdByPosition(pos));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        setSelectTextView(id);
    }

    private void RefreshTextViewsSelect(){
        setSelectTextView(ArrayIdViews[getSelectedTextViewPosition()]);
    }

    void setSelectTextView(int ID) {
        int position = getTextViewPositionById(ID);
        if (position >= 0) {
            if (getTextViewAllowToSelectState(position)) {
                for (int i = 0; i < TextViewArrLength; ++i) {
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
    }

    TextView getSelectedTextViewObject() {
        return ArrayOfTextViews[getSelectedTextViewPosition()];
    }

    void setTextViewAllowToSelectState(int ID, boolean state) {
        AllowedViewsToSelect[getTextViewPositionById(ID)] = state;
    }


    boolean getTextViewAllowToSelectState(int pos) {
        return AllowedViewsToSelect[pos];
    }

    void incrementTextViewSelectedPosition() {
        int pos = getSelectedTextViewPosition();
        int min_pos = 0;
        int max_pos = (TextViewArrLength - 1);
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
        int max_pos = (TextViewArrLength - 1);
        ViewsSelectStatus[pos] = false;
        do {
            --pos;
            if (pos < min_pos) pos = max_pos;
        } while (!getTextViewAllowToSelectState(pos));
        ViewsSelectStatus[pos] = true;
        setSelectTextView(getTextViewIdByPosition(pos));
    }

    void setAllowByChek(int id_radbtn1, int id_radbtn2, int id_textview1, int id_textview2){
        getRadioButtonObjectById(id_radbtn1).setChecked(true);
        getRadioButtonObjectById(id_radbtn2).setChecked(false);
        setTextViewAllowToSelectState(id_textview1, false);
        setTextViewAllowToSelectState(id_textview2, true);
        if (getTextViewPositionById(id_textview1) == getSelectedTextViewPosition()) {
            setSelectTextView(id_textview2);
        }
        RefreshTextViewsSelect();
    }
}