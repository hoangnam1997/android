package com.finalproject.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class menu {
    public static final String KEY_HOME = "Home";
    public static final String KEY_FOLLOW = "MENU_FOLLOW";

    private String key;
    private String name;

    public menu(){
    }
    public menu(String key,String name){
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
    public List<menu> getListMenu(){
        List<menu> result = new ArrayList<menu>();
        result.add(new menu("MENU_HOME","Home"));
        result.add(new menu("MENU_FOLLOW","Theo dõi"));
        return result;
    }

}
