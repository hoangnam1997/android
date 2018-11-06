package com.finalproject.activity;

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

import com.finalproject.R;
import com.finalproject.MenuAdapter;
import com.finalproject.Menu;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout contentMain;
    private NavigationView navigationView;
    private ListView lvMenu;
    private List<Menu> items;
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

//    public function change activity when click item on menu
    public void changeActivity(String key){

    }

//    set content view list newspaper
    public void setViewNewspaper(){

    }
}