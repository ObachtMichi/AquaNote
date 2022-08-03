package com.example.aquanote;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


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

    @SuppressLint("SimpleDataFormat")
    private static final DateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context) {
        if (sqLiteManager == null) {
            sqLiteManager = new SQLiteManager(context);
        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        StringBuilder SQL_CREATE_ENTRIES;
        SQL_CREATE_ENTRIES = new StringBuilder()
                .append("CREATE TABLE")
                .append(MainActivity.getName())
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT")
                .append(TYP_FIELD)
                .append(" TEXT, ")
                .append(VALUE_FIELD)
                .append(" TEXT, ");
        StringBuilder SQL_DELETE_ENTRIES;
        SQL_DELETE_ENTRIES = new StringBuilder()
                .append("DROP TABLE IF EXISTS")
                .append(MainActivity.getName());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


    public void addTypToDatabase(CheckBox[] arr) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < arr.length; i++) {

            contentValues.put(ID_FIELD, i);
            contentValues.put(TYP_FIELD, arr[i].getText().toString());
        }

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

}
