package com.reinvo.together;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class QuickAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_add);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);

        int width = display.widthPixels;
        int height = display.heightPixels;

        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.8));
    }
}
