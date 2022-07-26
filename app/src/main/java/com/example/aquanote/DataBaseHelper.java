package com.example.aquanote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper{

    //-----------------------------------------Variablen---------------------------------------------------

    public static final String COLUMN_VALUE_NUMBER= "VALUE_NUMBER";
    public static final String COLUMN_VALUE_DATE= "VALUE_DATE";
    public static final String COLUMN_ID = "ID";

    //-----------------------------------------Variablen---------------------------------------------------

    //Constructor
    public DataBaseHelper(@Nullable Context context, String name) {
        super(context, name, null, 1);
    }



    //-----------------------------------------On Create---------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {
        List<String> VALUE_NAMES = new ArrayList<>();

        if(!(SelectValuesStart.getCheckBox() == null)) {
            for (int i = 0; i < SelectValuesStart.getCheckBox().length; i++) {
                if (SelectValuesStart.getCheckBox()[i].isChecked()) {
                    VALUE_NAMES.add(SelectValuesStart.getCheckBox()[i].getText().toString());
                }
            }

            for (int i = 0; i < VALUE_NAMES.size(); i++) {
                String VALUE_NAME = VALUE_NAMES.get(i);
                String createTableStatement = "CREATE TABLE " + VALUE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_VALUE_NUMBER + " FLOAT, " + COLUMN_VALUE_DATE + " TEXT, " + " VALUE_TYP Text)";
                db.execSQL(createTableStatement);
            }
           }

        //Typ Tabelle
        for (int i = 0; i < VALUE_NAMES.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put("name", VALUE_NAMES.get(i));
            db.insert("sqlite_sequence", null, cv);
        }

    }
    //-----------------------------------------On Create---------------------------------------------------




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


    public boolean addEntry(Value value){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_VALUE_NUMBER, value.getValueNumber());
        cv.put(COLUMN_VALUE_DATE, value.getDate());
        cv.put("VALUE_TYP", value.getValueType());


        long insert = db.insert(value.getValueType(), null, cv);
        return insert != -1;
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

        }

        cursor.close();
        db.close();
        return returnList;
    }


    public ArrayList<Value> getDateAndValue(String type){
        ArrayList<Value> retListe = new ArrayList<>();

        String queryString = "SELECT * FROM " + type;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);


        if(cursor.moveToLast()) {
            do {
                int id = cursor.getInt(0);
                float value = cursor.getFloat(1);
                String datum = cursor.getString(2);
                String typ = cursor.getString(3);
                retListe.add(new Value(id, value, typ, datum));
            } while (cursor.moveToPrevious());

        }

        cursor.close();
        db.close();
        return retListe;
    }

    public Boolean deleteEntry(Value value){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + value.getValueType() + " WHERE ID = " + value.getId();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        } else{
            return false;
        }
    }


    public Boolean changeEntryValue(Value value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + value.getValueType() + " SET VALUE_NUMBER = '" + value.getValueNumber() + "' WHERE ID= " + value.getId();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        } else{
            return false;
        }
    }

    public Boolean changeEntryDate(Value value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + value.getValueType() + " SET VALUE_DATE = '" + value.getDate() + "' WHERE ID= " + value.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            return true;
        } else{
            return false;
        }
    }
}
