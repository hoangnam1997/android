package com.finalproject.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.adapter.MenuAdapter;
import com.finalproject.adapter.NewspaperAdapter;
import com.finalproject.helper.NewspaperHelper;
import com.finalproject.model.Menu;
import com.finalproject.model.Newspaper;
import com.finalproject.response.CategoryResponse;
import com.finalproject.response.NewspaperResponse;
import com.finalproject.ultils.Server;
import com.finalproject.ultils.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout contentMain;
    private NavigationView navigationView;
    private ListView lvMenu;
    private List<Menu> items;
    private List<Newspaper> contents;
    private ListView lvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAttributes();
        setMenu();
        setViewNewspaper();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    set attribute of layout
    public void setAttributes(){
        drawerLayout    = (DrawerLayout) findViewById(R.id.drawerLayout);
        contentMain     = (LinearLayout) findViewById(R.id.contentMain);
        navigationView  = (NavigationView) findViewById(R.id.navigationView);
        lvMenu = (ListView) findViewById(R.id.lvMenu);
        lvContent = (ListView)findViewById(R.id.lvContent);
    }

//    set list menu and event click on menu
    public void setMenu(){
//        set button menu on actionbar
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
//        get list menu
        Menu mMenu = new Menu();
        items = mMenu.getListMenu();
//        create adapter
        MenuAdapter adapterTest = new MenuAdapter(this,items);
//        set adapter
        lvMenu.setAdapter(adapterTest);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Menu menuSelected = items.get(position);
                // close drawer when item is tapped
                drawerLayout.closeDrawers();
                changeActivity(menuSelected.getKey());
            }
        });
    }


//    set content view list newspaper
    public void setViewNewspaper(){
        Service mService = Server.getService();
        mService.getNewspaper().enqueue(new Callback<NewspaperResponse>(){
            @Override
            public void onResponse(Response<NewspaperResponse> response, Retrofit retrofit) {
                setNewspaperByArray(response.body().getListNewspaper());
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.app_error),Toast.LENGTH_SHORT).show();
            }
        });
    }

//    set content view list newspaper by array
    public void setNewspaperByArray(List<Newspaper> listNewspaper){
        contents = listNewspaper;
        NewspaperAdapter adapterTest = new NewspaperAdapter(MainActivity.this,contents);
        lvContent.setAdapter(adapterTest);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Newspaper mNewspaper = contents.get(position);
                getViewNews(mNewspaper);
            }
        });
    }

//    public function change activity when click item on menu
    public void changeActivity(String key){
        switch (key){
            case Menu.KEY_HOME:
                getSupportActionBar().setTitle(this.getString(R.string.app_name));
                setViewNewspaper();
                break;
            case Menu.KEY_FOLLOW:
                NewspaperHelper newspaperHelper = new NewspaperHelper(this);
                List<Newspaper> listNewspaper = newspaperHelper.getAllNewspaper();
                setNewspaperByArray(listNewspaper);
                break;
        }
    }

//    start intent for newspaper
    public void getViewNews(Newspaper mNewspaper){
        Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
        intent.putExtra(Newspaper.KEY_LINK,mNewspaper.getUrl());
        intent.putExtra(Newspaper.KEY_TITLE,mNewspaper.getTitle());
        startActivity(intent);
    }
}