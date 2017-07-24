package com.sunset.mvp_retrofit_master.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.model.entity.MenuEntity;

import java.util.ArrayList;
import java.util.List;

public class PopMenuAdapter extends BaseAdapter {

    private Context mContext;

    private List<MenuEntity> tops = new ArrayList<>();

    public PopMenuAdapter(Context context) {
        this.mContext = context;
        // 初始化数据
        String[][] str_type = new String[][]{{"头条", "top"}, {"社会", "shehui"},
                {"国内", "guonei"}, {"国际", "guoji"}, {"娱乐", "yule"}, {"体育", "tiyu"},
                {"军事", "junshi"}, {"科技", "keji"}, {"财经", "caijing"}, {"时尚", "shishang"}};
        MenuEntity entity;
        for (String[] aStr_type : str_type) {
            entity = new MenuEntity();
            entity.setName(aStr_type[0]);
            entity.setTypeUrl(aStr_type[1]);
            tops.add(entity);
        }
    }

    @Override
    public int getCount() {
        return tops == null ? 0 : tops.size();
    }

    @Override
    public MenuEntity getItem(int position) {
        return tops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PopHolder myHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pop, parent, false);
            myHolder = new PopHolder(convertView);
            convertView.setTag(myHolder);
        } else {
            myHolder = (PopHolder) convertView.getTag();
        }

        myHolder.setTitle(tops.get(position).getName());

        return convertView;
    }

    private class PopHolder {

        TextView tv_title;

        PopHolder(View itemView) {
            tv_title = (TextView) itemView.findViewById(R.id.item_pop_tv);
        }

        public void setTitle(String title) {
            if (null == tv_title) return;
            tv_title.setText(title);
        }
    }
}
