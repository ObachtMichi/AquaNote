package com.example.aquanote;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;


public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String COLUMN_VALUE_TYPE = "VALUE_TYPE";
    public static final String COLUMN_VALUE_NUMBER= "VALUE_NUMBER";
    public static final String COLUMN_VALUE_DATE= "VALUE_DATE";
    public static final String SQLLITE_SCHEMA= "SQLLITE_SCHEMA";

    public static final String COLUMN_ID = "ID";
    private List<String> VALUE_NAMES;


    public DataBaseHelper(@Nullable Context context) {
        super(context, "values.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        VALUE_NAMES = new ArrayList<>();

        //db.execSQL("CREATE TABLE " + SQLLITE_SCHEMA+ " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_VALUE_TYPE + " TEXT)");
        //fillSQLSchema(SelectValuesStart.getCheckBox()[1].getText().toString());


        if(!(SelectValuesStart.getCheckBox() == null)) {
            for (int i = 0; i < SelectValuesStart.getCheckBox().length; i++) {
                if (SelectValuesStart.getCheckBox()[i].isChecked()) {
                    VALUE_NAMES.add(SelectValuesStart.getCheckBox()[i].getText().toString());
                }
            }


            for (int i = 0; i < VALUE_NAMES.size(); i++) {
                String VALUE_NAME = VALUE_NAMES.get(i);
                String createTableStatement = "CREATE TABLE " + VALUE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_VALUE_NUMBER + " FLOAT, " + COLUMN_VALUE_DATE + " TEXT)";
                db.execSQL(createTableStatement);
            }
           }

        //SQLiteDatabase dbW = this.getWritableDatabase();
        for (int i = 0; i < VALUE_NAMES.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put("name", VALUE_NAMES.get(i));
            db.insert("sqlite_sequence", null, cv);
        }




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


    public boolean addValueToType(Value value){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_VALUE_NUMBER, value.getValueNumber());
        cv.put(COLUMN_VALUE_DATE, value.getDate());

        long insert = db.insert(value.getValueType(), null, cv);
        if(insert == -1){
            return false;
        }
        return true;
    }


    public List<String> getValueTypes() {
        List<String> returnList = new ArrayList<>();

        String queryString = "SELECT name FROM sqlite_sequence";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);


        if(cursor.moveToFirst()) {
            do {
                returnList.add(cursor.getString(0));
            } while (cursor.moveToNext());

        } else{

        }

        cursor.close();
        db.close();
        return returnList;
    }



}
