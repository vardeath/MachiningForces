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
import com.example.stayi.MachiningForces.FragmentField.FieldType;

public class ExampleField extends LinearLayout {

    private static int id = 1;
    private FieldType mGeneralTextVIewFieldType = null;
    private TextView mGeneralTextVIew = null;
    private TextView mDescription = null;
    private TextView mUnit = null;

    private void init(Context context) {
        initializeViews(context);
    }

    public ExampleField(Context context) {
        super(context);
        init(context);
    }

    public ExampleField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExampleField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ExampleField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        switch (this.getId()){
            case R.id.MillDiameter:
                setGeneralTextViewFieldType(FieldType.MillDiameter);
                setUnitsText("Диаметр фрезы","D, мм");
                break;
            case R.id.MillCuttingSpeed:
                setGeneralTextViewFieldType(FieldType.MillCuttingSpeed);
                setUnitsText("Скорость резания", "Vc, м/мин");
                break;
            case R.id.MillRevolution:
                setGeneralTextViewFieldType(FieldType.MillRevolution);
                setUnitsText("Число оборотов", "n, об/мин");
                break;
            case R.id.MillTeethQuantity:
                setGeneralTextViewFieldType(FieldType.MillTeethQuantity);
                setUnitsText("Число режущих кромок", "z, шт");
                break;
            case R.id.MillCuttingDepth:
                setGeneralTextViewFieldType(FieldType.MillCuttingDepth);
                setUnitsText("Глубина обработки", "Ap, мм");
                break;
            case R.id.MillCuttingWidth:
                setGeneralTextViewFieldType(FieldType.MillCuttingWidth);
                setUnitsText("Ширина обработки", "Aе, мм");
                break;
            case R.id.MillGeneralAngle:
                setGeneralTextViewFieldType(FieldType.MillGeneralAngle);
                setUnitsText("Угол в плане", "А, град.");
                break;
            case R.id.MillToothFeed:
                setGeneralTextViewFieldType(FieldType.MillToothFeed);
                setUnitsText("Подача на зуб", "fz, мм");
                break;
            case R.id.MillRevolutionFeed:
                setGeneralTextViewFieldType(FieldType.MillRevolutionFeed);
                setUnitsText("Подача на оборот", "Fоб, мм");
                break;
            case R.id.MillMinuteFeed:
                setGeneralTextViewFieldType(FieldType.MillMinuteFeed);
                setUnitsText("Минутная подача", "Fмин, мм");
                break;
            case R.id.MillPathLength:
                setGeneralTextViewFieldType(FieldType.MillPathLength);
                setUnitsText("Длина обработки", "L, мм");
                break;
            case R.id.MillToolLength:
                setGeneralTextViewFieldType(FieldType.MillToolLength);
                setUnitsText("Вылет инструмента", "Lвыл, мм");
                break;
            case R.id.MillAttackAngle:
                setGeneralTextViewFieldType(FieldType.MillAttackAngle);
                setUnitsText("Передний угол", "Y, град.");
                break;
        }
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

    private void setUnitsText(String text1, String text2){
        mDescription.setText(text1);
        mUnit.setText(text2);
    }
}