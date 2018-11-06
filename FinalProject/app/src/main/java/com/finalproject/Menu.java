package com.finalproject;


import java.util.ArrayList;
import java.util.List;

public class Menu {
    public static final String KEY_HOME = "Home";
    public static final String KEY_FOLLOW = "MENU_FOLLOW";
    public static final String KEY_CATEGORY = "MENU_CATEGORY";
    public static final int MAX_ITEM_IN_ROW = 2;

    private String key;
    private String name;

    public Menu(){
    }
    public Menu(String key,String name){
        this.key    = key;
        this.name   = name;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    get list menu
    public List<Menu> getListMenu(){
        List<Menu> result = new ArrayList<Menu>();
        result.add(new Menu(Menu.KEY_HOME,"Home"));
        result.add(new Menu(Menu.KEY_FOLLOW,"Theo dõi"));
        result.add(new Menu(Menu.KEY_CATEGORY,"Danh mục"));
        return result;
    }

}
