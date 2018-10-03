package com.example.stayi.myapplication;

import android.widget.TextView;

class Conditions_Calc {
    private int fragment_ID; //id фрагмента, для которого идет расчет.
    private FragmentOnClickListener fListener; //Экземпляр посредника между слушателями клавиатуры и полей ввода фрагмента.

    private TextView[] TextViewStringValTempArray;
    private double[] TextViewDoubleValTempArray;

    private int Diameter_pos;
    private int Vc_pos;
    private int Rev_pos;
    private int Teeth_pos;
    private int Fz_pos;
    private int Fmin_pos;

    Conditions_Calc(int id, FragmentOnClickListener fList) {
        fragment_ID = id;
        fListener = fList;
        TextViewDoubleValTempArray = fListener.getTextViewDoubleValuesArray();
        TextViewStringValTempArray = fListener.getTextViewArray();

        Diameter_pos = fListener.getTextViewPositionById(R.id.TW_Mill_Diameter);
        Vc_pos = fListener.getTextViewPositionById(R.id.TW_vc_speed);
        Rev_pos = fListener.getTextViewPositionById(R.id.TW_n_rev);
        Teeth_pos = fListener.getTextViewPositionById(R.id.TW_n_teeth);
        Fz_pos = fListener.getTextViewPositionById(R.id.TW_t_feed_editor);
        Fmin_pos = fListener.getTextViewPositionById(R.id.TW_m_feed_editor);
    }

    private String reverse_arr_Double_to_STR(Double val) {
        //Округляем значение до сотых.
        Double value = val * 100;
        int result = (int) Math.round(value);
        float result2 = (float) result / 100;
        if (result2 != 0.0) return String.valueOf(result2);
        return "0";
    }

    void calculate() {
        calculate_simple_mill();
    }

    private void calculate_simple_Mill_speed() {
        TextViewDoubleValTempArray[Vc_pos] = (Math.PI * TextViewDoubleValTempArray[Rev_pos] * TextViewDoubleValTempArray[Diameter_pos]) / 1000;
        //fListener.getTextViewArray()[Vc_pos].setText(reverse_arr_Double_to_STR(TextViewDoubleValTempArray[Vc_pos]));
    }

    private void calculate_simple_Mill_rev() {
        TextViewDoubleValTempArray[Rev_pos] = (TextViewDoubleValTempArray[Vc_pos] * 1000) / (TextViewDoubleValTempArray[Diameter_pos] * Math.PI);
        //fListener.getTextViewArray()[Rev_pos].setText(reverse_arr_Double_to_STR(TextViewDoubleValTempArray[Rev_pos]));
    }

    private void calculate_simple_Mill_fmin() {
        TextViewDoubleValTempArray[Fmin_pos] = TextViewDoubleValTempArray[Rev_pos] * TextViewDoubleValTempArray[Teeth_pos] * TextViewDoubleValTempArray[Fz_pos];
        //fListener.getTextViewArray()[Fmin_pos].setText(reverse_arr_Double_to_STR(TextViewDoubleValTempArray[Fmin_pos]));
    }

    private void calculate_simple_Mill_fz() {
        TextViewDoubleValTempArray[Fz_pos] = TextViewDoubleValTempArray[Fmin_pos] / (TextViewDoubleValTempArray[Teeth_pos] * TextViewDoubleValTempArray[Rev_pos]);
        //fListener.getTextViewArray()[Fz_pos].setText(reverse_arr_Double_to_STR(TextViewDoubleValTempArray[Fz_pos]));
    }

    private void calculate_simple_mill() {
        TextViewDoubleValTempArray = fListener.getTextViewDoubleValuesArray();

        Double Diameter = TextViewDoubleValTempArray[Diameter_pos];
        Double Vc = TextViewDoubleValTempArray[Vc_pos];
        Double Rev = TextViewDoubleValTempArray[Rev_pos];
        Double Teeth = TextViewDoubleValTempArray[Teeth_pos];
        Double Fz = TextViewDoubleValTempArray[Fz_pos];
        Double Fmin = TextViewDoubleValTempArray[Fmin_pos];

        int selected_pos_id = fListener.getTextViewSelectedId();

        switch (selected_pos_id) {
            case R.id.TW_Mill_Diameter:
                if (Diameter > 0) {
                    if (!fListener.getTextViewAllowToSelectState(Rev_pos)) {
                        calculate_simple_Mill_rev();
                    } else calculate_simple_Mill_speed();
                    if (Teeth > 0) {
                        if (!fListener.getTextViewAllowToSelectState(Fmin_pos)) {
                            calculate_simple_Mill_fmin();
                        } else {
                            calculate_simple_Mill_fz();
                        }
                    }
                }
                break;
        }
        for (int i = 0; i < TextViewStringValTempArray.length; ++i) {
            String val = String.valueOf((int) Math.round(TextViewDoubleValTempArray[i]));
            if (Math.round(TextViewDoubleValTempArray[i]) > 0) fListener.getTextViewArray()[i].setText(val);
        }
    }
}