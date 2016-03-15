package com.example.wangkeke.nineoldanimdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        WindowManager windowManager = getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        int screenWidth = display.getWidth();
//        int screenHeight = display.getHeight();


        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）------480
        int height = metric.heightPixels;  // 屏幕高度（像素） ----- 854
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）-----1.5
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240） ----- 240


        //px = dp*(dpi/160);

        Toast.makeText(MainActivity.this, "screenWidth= " + width + "-----screenHeight = " + height

                + "---density = " + density + "----densityDpi = " + densityDpi, Toast.LENGTH_SHORT).show();


    }
}
