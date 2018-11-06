package com.finalproject.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.finalproject.R;
import com.finalproject.model.category;
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
        setImageViewMenu(convertView,itemCurrent);
        return convertView;
    }

//    set icon of menu
    public void setImageViewMenu(View convertView,menu mMenu){
        ImageView imgIconMenu = (ImageView)convertView.findViewById(R.id.imgIconMenu);
        switch (mMenu.getKey()){
            case menu.KEY_HOME:
                imgIconMenu.setImageResource(R.drawable.ic_home);
                break;
            case menu.KEY_FOLLOW:
                imgIconMenu.setImageResource(R.drawable.ic_follow);
                break;
            case menu.KEY_CATEGORY:
                imgIconMenu.setImageResource(R.drawable.ic_category);
//                set content of category
                setContentCategory(convertView);
                break;
            default:
                break;
        }
    }

    //    get table row in menu
    public TableRow getTableRow(){
        TableRow row= new TableRow(activity);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT

        );
        row.setLayoutParams(lp);
        return row;
    }
    public Button createButotn(String text){
        Button addBtn = new Button(activity);
        addBtn.setBackgroundColor(Color.TRANSPARENT);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1);
        addBtn.setLayoutParams(lp);
//            set category name
        addBtn.setText(text);
        return addBtn;
    }

//    set content
    public void setContentCategory(View convertView){
        TableLayout tbLayout = (TableLayout)convertView.findViewById(R.id.tlContent);
//        create new table row
        TableRow row= getTableRow();
//        get data
        category mCaategory = new category();
        List<category> aCategory = mCaategory.getCategory();
        int countItem = aCategory.size();
        for (int i = 1; i <= countItem;i++){
            category mCurrentCategory = aCategory.get(i-1);
            Button addBtn = createButotn(mCurrentCategory.getTitle());
//            set event onclick
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawerLayout);
                    drawerLayout.closeDrawers();
                }
            });
//            add view to row
            row.addView(addBtn);
            if(i == countItem && (i!= 0 && (i%(menu.MAX_ITEM_IN_ROW) != 0))){
                int j = i%(menu.MAX_ITEM_IN_ROW)+1;
                for(;j<=menu.MAX_ITEM_IN_ROW;j++){
                    Button addBtnNew = createButotn("");
                    row.addView(addBtnNew);
                }
            }
//            check new raw
            if(i == countItem || (i!= 0 && (i%(menu.MAX_ITEM_IN_ROW) == 0))){
                tbLayout.addView(row);
                row = getTableRow();
            }
        }
    }


}
