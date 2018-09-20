package com.example.stayi.myapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import static android.view.KeyEvent.KEYCODE_0;
import static android.view.KeyEvent.KEYCODE_DEL;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.KeyEvent.KEYCODE_NUMPAD_DOT;

public class EDIT_txt_listener {

    private boolean use_var = true;
    private EditText EDT;

    public EDIT_txt_listener(EditText edtx) {
        EDT = edtx;
        EDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (EDT.getText().length() == 0 && use_var) {
                    EDT.setText("0");
                    EDT.setSelection(EDT.getText().length());
                }
                use_var = true;
            }
        });
        EDT.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                char zero = '0';
                String empty = "";
                String val = String.valueOf(EDT.getText());
                if (val.equals(String.valueOf(zero)) && (keyCode == KEYCODE_0 || keyCode == KEYCODE_DEL))
                    return true;
                if (val.equals(String.valueOf(zero)) && keyCode != KEYCODE_ENTER) {
                    if (keyCode != KEYCODE_NUMPAD_DOT && EDT.getSelectionEnd() == 1) {
                        use_var = false;
                        EDT.setText(empty);
                    }
                }
                if (EDT.getId() == R.id.editText4_teeth) {
                    if(keyCode == KEYCODE_NUMPAD_DOT || keyCode == KEYCODE_0){
                        return true;
                    }
                }
                return false;
            }
        });
    }
}