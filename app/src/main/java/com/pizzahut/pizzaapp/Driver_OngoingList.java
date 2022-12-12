package com.pizzahut.pizzaapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Driver_OngoingList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Driver_Ongoing> list;
    Driver_OngoingListAdapter adapter = null;
    sqlhelper sqliteHelper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__ongoing_list);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new Driver_OngoingListAdapter(this, R.layout.driver_ongoing_items, list);
        gridView.setAdapter(adapter);

        //get data from sqlite

        Cursor cursor = MainActivity.sqliteHelper.getDriverData("SELECT * FROM DRIVERDETAILS");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String newdriverid = cursor.getString(1);
            String newdrivername = cursor.getString(2);
            String newdrivernumber = cursor.getString(3);
            String newdriveraddress = cursor.getString(4);
            String newdriveremail = cursor.getString(5);
            String newdrivervehiclemodel = cursor.getString(6);
            String newdrivervehiclenumber = cursor.getString(7);
            String newdriverdob = cursor.getString(8);


            list.add(new Driver_Ongoing(newdriverid, newdrivername, newdrivernumber, newdriveraddress, newdriveremail, newdrivervehiclemodel, newdrivervehiclenumber, newdriverdob,id));


        }
        adapter.notifyDataSetChanged();


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Driver_OngoingList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            //update
                            Cursor c = MainActivity.sqliteHelper.getDriverData("SELECT id FROM DRIVERDETAILS");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }

                            //show dialog update
                            showDialogUpdateDriver(Driver_OngoingList.this,arrID.get(position));

                            Toast.makeText(getApplicationContext(), "Update..", Toast.LENGTH_LONG).show();

                        } else {
                            //delete
                            Cursor c = MainActivity.sqliteHelper.getDriverData("SELECT id FROM DRIVERDETAILS");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDeleteDriver(arrID.get(position));
                        }
                    }

                });

                dialog.show();
                return true;
            }
        });


    }
    private void showDialogUpdateDriver(Activity activity, final int position){

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.driver_update_ongoing_activity);
        dialog.setTitle("Update");

        final EditText newdriverid = (EditText) dialog.findViewById(R.id.newdriverid);
        final EditText newdrivername = (EditText) dialog.findViewById(R.id.newdrivername);
        final EditText newdrivernumber = (EditText) dialog.findViewById(R.id.newdrivernumber);
        final EditText newdriveraddress = (EditText) dialog.findViewById(R.id.newdriveraddress);
        final EditText newdriveremail = (EditText) dialog.findViewById(R.id.newdriveremail);
        final EditText newdrivervehiclemodel = (EditText) dialog.findViewById(R.id.newdrivervehiclemodel);
        final EditText newdrivervehiclenumber = (EditText) dialog.findViewById(R.id.newdrivervehiclenumber);
        final EditText newdriverdob = (EditText) dialog.findViewById(R.id.newdriverdob);
        Button btnnewdriverupdated = (Button) dialog.findViewById(R.id.btnnewdriverupdated);

        //set width for dialog

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);

        //set height for dialog

        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.8);
        dialog.getWindow().setLayout(width,height);
        dialog.show();


        sqliteHelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);
        String driver_id = getIntent().getStringExtra("ID");

//        Cursor cursor = Driver_MainActivity.sqliteHelper.getDriverDetails(driver_id);
//
//        while (cursor.moveToNext()){
//            String driverid = cursor.getString(1);
//            String name = cursor.getString(2);
//            String number = cursor.getString(3);
//            String address = cursor.getString(4);
//            String email = cursor.getString(5);
//            String model = cursor.getString(6);
//            String Vnumber = cursor.getString(7);
//            String dob = cursor.getString(8);
//
//
//
//            newdriverid.setText(driverid);
//            newdrivername.setText(name);
//            newdrivernumber.setText(number);
//            newdriveraddress.setText(address);
//            newdriveremail.setText(email);
//            newdrivervehiclemodel.setText(model);
//            newdrivervehiclenumber.setText(Vnumber);
//            newdriverdob.setText(dob);
//        }









        btnnewdriverupdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = newdriverid.getText().toString();
                String s2 = newdrivername.getText().toString();
                String s3 = newdrivernumber.getText().toString();
                String s4 = newdriveraddress.getText().toString();
                String s5 = newdriveremail.getText().toString();
                String s6 = newdrivervehiclemodel.getText().toString();
                String s7 = newdrivervehiclenumber.getText().toString();
                String s8 = newdriverdob.getText().toString().trim();

                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")||s7.equals("")||s8.equals("")){
                    Toast.makeText(getApplicationContext(), "Field Empty", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        MainActivity.sqliteHelper.updateDataDriver(
                                newdriverid.getText().toString().trim(),
                                newdrivername.getText().toString().trim(),
                                newdrivernumber.getText().toString().trim(),
                                newdriveraddress.getText().toString().trim(),
                                newdriveremail.getText().toString().trim(),
                                newdrivervehiclemodel.getText().toString().trim(),
                                newdrivervehiclenumber.getText().toString().trim(),
                                newdriverdob.getText().toString().trim(),
                                position
                        );
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
                    }catch (Exception error){
                        Log.e("Error",error.getMessage());
                    }

                    updateOngoingListDriver();
                }



            }
        });


    }

    private void showDialogDeleteDriver(final int idOngongdriver){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(Driver_OngoingList.this);
        dialogDelete.setTitle("Warning");
        dialogDelete.setMessage("Do you really want to delete this ongoing oder?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    MainActivity.sqliteHelper.deleteDataDriver(idOngongdriver);
                    MainActivity.sqliteHelper.deleteDataDriver(idOngongdriver);
                    Toast.makeText(getApplicationContext(),"Deleted successfully",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("Error in delete",e.getMessage());
                }

                updateOngoingListDriver();

            }
        });
        dialogDelete.setNegativeButton("Cancel" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }


    private void updateOngoingListDriver(){
        Cursor cursor = MainActivity.sqliteHelper.getDriverData("SELECT * FROM DRIVERDETAILS");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String newdriverid = cursor.getString(1);
            String newdrivername = cursor.getString(2);
            String newdrivernumber = cursor.getString(3);
            String newdriveraddress = cursor.getString(4);
            String newdriveremail = cursor.getString(5);
            String newdrivervehiclemodel = cursor.getString(6);
            String newdrivervehiclenumber = cursor.getString(7);
            String newdriverdob = cursor.getString(8);

            list.add(new Driver_Ongoing(newdriverid,newdrivername,newdrivernumber,newdriveraddress,newdriveremail,newdrivervehiclemodel,newdrivervehiclenumber,newdriverdob,id));

        }
        adapter.notifyDataSetChanged();
    }

}