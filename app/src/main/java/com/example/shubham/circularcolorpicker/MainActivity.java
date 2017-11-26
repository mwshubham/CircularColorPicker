package com.example.shubham.circularcolorpicker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.shubham.circularcolorpicker.OnColorChangedListener;

public class MainActivity extends AppCompatActivity implements OnColorChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPicker1(View view) {
        new ColorPickerOval(this, this, "", Color.WHITE).show();
    }


    @Override
    public void colorChanged(String key, int color) {
        this.findViewById(android.R.id.content).setBackgroundColor(color);
    }

    @Override
    public void colorChanging(int color) {

    }
}