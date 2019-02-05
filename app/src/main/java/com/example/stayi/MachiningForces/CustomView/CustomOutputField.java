package com.example.stayi.MachiningForces.CustomView;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stayi.MachiningForces.Enumerations.FieldType;
import com.example.stayi.MachiningForces.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CustomOutputField extends LinearLayout{

    private FieldType mGeneralTextVIewFieldType = null;
    private TextView mGeneralTextVIew = null;
    private TextView mDescription = null;

    public CustomOutputField(Context context) {
        super(context);
        init(context);
    }

    public CustomOutputField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomOutputField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomOutputField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        initializeViews(context);
    }

    private void initializeViews(@NonNull Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_output, this);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mGeneralTextVIew = this.findViewById(R.id.outputMain);
        mGeneralTextVIew.setId(View.generateViewId());
        mDescription = this.findViewById(R.id.outputDescription);
        mDescription.setId(View.generateViewId());
    }

    public FieldType getMainTextViewFieldType() {
        return mGeneralTextVIewFieldType;
    }

    public int getMainTextViewId() {
        return mGeneralTextVIew.getId();
    }

    public int getDescriptionTextViewId() {
        return mDescription.getId();
    }

    public void setValues(PrimaryValue Object) {
        mGeneralTextVIewFieldType = Object.getFieldType();
        mDescription.setText(Object.getDescription());
    }
}