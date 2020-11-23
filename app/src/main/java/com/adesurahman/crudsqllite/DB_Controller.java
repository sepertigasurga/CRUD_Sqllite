package com.adesurahman.crudsqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.DB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, NPM TEXT UNIQUE, NAMA TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(sqLiteDatabase);
    }

    public void insert_student(String npm, String nama){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NPM", npm);
        contentValues.put("NAMA", nama);
        this.getWritableDatabase().insertOrThrow("STUDENTS","",contentValues);
    }

    public void delete_student(String npm){
        this.getWritableDatabase().delete("STUDENTS","npm='"+npm+"'",null);
    }

    public void update_student(String old_npm, String new_npm){
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET NPM='"+new_npm+"' WHERE NAMA='"+old_npm+"'");
    }

    public void list_all_students(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
    }
}
