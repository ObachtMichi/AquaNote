package com.example.aquanote;


import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Value {

    private int id;
    private String valueType;
    private float valueNumber;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy 'at' H:m");
    String date;


    public Value(int id, String valueType, float valueNumber){
        this.id = id;
        this.valueType = valueType;
        this.valueNumber = valueNumber;
    }
    public Value(int id, float valueNumber, String valueType , String date){
        this.id = id;
        this.date = date;
        this.valueNumber = valueNumber;
        this.valueType=valueType;
    }

    public Value(int id, String valueType){
        this.valueType = valueType;
        this.id= id;
    }

    public Value(String valueType, float valueNumber){
        this.valueType = valueType;
        this.valueNumber = valueNumber;
        this.date = sdf.format(new Date());
    }


    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", valueType='" + valueType + '\'' +
                ", valueNumber=" + valueNumber +
                ", sdf=" + sdf +
                ", date='" + date + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public float getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(float valueNumber) {
        this.valueNumber = valueNumber;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
