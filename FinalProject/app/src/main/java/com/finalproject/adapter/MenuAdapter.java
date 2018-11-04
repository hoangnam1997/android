package com.finalproject.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.R;
import com.finalproject.model.menu;

import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private List<menu> items;
    private Activity activity;

    public MenuAdapter(Activity activity, List<menu> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        menu itemCurrent = items.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_menu_view, null);
//        set name of menu
        TextView txtView = (TextView) convertView.findViewById(R.id.txtName);
        txtView.setText(itemCurrent.getName());
//        set icon of menu
        ImageView imgIconMenu = (ImageView)convertView.findViewById(R.id.imgIconMenu);
        setImageViewMenu(itemCurrent,imgIconMenu);
        return convertView;
    }

//    set icon of menu
    public void setImageViewMenu(menu mMenu,ImageView imgIconMenu){
        switch (mMenu.getKey()){
            case menu.KEY_HOME:
                imgIconMenu.setImageResource(R.drawable.ic_home);
                break;
            case menu.KEY_FOLLOW:
                imgIconMenu.setImageResource(R.drawable.ic_follow);
                break;
            default:
                break;
        }
    }
}
