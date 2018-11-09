package com.finalproject.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public abstract class DbHelper extends SQLiteOpenHelper {
    protected static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "dbNewspaper";
    protected String tb_name;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + tb_name);
        onCreate(db);
    }

    public String getTb_name() {
        return tb_name;
    }
    @Override
    public abstract void onCreate(SQLiteDatabase db);
//    String SQL_String = "CREATE TABLE " + TB_STUDENT + "("
//            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
//            + KEY_CLASS + " TEXT" + ")";
//        db.execSQL(SQL_String);

    public abstract void setTb_name();

//    public void insertStudent(Student student) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String nullColumnHack = null; // Allow null value
//        ContentValues values = new ContentValues();
//        if (student.getId() != -1) {
//            values.put(KEY_ID, student.getId());
//            values.put(KEY_NAME, student.getName());
//            values.put(KEY_CLASS, student.getClassName());
//            db.insert(TB_STUDENT, nullColumnHack, values);
//        }
//        db.close();
//    }
//
//    public Student getStudent(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] fields = {KEY_ID, KEY_NAME, KEY_CLASS};
//        String criterials = KEY_ID + "=?";
//        String[] parameters = {String.valueOf(id)};
//        String groupby = null;
//        String having = null;
//        String orderby = null;
//        Cursor cursor = db.query(TB_STUDENT, fields, criterials,
//                parameters, groupby, having, orderby);
//        if (cursor != null)
//            cursor.moveToFirst();
//        Student student = new Student(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
//        return student;
//    }
//
//    public List<Student> getAllStudents() {
//        List<Student> students = new ArrayList<Student>();
//        String[] criterial = null;
//        String selectQuery = "SELECT  * FROM " + TB_STUDENT;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, criterial);
//        if (cursor.moveToFirst()) {
//            do {
//                Student student = new Student();
//                student.setId(Integer.parseInt(cursor.getString(0)));
//                student.setName(cursor.getString(1));
//                student.setClassName(cursor.getString(2));
//                // Add student to list
//                students.add(student);
//            } while (cursor.moveToNext());
//        }
//        return students;
//    }
//
//    public int updateStudent(Student student) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, student.getName());
//        values.put(KEY_CLASS, student.getClassName());
//        String whereClause = KEY_ID + "=?";
//        String[] whereArgs = {String.valueOf(student.getId())};
//        return db.update(TB_STUDENT, values, whereClause, whereArgs);
//    }
//
//    public void deleteStudent(Student student) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String whereClause = "_id=?";
//        String[] whereArgs = {String.valueOf(student.getId())};
//        db.delete(TB_STUDENT, whereClause, whereArgs);
//    }

}
