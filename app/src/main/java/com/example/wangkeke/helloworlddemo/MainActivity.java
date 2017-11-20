package com.example.wangkeke.helloworlddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener {

    private String[] imageUrl = new String[]{"http://a2.att.hudong.com/22/33/01300000247011124170338935232.jpg", "http://img0.imgtn.bdimg.com/it/u=1525686852,3109542472&fm=21&gp=0.jpg", "http://www.microfotos.com/pic/0/58/5805/580501preview4.jpg", "http://img1.imgtn.bdimg.com/it/u=2222452981,4042994050&fm=21&gp=0.jpg"};

    private GestureDetector detector;

    Animation leftInAnimation;

    Animation leftOutAnimation;

    Animation rightInAnimation;

    Animation rightOutAnimation;

    private static final int DELAY_TIME = 3000;

    @Bind(R.id.image_one)
    ImageView imageOne;
//    @Bind(R.id.image_two)
//    ImageView imageTwo;
    @Bind(R.id.layout)
    LinearLayout layout;

    @Bind(R.id.layout_text)
    TextView layout_tv;


    @Bind(R.id.image_three)
    ImageView imageThree;
    @Bind(R.id.image_four)
    ImageView imageFour;
    @Bind(R.id.flipper)
    ViewFlipper flipper;
    @Bind(R.id.point_layout)
    LinearLayout pointLayout;

    private Handler postHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            showToRightNext();

            //清除所有任务
            if (postHandler != null)
                postHandler.removeCallbacksAndMessages(null);
            //3秒后再次执行
            postHandler.sendEmptyMessageDelayed(1, DELAY_TIME);
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        //手势监听
        detector = new GestureDetector(MainActivity.this, this);
        //动画效果
        leftInAnimation = AnimationUtils.loadAnimation(this, R.anim.left_in);
        leftOutAnimation = AnimationUtils.loadAnimation(this, R.anim.left_out);
        rightInAnimation = AnimationUtils.loadAnimation(this, R.anim.right_in);
        rightOutAnimation = AnimationUtils.loadAnimation(this, R.anim.right_out);

        //显示默认标致页数point
        defaultShowPoint();
        //循环滑动图片
        postHandler.sendEmptyMessageDelayed(1, DELAY_TIME);

        layout_tv.setText("默认值");


        for (int i = 0; i < imageUrl.length; i++) {
            switch (i)
            {
                case 0:
                    ImageLoader.getInstance().displayImage(imageUrl[i],imageOne);
                    break;
                case 1:
//                    ImageLoader.getInstance().displayImage(imageUrl[i],imageTwo);
                    break;
                case 2:
                    ImageLoader.getInstance().displayImage(imageUrl[i],imageThree);
                    break;
                case 3:
                    ImageLoader.getInstance().displayImage(imageUrl[i],imageFour);
                    break;
            }
        }
    }

    @OnClick({R.id.image_one, R.id.layout, R.id.image_three, R.id.image_four,R.id.layout_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_one:
                Toast.makeText(MainActivity.this, "one", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.image_two:
//                Toast.makeText(MainActivity.this, "two", Toast.LENGTH_SHORT).show();
            case R.id.layout:

                break;
            case R.id.image_three:
                Toast.makeText(MainActivity.this, "three", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_four:
                Toast.makeText(MainActivity.this, "four", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_text:
                Toast.makeText(MainActivity.this, "layout_text", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //设置可以点击
        this.imageOne.setClickable(true);
//        this.imageTwo.setClickable(true);
        this.layout.setClickable(true);
        this.imageThree.setClickable(true);
        this.imageFour.setClickable(true);
        this.layout_tv.setClickable(true);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //防止onclick和onFling
        this.imageOne.setClickable(false);
//        this.imageTwo.setClickable(false);
        this.layout.setClickable(false);
        this.imageThree.setClickable(false);
        this.imageFour.setClickable(false);
        this.layout_tv.setClickable(false);

        if (e1.getX() - e2.getX() > 80) {
            if (postHandler != null)
                postHandler.removeCallbacksAndMessages(null);
            showToRightNext();
            return true;
        } else if (e1.getX() - e2.getY() < 80) {
            if (postHandler != null)
                postHandler.removeCallbacksAndMessages(null);
            showToLeftNext();
            return true;
        }
        return true;
    }

    /**
     * 向右滑动
     */
    private void showToRightNext() {
        flipper.setInAnimation(leftInAnimation);
        flipper.setOutAnimation(leftOutAnimation);
        flipper.showNext();//向右滑动
        updatePointPosition();
        postHandler.sendEmptyMessageDelayed(1, DELAY_TIME);
    }

    /**
     * 向左滑动
     */
    private void showToLeftNext() {
        flipper.setInAnimation(rightInAnimation);
        flipper.setOutAnimation(rightOutAnimation);
        flipper.showPrevious();//向左滑动
        updatePointPosition();
        postHandler.sendEmptyMessageDelayed(1, DELAY_TIME);
    }

    /**
     * 更新point点的状态
     */
    private void updatePointPosition() {

        int curPoi = flipper.getDisplayedChild();

        for (int i = 0; i < pointLayout.getChildCount(); i++) {
            if (curPoi == i) {
                pointLayout.getChildAt(curPoi).setBackgroundResource(R.drawable.skin_tabbar_dot_selected);
            } else {
                pointLayout.getChildAt(i).setBackgroundResource(R.drawable.skin_tabbar_dot_normal);
            }
        }

    }

    /**
     * 显示默认标致页数point
     */
    private void defaultShowPoint() {
        pointLayout.removeAllViews();
        for (int i = 0; i < imageUrl.length; i++) {
            TextView textView = new TextView(MainActivity.this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.width = 6;
            layoutParams.height = 6;
            textView.setLayoutParams(layoutParams);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10, 0, 0, 0);
            textView.setLayoutParams(lp);

            if (i == 0) {
                textView.setBackgroundResource(R.drawable.skin_tabbar_dot_selected);
            } else {
                textView.setBackgroundResource(R.drawable.skin_tabbar_dot_normal);
            }
            pointLayout.addView(textView);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 执行touch 事件
        super.onTouchEvent(event);
        return this.detector.onTouchEvent(event);
    }

    /**
     * //这个方法会先执行，当返回为true时，才执行onTouchEvent 方法
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //先执行滑屏事件
        detector.onTouchEvent(ev);
        super.dispatchTouchEvent(ev);
        return true;
    }
}
