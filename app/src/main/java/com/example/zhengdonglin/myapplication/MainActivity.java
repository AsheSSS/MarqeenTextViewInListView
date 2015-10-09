package com.example.zhengdonglin.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public List<String> m_list;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_list = new ArrayList<String>();
        initView();
        initData();
        lv.setAdapter(new MyAdapter());
        setListViewHeightBasedOnChildren(lv);
    }
    
    
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { 
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            Log.e("EEEEE",totalHeight+"totalHeight");
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        params.height = totalHeight/20*5;
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        listView.setLayoutParams(params);
    }
    private void initData() {
        for (int i=1;i<=20;i++) {
            String str = new String(i+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaa");
            m_list.add(str);
        }
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return m_list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this,R.layout.item_tv,null);
                holder = new ViewHolder();
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            holder.tv.setText(m_list.get(position));
            holder.tv.setSelected(true);
            return convertView;
        }
    }
    
    class ViewHolder {
        TextView tv;
    }
}

