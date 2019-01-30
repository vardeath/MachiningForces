package com.example.stayi.MachiningForces;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExampleField extends LinearLayout {

    private TextView mExampleTw_1;
    private TextView mExampleTw_2;
    private TextView mExampleTw_3;
    private TextView mExampleTw_4;

    public ExampleField(Context context) {
        super(context);
        initializeViews(context);
    }

    public ExampleField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public ExampleField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ExampleField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.example_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //Настраиваем для обоих кнопок изображения.
        //Будем использовать стандартные изображения:
        mExampleTw_1 = (TextView) this.findViewById(R.id.example_element_1);
        mExampleTw_2 = (TextView) this.findViewById(R.id.example_element_2);
        mExampleTw_3 = (TextView) this.findViewById(R.id.example_element_3);
        mExampleTw_4 = (TextView) this.findViewById(R.id.example_element_4);
    }

    public void setText(String text1, String text2) {
        mExampleTw_3.setText(text1);
        mExampleTw_4.setText(text2);
    }
}