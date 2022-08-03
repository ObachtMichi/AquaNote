package com.example.aquanote;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Value {

    private int id;
    private String valueType;
    private float valueNumber;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy 'at' HH:mm:ss");
    String date;

    public Value(){
        this.date = sdf.format(new Date());
    }

    public Value(String date, float valueNumber){
        this.date = date;
        this.valueNumber = valueNumber;
    }
    public Value(int id, String valueType, float valueNumber) {
        this.id = id;
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
