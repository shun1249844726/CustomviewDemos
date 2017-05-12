package com.lexinsmart.xushun.customviewdemos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lexinsmart.xushun.customviewdemos.activity.LineChartActivity;
import com.lexinsmart.xushun.customviewdemos.activity.SuperCircleSample;
import com.lexinsmart.xushun.customviewdemos.psddialog.DialogActivity;
import com.lexinsmart.xushun.customviewdemos.transparentdialog.TransparentDialog;
import com.lexinsmart.xushun.customviewdemos.views.SuperCircle;
import com.sdsmdg.tastytoast.TastyToast;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    private String[] mListItems = {"SuperCircleSample", "LineChartView","psdDialog","transparentDialog"};
    private LayoutInflater mLayoutInflater;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv_demos);
        ListAdapter mAdapter = new ListAdapter(this, mListItems);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Class<?> activity = null;
                switch (mListItems[position]) {
                    case "SuperCircleSample":
                        activity = SuperCircleSample.class;
                        break;
                    case "LineChartView":
                        activity = LineChartActivity.class;
                        break;
                    case "psdDialog":
                        activity = DialogActivity.class;
                        break;
                    case "transparentDialog":
                        activity = TransparentDialog.class;
                    default:
                        break;
                }
                if (activity != null) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, activity);
                    startActivity(intent);
                }
            }
        });

        TastyToast.makeText(getApplicationContext(), "Hello World !", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);

    }

    private class ListAdapter extends BaseAdapter {

        private final String[] mItems;

        public ListAdapter(Context context, String[] mData) {
            this.mItems = mData;
            mLayoutInflater = LayoutInflater.from(context);

        }


        public final class ViewHolder {
            public TextView itemContent;
        }

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();

                convertView = mLayoutInflater.inflate(R.layout.item_list, null);

                holder.itemContent = (TextView) convertView.findViewById(R.id.tv_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.itemContent.setText(mItems[position]);

            return convertView;
        }
    }
}
