package com.example.stayi.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

@SuppressLint("Registered")
public class keyboard_listener extends Activity implements View.OnClickListener {

    TextView EDITABLE;
    public keyboard_listener(View view) {
        //Собираем массив ID кнопок виртуальной клавиатуры.
        int[] BUTTON_IDS = new int[]{R.id.SL_KEY_0, R.id.SL_KEY_1, R.id.SL_KEY_2, R.id.SL_KEY_3, R.id.SL_KEY_4,
                R.id.SL_KEY_5, R.id.SL_KEY_6, R.id.SL_KEY_7, R.id.SL_KEY_8, R.id.SL_KEY_9, R.id.SL_KEY_DOT, R.id.SL_KEY_UP,
                R.id.SL_KEY_DOWN, R.id.SL_KEY_DEL, R.id.SL_KEY_CLEAR, R.id.SL_KEY_STORAGE};
        //Собираем массив кнопок, вешаем на него слушатель.
        Button[] BUTTONS = new Button[BUTTON_IDS.length];
        for (int i = 0; i < BUTTONS.length; ++i ){
            BUTTONS[i] = (Button) view.findViewById(BUTTON_IDS[i]);
            BUTTONS[i].setOnClickListener(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void set_current_view(TextView cur_view, information_bridge bidge) {
        EDITABLE = cur_view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SL_KEY_0:
                Toast.makeText(view.getContext(), "pressed 0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_1:
                Toast.makeText(view.getContext(), "pressed 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_2:
                Toast.makeText(view.getContext(), "pressed 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_3:
                Toast.makeText(view.getContext(), "pressed 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_4:
                Toast.makeText(view.getContext(), "pressed 4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_5:
                Toast.makeText(view.getContext(), "pressed 5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_6:
                Toast.makeText(view.getContext(), "pressed 6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_7:
                Toast.makeText(view.getContext(), "pressed 7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_8:
                Toast.makeText(view.getContext(), "pressed 8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_9:
                Toast.makeText(view.getContext(), "pressed 9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_DOT:
                Toast.makeText(view.getContext(), "pressed DOT", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_UP:
                Toast.makeText(view.getContext(), "pressed UP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_DOWN:
                Toast.makeText(view.getContext(), "pressed DOWN", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_DEL:
                Toast.makeText(view.getContext(), "pressed DEL", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_CLEAR:
                Toast.makeText(view.getContext(), "pressed CLEAR", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SL_KEY_STORAGE:
                Toast.makeText(view.getContext(), "pressed STORAGE", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
