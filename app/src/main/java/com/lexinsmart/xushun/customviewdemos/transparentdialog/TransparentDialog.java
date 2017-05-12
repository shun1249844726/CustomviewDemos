package com.lexinsmart.xushun.customviewdemos.transparentdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lexinsmart.xushun.customviewdemos.R;

/**
 * Created by xushun on 2017/5/5.
 */

public class TransparentDialog extends AppCompatActivity {
    Button showDialog;
    Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent_dialog);
        mContext = this;
        showDialog = (Button) findViewById(R.id.btn_show_transparent_dialogß);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog myDialog = new MyDialog(mContext,R.style.update_dialog);
                myDialog.show();

            }
        });

    }

}
