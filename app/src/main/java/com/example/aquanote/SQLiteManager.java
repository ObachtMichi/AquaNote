package com.example.aquanote;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;



public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "AquaDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Aqua";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String TYP_FIELD = "typ";
    private static final String VALUE_FIELD = "value";
    private static final String DELETED_FIELD = "deleted";



    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context){
        if (sqLiteManager == null){
            sqLiteManager = new SQLiteManager(context);
        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE")
                .append(MainActivity.getName())
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(" TEXT, ")
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT, ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }



    public void addTypToDatabase(CheckBox[] arr){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < arr.length; i++) {

            contentValues.put(ID_FIELD, i);
            contentValues.put(TYP_FIELD, arr[i].getText().toString());
        }

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

}
