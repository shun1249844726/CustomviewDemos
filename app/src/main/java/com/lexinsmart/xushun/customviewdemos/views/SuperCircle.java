package com.lexinsmart.xushun.customviewdemos.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xushun on 2017/4/28.
 */

public class SuperCircle extends View {
    private int mViewWidth; //view的宽
    private int mViewHeight;    //view的高
    private int mViewCenterX;   //view宽的中心点
    private int mViewCenterY;   //view高的中心点
    private int mMinRadio; //最里面白色圆的半径
    private float mRingWidth; //圆环的宽度
    private int mSelect;    //分成多少段
    private int mSelectAngle;   //每个圆环之间的间隔
    private int mMinCircleColor;    //最里面圆的颜色
    private int mMaxCircleColor;    //最外面圆的颜色
    private int mRingNormalColor;    //默认圆环的颜色
    private Paint mPaint;
    private int color[] = new int[3];   //渐变颜色

    private float mRingAngleWidth;  //每一段的角度

    private RectF mRectF; //圆环的矩形区域
    private int mSelectRing = 0;        //要显示几段彩色

    private boolean isShowSelect = false;   //是否显示断
    public SuperCircle(Context context) {
        this(context,null);
    }

    public SuperCircle(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SuperCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }
}
