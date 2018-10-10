package com.example.stayi.myapplication;

import android.widget.TextView;

class Conditions_Calc {
    private int fragment_ID; //id фрагмента, для которого идет расчет.
    private FragmentOnClickListener fListener; //Экземпляр посредника между слушателями клавиатуры и полей ввода фрагмента.

    private TextView[] TextViewStringValTempArray;
    private double[] TextViewDoubleValTempArray;
    private boolean[] IsCalculated;

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
        IsCalculated = new boolean[TextViewDoubleValTempArray.length];

        Diameter_pos = fListener.getTextViewPositionById(R.id.TW_Mill_Diameter);
        Vc_pos = fListener.getTextViewPositionById(R.id.TW_vc_speed);
        Rev_pos = fListener.getTextViewPositionById(R.id.TW_n_rev);
        Teeth_pos = fListener.getTextViewPositionById(R.id.TW_n_teeth);
        Fz_pos = fListener.getTextViewPositionById(R.id.TW_fz_feed);
        Fmin_pos = fListener.getTextViewPositionById(R.id.TW_min_feed);
    }

    private String reverse_arr_Double_to_STR(Double val) {
        //Округляем значение до сотых.
        Double value = val * 1000;
        int result = (int) Math.round(value);
        float result2 = (float) result / 1000;
        if (result2 != 0.0) return String.valueOf(result2);
        return "0";
    }

    void calculate() {
        if (fragment_ID == R.id.MILL_calc_simple) calculate_simple_mill();
    }

    private void calculate_simple_Mill_speed() {
        TextViewDoubleValTempArray[Vc_pos] = (Math.PI * TextViewDoubleValTempArray[Rev_pos] * TextViewDoubleValTempArray[Diameter_pos]) / 1000;
        IsCalculated[Vc_pos] = true;
        //fListener.getTextViewArray()[Vc_pos].setText(reverse_arr_Double_to_STR(TextViewDoubleValTempArray[Vc_pos]));
    }

    private void calculate_simple_Mill_rev() {
        TextViewDoubleValTempArray[Rev_pos] = (TextViewDoubleValTempArray[Vc_pos] * 1000) / (TextViewDoubleValTempArray[Diameter_pos] * Math.PI);
        IsCalculated[Rev_pos] = true;
        //fListener.getTextViewArray()[Rev_pos].setText(reverse_arr_Double_to_STR(TextViewDoubleValTempArray[Rev_pos]));
    }

    private void calculate_simple_Mill_fmin() {
        TextViewDoubleValTempArray[Fmin_pos] = TextViewDoubleValTempArray[Rev_pos] * TextViewDoubleValTempArray[Teeth_pos] * TextViewDoubleValTempArray[Fz_pos];
        IsCalculated[Fmin_pos] = true;
        //fListener.getTextViewArray()[Fmin_pos].setText(reverse_arr_Double_to_STR(TextViewDoubleValTempArray[Fmin_pos]));
    }

    private void calculate_simple_Mill_fz() {
        TextViewDoubleValTempArray[Fz_pos] = TextViewDoubleValTempArray[Fmin_pos] / (TextViewDoubleValTempArray[Teeth_pos] * TextViewDoubleValTempArray[Rev_pos]);
        IsCalculated[Fz_pos] = true;
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

        for (int i = 0; i < IsCalculated.length; ++i) {
            IsCalculated[i] = false;
        }

        switch (selected_pos_id) {
            case R.id.TW_Mill_Diameter:
                if (Diameter > 0 && (Vc > 0 || Rev > 0)) {
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
            case R.id.TW_vc_speed:
                if (Diameter > 0 && (Vc > 0 || Rev > 0)) {
                    calculate_simple_Mill_rev();
                }
                if (Teeth > 0) {
                    if (!fListener.getTextViewAllowToSelectState(Fmin_pos)) {
                        calculate_simple_Mill_fmin();
                    } else {
                        calculate_simple_Mill_fz();
                    }
                }
                break;
            case R.id.TW_n_rev:
                if (Diameter > 0) {
                    calculate_simple_Mill_speed();
                }
                if (Teeth > 0) {
                    if (!fListener.getTextViewAllowToSelectState(Fmin_pos)) {
                        calculate_simple_Mill_fmin();
                    } else {
                        calculate_simple_Mill_fz();
                    }
                }
                break;
            case R.id.TW_n_teeth:
                if (Rev > 0 && (Fz > 0 || Fmin > 0)) {
                    if (!fListener.getTextViewAllowToSelectState(Fmin_pos)) {
                        calculate_simple_Mill_fmin();
                    } else {
                        calculate_simple_Mill_fz();
                    }
                }
                break;
            case R.id.TW_fz_feed:
                if (Rev > 0 && (Fz > 0 || Fmin > 0)) {
                    calculate_simple_Mill_fmin();
                }
                break;
            case R.id.TW_min_feed:
                if (Rev > 0 && (Fz > 0 || Fmin > 0)) {
                    calculate_simple_Mill_fz();
                }
                break;
        }
        if (Double.valueOf(String.valueOf(fListener.getSelectedTextViewObject().getText())) > 0) {
            for (int i = 0; i < TextViewDoubleValTempArray.length; ++i) {
                String val;
                if (TextViewDoubleValTempArray[i] > 1) val = String.valueOf(Math.round(TextViewDoubleValTempArray[i]));
                else val = reverse_arr_Double_to_STR(TextViewDoubleValTempArray[i]);
                if (IsCalculated[i]) {
                    if (i != Fz_pos) fListener.getTextViewArray()[i].setText(val);
                    else {
                        val = reverse_arr_Double_to_STR(TextViewDoubleValTempArray[i]);
                        fListener.getTextViewArray()[i].setText(val);
                    }
                }
            }
        } else {
            switch (selected_pos_id) {
                case R.id.TW_Mill_Diameter:
                    if (fListener.getTextViewAllowToSelectState(Rev_pos)) {
                        fListener.getTextViewArray()[Vc_pos].setText("0");
                    } else fListener.getTextViewArray()[Rev_pos].setText("0");
                    break;
                case R.id.TW_vc_speed:
                    fListener.getTextViewArray()[Rev_pos].setText("0");
                    break;
                case R.id.TW_n_rev:
                    fListener.getTextViewArray()[Vc_pos].setText("0");
                    break;
                case R.id.TW_n_teeth:
                    if (fListener.getTextViewAllowToSelectState(Fmin_pos)) {
                        fListener.getTextViewArray()[Fz_pos].setText("0");
                    } else fListener.getTextViewArray()[Fmin_pos].setText("0");
                    break;
                case R.id.TW_fz_feed:
                    fListener.getTextViewArray()[Fmin_pos].setText("0");
                    break;
                case R.id.TW_min_feed:
                    fListener.getTextViewArray()[Fz_pos].setText("0");
                    break;
            }
        }
    }
}