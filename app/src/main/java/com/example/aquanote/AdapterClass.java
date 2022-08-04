package com.example.aquanote;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
                    editValue.setHint(value.getText().toString());
                    dialog.show();

                    final Button btn_Cancel = dialog.findViewById(R.id.btn_Cancel);
                    final Button btn_Accept = dialog.findViewById(R.id.btn_Accept);
                    final Button btn_Delete = dialog.findViewById(R.id.btn_Delete);



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
                            Value nValue = new Value(Integer.parseInt(buttonSelectEntry.getText().toString()), typ.getText().toString(), Float.valueOf(editValue.getText().toString()));
                            ag.changeValue(nValue);
                            dialog.dismiss();
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

}


