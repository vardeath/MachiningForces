package com.example.stayi.myapplication;

import android.view.View;
import android.widget.TextView;

class Change_Fixed_Index {
    private int Current_fragment_ID;
    private TextView[] Work_FIXED_Arr;
    private boolean[] Work_FIXED_values_Arr;

    Change_Fixed_Index(int ID, TextView[] Fixindexes, boolean[] index_values) {
        Current_fragment_ID = ID;
        Work_FIXED_Arr = Fixindexes;
        Work_FIXED_values_Arr = index_values;
    }

    private void change_fix_index_activity(int one, int two) {
        Work_FIXED_values_Arr[one] = true;
        Work_FIXED_values_Arr[two] = false;
        Work_FIXED_Arr[one].setVisibility(View.VISIBLE);
        Work_FIXED_Arr[two].setVisibility(View.INVISIBLE);
    }

    void reset_Indexes_Values() {
        switch (Current_fragment_ID) {
            case R.id.MILL_calc_simple:
                change_fix_index_activity(0, 1);
                change_fix_index_activity(2, 3);
                break;
        }
    }

    void set_index_position(int position) {
        int active;
        int inactive;
        switch (Current_fragment_ID) {
            case R.id.MILL_calc_simple:
                //Инициализация позиций полей ввода TextView в соответствии с положением в массиве.
                int Vc = 1;
                int Rev = 2;
                int fz = 4;
                int F = 5;

                //Инициализация номеров позиций в массиве фиксированных индексов.

                if (position == Vc) {
                    active = 0;
                    inactive = 1;
                    change_fix_index_activity(active, inactive);
                } else if (position == Rev) {
                    active = 1;
                    inactive = 0;
                    change_fix_index_activity(active, inactive);
                } else if (position == fz) {
                    active = 2;
                    inactive = 3;
                    change_fix_index_activity(active, inactive);
                } else if (position == F) {
                    active = 3;
                    inactive = 2;
                    change_fix_index_activity(active, inactive);
                }
                break;
        }
    }
}