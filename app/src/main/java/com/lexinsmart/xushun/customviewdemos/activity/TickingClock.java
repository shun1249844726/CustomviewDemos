package com.lexinsmart.xushun.customviewdemos.activity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lexinsmart.xushun.customviewdemos.R;
import com.lexinsmart.xushun.customviewdemos.views.BgColorView;
import com.lexinsmart.xushun.customviewdemos.views.ColorEvaluator;
import com.lexinsmart.xushun.customviewdemos.views.TickingClockView;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by xushun on 2017/5/18.
 */

public class TickingClock extends AppCompatActivity {
    private TickingClockView mTickingClockView;
    private BgColorView rlBg;
    private Button changeBgBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick_clock);

        mTickingClockView = (TickingClockView) findViewById(R.id.clock_view);
        rlBg = (BgColorView) findViewById(R.id.rl_bg);
        changeBgBtn = (Button) findViewById(R.id.btn_changeBg);

        changeBgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(getApplicationContext(), "Click !", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);


                // 颜色过渡动画
                ObjectAnimator animator = ObjectAnimator.ofObject(rlBg, "backgroundColor",
                        new ColorEvaluator(), "#2B93EC", "#ED6E74");
                animator.setDuration(2000);
                animator.start();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mTickingClockView.startClock();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTickingClockView.stopClock();
    }
}
