package com.example.shubham.circularcolorpicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.Locale;

public class ColorPickerOval extends Dialog implements OvalColorsSelectedListener {

    private OnColorChangedListener mListener;
    private int mInitialColor;
    private static String mKey;

    public ColorPickerOval(Context context, OnColorChangedListener listener, String key, int initialColor) {
        super(context);
        mKey = key;
        mListener = listener;
        mInitialColor = initialColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.picker_oval);
        final ColorPickerOvalView view = (ColorPickerOvalView) findViewById(R.id.viewOval);

        final EditText etHexColor = (EditText) findViewById(R.id.etHexColor);


        final boolean[] isREditFromView = {false};
        final boolean[] isGEditFromView = {false};
        final boolean[] isBEditFromView = {false};

        OnColorChangedListener l = new OnColorChangedListener() {
            public void colorChanged(String key, int color) {
                mListener.colorChanged(key, color);
                dismiss();
            }

            @Override
            public void colorChanging(int color) {
                Log.v("App", color + "");
                isREditFromView[0] = true;
                isGEditFromView[0] = true;
                isBEditFromView[0] = true;
                String hexColor = String.format("#%06X", (0xFFFFFF & color));
                etHexColor.setText(hexColor);
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);

            }
        };

        view.init(l, mInitialColor, mKey);


        etHexColor.setText("#");
        Selection.setSelection(etHexColor.getText(), etHexColor.getText().toString().length());

        etHexColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("#")) {
                    etHexColor.setText("#");
                    Selection.setSelection(etHexColor.getText(), etHexColor.getText().toString().length());
                }
                if (s.toString().length() == 7 || s.toString().length() == 9) {
                    Log.v("App", etHexColor.getText().toString());
                    int color = Color.parseColor(etHexColor.getText().toString());
                    view.setColor(color);
                    isREditFromView[0] = true;
                    isGEditFromView[0] = true;
                    isBEditFromView[0] = true;
                    int r = Color.red(color);
                    int g = Color.green(color);
                    int b = Color.blue(color);

                }
            }
        });

        final int[] R = {0};
        final int[] G = {0};
        final int[] B = {0};
    }

    @Override
    public void onOvalColorSelected(int color) {
        mListener.colorChanged(mKey, color);
        dismiss();
    }
}