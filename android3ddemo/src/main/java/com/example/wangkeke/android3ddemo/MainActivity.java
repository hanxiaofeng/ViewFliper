package com.example.wangkeke.android3ddemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener, RotateAnimation.InterpolatedTimeListener {

    private Button btnIncrease, btnDecrease;
    private TextView txtNumber;
    private int number;
    /** TextNumber是否允许显示最新的数字。 */
    private boolean enableRefresh;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        txtNumber = (TextView) findViewById(R.id.txtNumber);

        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);

        number = 3;
        txtNumber.setText(Integer.toString(number));
    }

    public void onClick(View v) {
        enableRefresh = true;
        RotateAnimation rotateAnim = null;
        float cX = txtNumber.getWidth() / 2.0f;
        float cY = txtNumber.getHeight() / 2.0f;
        if (v == btnDecrease) {
            number--;
            rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_DECREASE);
        } else if (v == btnIncrease) {
            number++;
            rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_INCREASE);
        }
        if (rotateAnim != null) {
            rotateAnim.setInterpolatedTimeListener(this);
            rotateAnim.setFillAfter(true);
            txtNumber.startAnimation(rotateAnim);
        }
    }

    @Override
    public void interpolatedTime(float interpolatedTime) {
        // 监听到翻转进度过半时，更新txtNumber显示内容。
        if (enableRefresh && interpolatedTime > 0.5f) {
            txtNumber.setText(Integer.toString(number));
            Log.d("ANDROID_LAB", "setNumber:" + number);
            enableRefresh = false;
        }
    }
}
