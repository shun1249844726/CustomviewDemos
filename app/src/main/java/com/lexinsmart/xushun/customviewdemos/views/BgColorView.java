package com.lexinsmart.xushun.customviewdemos.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by xushun on 2017/5/19.
 */

public class BgColorView extends RelativeLayout {

    private String backgroundColor;
    public BgColorView(Context context) {
        this(context,null);
    }

    public BgColorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BgColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.setBackgroundColor(Color.parseColor(backgroundColor));
    }
}
