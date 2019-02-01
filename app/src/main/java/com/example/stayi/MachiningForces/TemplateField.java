package com.example.stayi.MachiningForces;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.stayi.MachiningForces.Enumerations.FieldType;

import static android.app.PendingIntent.getActivity;

public class TemplateField extends LinearLayout {

    private static int id = 1;
    private Context contxt;
    private FieldType mGeneralTextVIewFieldType = null;
    private TextView mGeneralTextVIew = null;
    private TextView mDescription = null;
    private TextView mUnit = null;

    private void init(Context context) {
        contxt = context;
        initializeViews(context);
    }

    public TemplateField(Context context) {
        super(context);
        init(context);
    }

    public TemplateField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TemplateField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TemplateField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.example_view, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mGeneralTextVIew = (TextView) this.findViewById(R.id.example_element_2);
        mGeneralTextVIew.setId(View.generateViewId());
        mDescription = (TextView) this.findViewById(R.id.example_element_3);
        mUnit = (TextView) this.findViewById(R.id.example_element_4);
    }

    public void setText(String text1, String text2) {
        mDescription.setText(text1);
        mUnit.setText(text2);
    }

    public void setGeneralTextViewFieldType(FieldType ftp) {
        mGeneralTextVIewFieldType = ftp;
    }

    public FieldType getGeneralTextViewFieldType() {
        return mGeneralTextVIewFieldType;
    }

    public int getGeneralTextViewId() {
        return mGeneralTextVIew.getId();
    }

    public void setText(TemplateStringPairs templateStringPairs) {
        mDescription.setText(templateStringPairs.getFirstValue());
        mUnit.setText(templateStringPairs.getSecondValue());
    }
}