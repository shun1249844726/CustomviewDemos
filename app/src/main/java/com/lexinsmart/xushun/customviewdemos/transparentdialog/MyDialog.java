package com.lexinsmart.xushun.customviewdemos.transparentdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lexinsmart.xushun.customviewdemos.R;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by xushun on 2017/5/5.
 */

public class MyDialog  extends Dialog{
    private Context context;
    public MyDialog(@NonNull Context context) {
        this(context, R.style.update_dialog);
        this.context = context;
    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    private void init() {
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_transparent_dialog);
        initViews();
        initValues();

        TastyToast.makeText(getContext(),"ddd", Toast.LENGTH_SHORT,TastyToast.WARNING);

    }

    private void initValues() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

    }

    private void initViews() {
       // findViewById(R.id.alert_update_btn).setOnClickListener(thi);
    }
}
