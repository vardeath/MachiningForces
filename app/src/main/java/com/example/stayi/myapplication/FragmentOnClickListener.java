package com.example.stayi.myapplication;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class FragmentOnClickListener implements View.OnClickListener {
    private Fragment_Adaptor ButAdaptor;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public FragmentOnClickListener(Fragment_Adaptor adapt) {
        ButAdaptor = adapt;
        TextView[] T_Arr = ButAdaptor.getTextViewsArray();
        Button[] B_Arr = ButAdaptor.getButtonArray();
        for (TextView x : T_Arr) {x.setOnClickListener(this);}
        for (Button x : B_Arr) {x.setOnClickListener(this);}
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        ButAdaptor.setSelectedView(id);
        ButAdaptor.doButton_action(id);
    }
}