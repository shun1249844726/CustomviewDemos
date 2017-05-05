package com.lexinsmart.xushun.customviewdemos.psddialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lexinsmart.xushun.customviewdemos.R;

/**
 * Created by xushun on 2017/5/3.
 */

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button btnShowDialog = (Button) findViewById(R.id.btn_show_dialog);
        btnShowDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DialogUtils.showSecurityCodeInputDialog(this, new DialogUtils.DialogOnClickListener() {
            @Override
            public void onClick(String str) {
                Toast.makeText(DialogActivity.this, str, Toast.LENGTH_SHORT).show();
                DialogUtils.dialogDismiss();

            }
        });
    }
}
