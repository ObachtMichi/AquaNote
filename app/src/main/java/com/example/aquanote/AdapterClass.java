package com.example.aquanote;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    private ArrayList<Value> valueList;
    public AdapterClass(ArrayList<Value> valueList){
        this.valueList = valueList;
    }
    private Value val;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView value, date, typ;
        private Button buttonSelectEntry;
        private String aquariumName = MainActivity.getName();
        private DatePickerDialog datePickerDialog;
        private Button dateButton;
        int year1, hour1, month1, minute1, day1;
        int nYear, nHour, nMonth, nMinute, nDay;
        String date1;



        public MyViewHolder(final View view){
            super(view);
            value = view.findViewById(R.id.textListValue);
            date = view.findViewById(R.id.textListDate);
            buttonSelectEntry = view.findViewById(R.id.buttonSelectEntry);
            typ = view.findViewById(R.id.textValueTyp);


            buttonSelectEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity_graph ag = new activity_graph();


                    final Dialog dialog = new Dialog(view.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.dialog_value_type);
                    EditText editValue = dialog.findViewById(R.id.editValue);
                    dateButton = dialog.findViewById(R.id.datePickerButton);
                    editValue.setHint(value.getText().toString());
                    dateButton.setText(date.getText().toString());
                    dialog.show();

                    final Button btn_Cancel = dialog.findViewById(R.id.btn_Cancel);
                    final Button btn_Accept = dialog.findViewById(R.id.btn_Accept);
                    final Button btn_Delete = dialog.findViewById(R.id.btn_Delete);


                    Calendar cal = Calendar.getInstance();
                    year1 = cal.get(Calendar.YEAR);
                    month1 = cal.get(Calendar.MONTH);
                    day1 = cal.get(Calendar.DAY_OF_MONTH);
                    hour1 = cal.get(Calendar.HOUR_OF_DAY);
                    minute1 = cal.get(Calendar.MINUTE);


                    dateButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                                    new TimePickerDialog.OnTimeSetListener() {

                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay,
                                                              int minute) {

                                            nHour = hourOfDay;
                                            nMinute = minute;
                                            date1 = makeDataString(nDay, nMonth, nYear, nHour, nMinute);
                                            dateButton.setText(date1);
                                        }
                                    }, hour1, minute1, false);

                            timePickerDialog.show();

                            DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                                    new DatePickerDialog.OnDateSetListener() {

                                        @SuppressLint("SetTextI18n")
                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {
                                            nYear = year;
                                            nMonth = monthOfYear + 1;
                                            nDay = dayOfMonth;

                                        }
                                    }, year1, month1, day1);
                            datePickerDialog.show();

                        }
                    }


                    );



                    btn_Delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            final Dialog dialogSure = new Dialog(view.getContext());
                            dialogSure.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialogSure.setCancelable(false);
                            dialogSure.setContentView(R.layout.delete_sure);
                            dialogSure.show();

                            final Button btn_CancelSure = dialogSure.findViewById(R.id.btn_CancelSure);
                            final Button btn_DeleteSure = dialogSure.findViewById(R.id.btn_DeleteSure);

                            btn_DeleteSure.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    ag.deleteEntry(new Value(Integer.parseInt(buttonSelectEntry.getText().toString()), typ.getText().toString()));
                                    dialogSure.dismiss();
                                    dialog.dismiss();


                                    Intent i = new Intent(view.getContext(), activity_graph.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    view.getContext().startActivity(i);



                                }
                            });

                            btn_CancelSure.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogSure.dismiss();
                                }
                            });
                        }
                    });



                    btn_Cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    btn_Accept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (editValue.getText().toString().equals(".")){
                                Toast.makeText(view.getContext(), "Not Valid", Toast.LENGTH_SHORT).show();
                            } else {
                                if (!(editValue.getText().toString().equals(""))) {
                                    Value nValue = new Value(Integer.parseInt(buttonSelectEntry.getText().toString()), typ.getText().toString(), Float.valueOf(editValue.getText().toString()));
                                    ag.changeValue(nValue);
                                } else {
                                    Value nValue = new Value(Integer.parseInt(buttonSelectEntry.getText().toString()), typ.getText().toString(), Float.valueOf(value.getText().toString()));
                                    ag.changeValue(nValue);
                                }

                                dialog.dismiss();
                                Intent i = new Intent(view.getContext(), activity_graph.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                view.getContext().startActivity(i);
                            }
                        }
                    });




                }
            });
        }


    }

    @NonNull
    @Override
    public AdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, int position) {
        float value = valueList.get(position).getValueNumber();
        String date = valueList.get(position).getDate();
        int id = valueList.get(position).getId();
        String typVar = valueList.get(position).getValueType();
        holder.typ.setText(typVar);
        holder.value.setText(String.valueOf(value));
        holder.date.setText(date);
        holder.buttonSelectEntry.setText(String.valueOf(id));

    }

    @Override
    public int getItemCount() {
        return valueList.size();
    }




    private String makeDataString(int day, int month, int year, int hour, int minute){
        return getMonthFormat(month) + " " + day + " " + year + " at " + hour + ":" + minute;
    }

    private String getMonthFormat(int month) {
            if(month == 1)
                return "JAN";
            if(month == 2)
                return "FEB";
            if(month == 3)
                return "MAR";
            if(month == 4)
                return "APR";
            if(month == 5)
                return "MAY";
            if(month == 6)
                return "JUN";
            if(month == 7)
                return "JUL";
            if(month == 8)
                return "AUG";
            if(month == 9)
                return "SEP";
            if(month == 10)
                return "OCT";
            if(month == 11)
                return "NOV";
            if(month == 12)
                return "DEC";

            //default should never happen
            return "JAN";
        }
}


