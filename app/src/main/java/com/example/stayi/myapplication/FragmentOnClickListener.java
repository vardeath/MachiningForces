package com.example.stayi.myapplication;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public abstract class FragmentOnClickListener implements View.OnClickListener {

    private int FRAGMENT_ID;

    int getFragmentId(){
        return FRAGMENT_ID;
    }
    //Массив ID views, имеющихся в фрагменте.
    private int[] ArrayIdViews;

    public int[] getTextViewArrayID() {
        return ArrayIdViews;
    }

    //Вернуть TextView ID по известной позиции в массиве.
    private int getTextViewIdByPosition(int pos) {
        return ArrayIdViews[pos];
    }

    //Вернуть позицию TextView в массиве по известному ID.
    private int getTextViewPositionById(int ID) {
        int found_position = -1; //Если нет такого ID, вернуть отрицательное число.
        for (int i = 0; i < ArrayIdViews.length; ++i) {
            if (ArrayIdViews[i] == ID) {
                found_position = i;
            }
        }
        return found_position;
    }

    //Массив ID RadioButtons, имеющихся в фрагменте.
    private int[] ArrayIdOfRadiobuttons;

    //Вернуть позицию обьекта RadioButton в массиве по известнмоу ID.
    private int getRadioButtonPositionById(int ID) {
        int found_position = -1;
        for (int i = 0; i < ArrayIdOfRadiobuttons.length; ++i) {
            if (ArrayIdOfRadiobuttons[i] == ID) {
                found_position = i;
            }
        }
        return found_position;
    }

    //Массив инициализированных обьектов TextView со слушателями.
    private TextView[] ArrayOfTextViews;

    public TextView[] getTextViewArray() {
        return ArrayOfTextViews;
    }
    //Массив инициализированных обьектов RadioButton со слушателями.
    private RadioButton[] ArrayOfRadBtn;

    //Вернуть обьект RadioButton по известному ID.
    private RadioButton getRadioButtonObjectById(int ID) {
        return ArrayOfRadBtn[getRadioButtonPositionById(ID)];
    }

    //Массив данных о выделенных поизиях TextView.
    private boolean[] ViewsSelectStatus;

    public void ClearAllTextViewsValues(){
        for (int i = 0; i < ArrayOfTextViews.length; ++i) {
            ArrayOfTextViews[i].setText("0");
        }
    }
    //Получить позицию выделенного обьекта TextView.
    private int getSelectedTextViewPosition() {
        int pos = 0;
        for (int i = 0; i < ViewsSelectStatus.length; ++i) {
            if (ViewsSelectStatus[i]) pos = i;
        }
        return pos;
    }

    public int getTextViewSelectedId(){
        return ArrayIdViews[getSelectedTextViewPosition()];
    }
    public void setTextViewSelectByPosition(int pos){
        setSelectTextView(getTextViewIdByPosition(pos));
    }
    private boolean[] AllowedViewsToSelect;

    FragmentOnClickListener(int fragment_id, View view, int[] ArrIdOfTextViews, int[] ArrIdOfRadbuttons) {
        FRAGMENT_ID = fragment_id;
        ArrayIdViews = ArrIdOfTextViews;
        ArrayIdOfRadiobuttons = ArrIdOfRadbuttons;
        ArrayOfTextViews = new TextView[ArrayIdViews.length];
        ArrayOfRadBtn = new RadioButton[ArrayIdOfRadiobuttons.length];

        for (int i = 0; i < ArrIdOfTextViews.length; ++i) {
            ArrayOfTextViews[i] = view.findViewById(ArrIdOfTextViews[i]);
            ArrayOfTextViews[i].setOnClickListener(this);
        }
        for (int i = 0; i < ArrIdOfRadbuttons.length; ++i) {
            ArrayOfRadBtn[i] = view.findViewById(ArrIdOfRadbuttons[i]);
            ArrayOfRadBtn[i].setOnClickListener(this);
        }

        ViewsSelectStatus = new boolean[ArrIdOfTextViews.length];
        AllowedViewsToSelect = new boolean[ArrIdOfTextViews.length];

        for (int i = 0; i < ArrIdOfTextViews.length; ++i) {
            ViewsSelectStatus[i] = false; // В массиве в момент инициализации нет выделенных view.
            AllowedViewsToSelect[i] = true; // В массиве в момент инициализации доступны к выделению все view.
        }
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
                for (int i = 0; i < ViewsSelectStatus.length; ++i) {
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

    public TextView getSelectedTextViewObject() {
        return ArrayOfTextViews[getSelectedTextViewPosition()];
    }

    void setTextViewAllowToSelectState(int ID, boolean state) {
        AllowedViewsToSelect[getTextViewPositionById(ID)] = state;
    }


    private boolean getTextViewAllowToSelectState(int pos) {
        return AllowedViewsToSelect[pos];
    }

    void incrementTextViewSelectedPosition() {
        int pos = getSelectedTextViewPosition();
        int min_pos = 0;
        int max_pos = (ArrayIdViews.length - 1);
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
        int max_pos = (ArrayIdViews.length - 1);
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