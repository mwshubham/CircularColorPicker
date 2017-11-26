package com.example.shubham.circularcolorpicker;

public interface OnColorChangedListener {
    void colorChanged(String key, int color);

    void colorChanging(int color);
}
