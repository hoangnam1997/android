package com.finalproject.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.R;
import com.finalproject.model.Newspaper;

import java.util.List;

public class NewspaperAdapter  extends BaseAdapter {
    private List<Newspaper> items;
    private Activity activity;

    public NewspaperAdapter(Activity activity, List<Newspaper> items) {
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
        Newspaper itemCurrent = items.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_menu_view, null);
//        set name of menu
        TextView txtView = (TextView) convertView.findViewById(R.id.txtName);
        txtView.setText(itemCurrent.getTitle());
//        set icon of menu
        ImageView imgIconMenu = (ImageView)convertView.findViewById(R.id.imgIconMenu);
        return convertView;
    }


}
