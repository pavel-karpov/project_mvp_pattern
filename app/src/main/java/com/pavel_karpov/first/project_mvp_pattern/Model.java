package com.pavel_karpov.first.project_mvp_pattern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class Model implements PresenterListener.Model {
    Context ctx;
    Model(Context ctx){
        this.ctx = ctx;
    }
    SQLiteDatabase db;
    void insert_data(SQLiteDatabase db, String firstName,String lastName,String position){
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName",firstName);
        contentValues.put("LastName",lastName);
        contentValues.put("Position",position);
        db.insert(SqLite.Table_name,null,contentValues);
    }
    @Override
    public void loadMessage(String first, String last, String position) {
        db = new SqLite(ctx).getWritableDatabase();
        if (db!=null){
          insert_data(db,first,last,position);
          Toast.makeText(ctx,"Данные загружены",Toast.LENGTH_LONG).show();
          db.close();
        }
    }

    @Override
    public String readMessage() {
        String text="";
        db = new SqLite(ctx).getWritableDatabase();
        if (db!=null){
            Cursor cursor = db.query(SqLite.Table_name,null,null,null,null,null,null);
            if (cursor!=null){
                cursor.moveToFirst();
                do {
                    text+=cursor.getString(cursor.getColumnIndex("FirstName"))+ " ";
                    text+=cursor.getString(cursor.getColumnIndex("LastName"))+ " ";
                    text+=cursor.getString(cursor.getColumnIndex("Position"))+ "\n";
                    cursor.moveToNext();
                }
                while (!cursor.isAfterLast());
            cursor.close();
            }
            db.close();
        }
        return text;
    }
    public class SqLite extends SQLiteOpenHelper{
        private static final String Database_name = "Notes.db";
        private static final int Database_version =1;
        private static final String Table_name = "note";
        public SqLite(@Nullable Context context) {
            super(context, Database_name, null, Database_version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+Table_name+" (_id integer primary key autoincrement, " + "FirstName TEXT, " + "LastName TEXT, "+"Position TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}
