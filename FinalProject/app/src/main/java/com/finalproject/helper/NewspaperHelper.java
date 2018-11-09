package com.finalproject.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.model.Newspaper;

import java.util.ArrayList;
import java.util.List;

public class NewspaperHelper extends DbHelper {
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_THUMB_ART = "thumb_art";

    public NewspaperHelper(Context context) {
        super(context);
        setTb_name();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + this.tb_name + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TITLE + " TEXT,"
            + KEY_URL + " TEXT,"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_THUMB_ART + " TEXT"
            + ")";
        db.execSQL(SQL_String);
    }

    @Override
    public void setTb_name() {
        this.tb_name = "Newspaper";
    }

    public void insert(Newspaper newspaper) {
        Newspaper newspaper1 = get(newspaper.getUrl());
        if(newspaper1 != null){
            return;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        String nullColumnHack = null; // Allow null value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, newspaper.getTitle());
        values.put(KEY_URL, newspaper.getUrl());
        values.put(KEY_DESCRIPTION, newspaper.getDescription());
        values.put(KEY_THUMB_ART, newspaper.getThumb_art());
        db.insert(tb_name, nullColumnHack, values);
        db.close();
    }

    public Newspaper get(String url) {
        Newspaper mNewspaper  = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] fields = {KEY_TITLE, KEY_URL, KEY_DESCRIPTION,KEY_THUMB_ART};
        String criterials = KEY_URL + "=?";
        String[] parameters = {String.valueOf(url)};
        String groupby = null;
        String having = null;
        String orderby = null;
        Cursor cursor = db.query(tb_name, fields, criterials,
                parameters, groupby, having, orderby);
        if (cursor != null && cursor.moveToFirst()){
            mNewspaper = new Newspaper(cursor.getString(0),
            cursor.getString(1), cursor.getString(2),cursor.getString(3));
        }
        return mNewspaper;
    }

    public List<Newspaper> getAllNewspaper() {
        List<Newspaper> aNewspaper = new ArrayList<Newspaper>();
        String[] criterial = null;
        String selectQuery = "SELECT  * FROM " + tb_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                Newspaper newspaper = new Newspaper();
                newspaper.setTitle(cursor.getString(1));
                newspaper.setUrl(cursor.getString(2));
                newspaper.setDescription(cursor.getString(3));
                newspaper.setThumb_art(cursor.getString(4));
                aNewspaper.add(newspaper);
            } while (cursor.moveToNext());
        }
        return aNewspaper;
    }

    public int update(Newspaper newspaper) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, newspaper.getTitle());
        values.put(KEY_DESCRIPTION, newspaper.getDescription());
        values.put(KEY_THUMB_ART, newspaper.getThumb_art());
        String whereClause = KEY_URL + "=?";
        String[] whereArgs = {String.valueOf(newspaper.getUrl())};
        return db.update(tb_name, values, whereClause, whereArgs);
    }

    public void delete(Newspaper newspaper) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = KEY_URL+"=?";
        String[] whereArgs = {String.valueOf(newspaper.getUrl())};
        db.delete(tb_name, whereClause, whereArgs);
    }
}
