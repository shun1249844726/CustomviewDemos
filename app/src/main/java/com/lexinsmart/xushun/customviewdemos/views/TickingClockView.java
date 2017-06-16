package com.lexinsmart.xushun.customviewdemos.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lexinsmart.xushun.customviewdemos.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by xushun on 2017/5/18.
 */

public class TickingClockView extends View {
    private Paint circlePaint,scalePaint,dialPaint;
    private int bgColor;
    //view 的宽高
    private float mWidth,mHeight;
    //圆的半径
    private float circleRadius;
    //圆心X,Y坐标
    private float circleX,circleY;

    private int second,minute;
    private double hour;

    public TickingClockView(Context context) {
        this(context,null);
    }

    public TickingClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TickingClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TickingClockView,defStyleAttr,0);
        bgColor = ta.getColor(R.styleable.TickingClockView_bgColor,0x226baa);
        ta.recycle();

        initPaint();
    }

    private void initPaint() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.WHITE);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setStrokeWidth(10);

        scalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scalePaint.setColor(Color.BLACK);
        scalePaint.setStyle(Paint.Style.STROKE);
        scalePaint.setStrokeWidth(15);


        //分钟刻度的画笔
        dialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dialPaint.setColor(Color.RED);
        dialPaint.setStrokeWidth(5);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(measuredDimension(widthMeasureSpec), measuredDimension(heightMeasureSpec));

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        if(mWidth<mHeight){
            //圆的半径为view的宽度的一半再减9，防止贴边
            circleRadius = mWidth/2-9;
            circleX = mWidth/2;
            circleY = mHeight/2;
        } else{
            circleRadius = mHeight/2-9;
            circleX = mWidth/2;
            circleY = mHeight/2;
        }

    }
    private int measuredDimension(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 500;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setTimes();

        drawCircle(canvas);
        drawScale(canvas);
        drawPointer(canvas);

    }

    private void setTimes() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        second = getTimes(date,Calendar.SECOND);
        minute = getTimes(date,Calendar.MINUTE);
        hour = getTimes(date,Calendar.HOUR)+minute/12*0.2;

    }
    private int getTimes(Date date,int calendarField){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendarField);
    }

    public void startClock(){
        setTimes();
        invalidate();
    }
    public void stopClock(){
        handler.removeMessages(0);
    }
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                invalidate();
            }
        }
    };
    /**
     * 画刻度
     * @param canvas
     */
    private void drawScale(Canvas canvas) {
        for (int i = 0;i<4;i++){
            canvas.drawLine(circleX,circleY-circleRadius*0.8f-9,circleX,circleY-circleRadius*0.8f+20,scalePaint);
            canvas.rotate(360/4,circleX,circleY);
        }

    }

    /**
     * 画底盘的圆
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        circlePaint.setColor(Color.WHITE);
        circlePaint.setAlpha(150);
        canvas.drawCircle(circleX,circleY, (float) (circleRadius),circlePaint);


        circlePaint.setColor(Color.WHITE);
        canvas.drawCircle(circleX,circleY, (float) (circleRadius*0.8),circlePaint);


        circlePaint.setColor(Color.BLACK);

        canvas.drawCircle(circleX,circleY,10,circlePaint);


    }

    /**画指针
     * X点坐标 cos(弧度)*r
     * Y点坐标 sin(弧度)*r
     * toRadians将角度转成弧度
     * 安卓坐标系与数学坐标系不同的地方是X轴是相反的，所以为了调整方向，需要将角度+270度
     * @param canvas
     */
    private void drawPointer(Canvas canvas){
        canvas.translate(circleX,circleY);
        float hourX = (float) Math.cos(Math.toRadians(hour*30+270))*circleRadius*0.3f;
        float hourY = (float) Math.sin(Math.toRadians(hour*30+270))*circleRadius*0.3f;
        float minuteX = (float) Math.cos(Math.toRadians(minute*6+270))*circleRadius*0.45f;
        float minuteY = (float) Math.sin(Math.toRadians(minute*6+270))*circleRadius*0.45f;
        float secondX = (float) Math.cos(Math.toRadians(second*6+270))*circleRadius*0.6f;
        float secondY = (float) Math.sin(Math.toRadians(second*6+270))*circleRadius*0.6f;



        scalePaint.setStrokeWidth(20);
        canvas.drawLine(0,0,hourX,hourY,scalePaint);
        scalePaint.setStrokeWidth(15);
        canvas.drawLine(0,0,minuteX,minuteY,scalePaint);
        scalePaint.setStrokeWidth(10);
        canvas.drawLine(0,0,secondX,secondY,dialPaint);
        //一秒重绘一次
        handler.sendEmptyMessageDelayed(0,1000);
    }
    public void setBgColor(int color){
        bgColor = color;

    }
}
