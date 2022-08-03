package com.example.aquanote;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Currency;
import java.util.List;
import java.util.Locale;


public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String COLUMN_VALUE_NUMBER= "VALUE_NUMBER";
    public static final String COLUMN_VALUE_DATE= "VALUE_DATE";

    public static final String COLUMN_ID = "ID";
    private List<String> VALUE_NAMES;


    public DataBaseHelper(@Nullable Context context, String name) {
        super(context, name, null, 1);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {
        VALUE_NAMES = new ArrayList<>();

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

        //Typ Tabelle
        for (int i = 0; i < VALUE_NAMES.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put("name", VALUE_NAMES.get(i));
            db.insert("sqlite_sequence", null, cv);
        }
/*
        //Image
        String BLOB_QUERY = "CREATE TABLE image (image BLOB)";
        db.execSQL(BLOB_QUERY);

        byte[] byteArr = activity_home_screen.getBytes(MainActivity.getPicture());

        ContentValues cv = new  ContentValues();
        cv.put("image", byteArr);
        db.insert("image", null, cv);


 */


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

        }

        cursor.close();
        db.close();
        return returnList;
    }

/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bitmap getAquariumPicture(){

        Bitmap bmp = null;
        String queryString = "SELECT image FROM image";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                byte[] byteArr;
                byteArr = cursor.getBlob(0);
                bmp = activity_home_screen.getImage(byteArr);
            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();
        return bmp;
    }

 */

}
