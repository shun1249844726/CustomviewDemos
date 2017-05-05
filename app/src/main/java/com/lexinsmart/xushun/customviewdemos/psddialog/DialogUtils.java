package com.lexinsmart.xushun.customviewdemos.psddialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.lexinsmart.xushun.customviewdemos.R;

/**
 * Dialog提示框
 * Created by yangle on 2017/2/4.
 */
public class DialogUtils {

    private static Dialog dialog;

    /**
     * Dialog提示框消失方法
     */
    public static void dialogDismiss() {
        if (isDialogShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * Dialog提示框是否正在运行
     *
     * @return Dialog提示框是否正在运行
     */
    public static boolean isDialogShowing() {
        return dialog != null && dialog.isShowing();
    }


    /**
     * 创建Dialog提示框
     *
     * @param context 上下文
     */
    private static void createDialog(Context context) {
        dialogDismiss();
        dialog = new Dialog(context, R.style.SampleTheme);
        dialog.setContentView(R.layout.layout_dialog);
        // 点击弹窗外区域，弹窗不消失
        dialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 显示确认安全码提示框
     *
     * @param activity              当前Activity
     * @param dialogOnClickListener 确定按钮点击事件
     */
    public static void showSecurityCodeInputDialog(final Activity activity,
                                                   final DialogOnClickListener dialogOnClickListener) {
        createDialog(activity);

        ImageView ivClose = (ImageView) dialog.findViewById(R.id.iv_close);
        final GridPasswordView gpvCode = (GridPasswordView) dialog.findViewById(R.id.gpv_code);
        Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = gpvCode.getPassWord();
                dialogOnClickListener.onClick(code);
            }
        });

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                // 获取焦点
                gpvCode.requestFocus();
                // 显示软键盘
                SoftInputUtils.showSoftInput(activity);
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // 隐藏软键盘
                SoftInputUtils.hideSoftInput(activity);
            }
        });

        dialog.show();
    }

    public interface DialogOnClickListener {
        /**
         * 点击事件
         *
         * @param str 回调参数
         */
        public void onClick(String str);
    }
}
