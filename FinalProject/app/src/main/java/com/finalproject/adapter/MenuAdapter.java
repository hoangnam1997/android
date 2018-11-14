package com.finalproject.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.activity.MainActivity;
import com.finalproject.activity.WebViewActivity;
import com.finalproject.model.Category;
import com.finalproject.model.Menu;
import com.finalproject.model.Newspaper;
import com.finalproject.response.CategoryResponse;
import com.finalproject.response.NewspaperResponse;
import com.finalproject.ultils.Server;
import com.finalproject.ultils.Service;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MenuAdapter extends BaseAdapter {
    private List<Menu> items;
    List<Category> aCategory;
    private Activity activity;
    private ListView lvContent;
    private NewspaperAdapter adapterNewspaper;

    public MenuAdapter(Activity activity, List<Menu> items) {
        this.activity = activity;
        this.items = items;
        this.adapterNewspaper = adapterNewspaper;
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
        Menu itemCurrent = items.get(position);
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
    public void setImageViewMenu(View convertView,Menu mMenu){
        ImageView imgIconMenu = (ImageView)convertView.findViewById(R.id.imgIconMenu);
        switch (mMenu.getKey()){
            case Menu.KEY_HOME:
                imgIconMenu.setImageResource(R.drawable.ic_home);
                break;
            case Menu.KEY_FOLLOW:
                imgIconMenu.setImageResource(R.drawable.heart);
                break;
            case Menu.KEY_CATEGORY:
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
    public void setContentCategory(final View convertView){
//        get data
        Service mService = Server.getService();
        mService.getCategory().enqueue(new Callback<CategoryResponse>(){
            @Override
            public void onResponse(Response<CategoryResponse> response, Retrofit retrofit) {
                aCategory = response.body().getListCategory();
                setViewCategory(convertView,aCategory);
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(activity, activity.getString(R.string.app_error),Toast.LENGTH_LONG).show();
            }
        });

    }

//    set list category
    public void setViewCategory(View convertView,List<Category> aCategory){
        TableLayout tbLayout = (TableLayout)convertView.findViewById(R.id.tlContent);
//        create new table row
        TableRow row= getTableRow();
        int countItem = aCategory.size();
        for (int i = 1; i <= countItem;i++){
            final Category mCurrentCategory = aCategory.get(i-1);
            Button addBtn = createButotn(mCurrentCategory.getTitle());
//            set event onclick
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)activity).getSupportActionBar().setTitle(activity.getString(R.string.app_name) + " (" +mCurrentCategory.getTitle() +")");
                    DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawerLayout);
                    drawerLayout.closeDrawers();
                    setViewCategory(mCurrentCategory.getId());
                }
            });
//            add view to row
            row.addView(addBtn);
            if(i == countItem && (i!= 0 && (i%(Menu.MAX_ITEM_IN_ROW) != 0))){
                int j = i%(Menu.MAX_ITEM_IN_ROW)+1;
                for(;j<=Menu.MAX_ITEM_IN_ROW;j++){
                    Button addBtnNew = createButotn("");
                    row.addView(addBtnNew);
                }
            }
//            check new raw
            if(i == countItem || (i!= 0 && (i%(Menu.MAX_ITEM_IN_ROW) == 0))){
                tbLayout.addView(row);
                row = getTableRow();
            }
        }
    }

    //    set content view list newspaper
    public void setViewCategory(int id){
        lvContent = (ListView)activity.findViewById(R.id.lvContent);
        Service mService = Server.getService();
        mService.getNewspaper(id).enqueue(new Callback<NewspaperResponse>(){
            @Override
            public void onResponse(Response<NewspaperResponse> response, Retrofit retrofit) {
                final List<Newspaper> contents = response.body().getListNewspaper();
                adapterNewspaper = new NewspaperAdapter(activity,contents);
                lvContent.setAdapter(adapterNewspaper);
                lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Newspaper mNewspaper = contents.get(position);
                        getViewNews(mNewspaper);
                    }
                });
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(activity, activity.getString(R.string.app_error),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //    start intent for newspaper
    public void getViewNews(Newspaper mNewspaper){
        Intent intent = new Intent(activity,WebViewActivity.class);
        intent.putExtra(Newspaper.KEY_OBJECT, mNewspaper);
        activity.startActivity(intent);
    }

}
