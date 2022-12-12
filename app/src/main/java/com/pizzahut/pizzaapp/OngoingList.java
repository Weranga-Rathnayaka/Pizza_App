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

public class OngoingList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Ongoing> list;
    OngoingListAdapter adapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_list);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new OngoingListAdapter(this, R.layout.ongoing_items, list);
        gridView.setAdapter(adapter);

        //get data from sqlite

        Cursor cursor = MainActivity.sqliteHelper.getDataDelivery("SELECT * FROM ONGOING");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String edtoderid = cursor.getString(1);
            String edtname = cursor.getString(2);
            String edtnumber = cursor.getString(3);
            String edtaddress = cursor.getString(4);
            String edtprice = cursor.getString(5);
            String edtdriverid = cursor.getString(6);
            String edtdrivername = cursor.getString(7);
            String edtcomplete = cursor.getString(8);


            list.add(new Ongoing(edtoderid, edtname, edtnumber, edtaddress, edtprice, edtdriverid, edtdrivername, edtcomplete,id));


        }
        adapter.notifyDataSetChanged();


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(OngoingList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            //update
                            Cursor c = MainActivity.sqliteHelper.getDataDelivery("SELECT id FROM ONGOING");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }

                            //show dialog update
                            showDialogUpdate(OngoingList.this,arrID.get(position));

                            Toast.makeText(getApplicationContext(), "Update..", Toast.LENGTH_LONG).show();

                        } else {
                            //delete
                            Cursor c = MainActivity.sqliteHelper.getDataDelivery("SELECT id FROM ONGOING");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }

                });

                dialog.show();
                return true;
            }
        });


    }
    private void showDialogUpdate(Activity activity, final int position){

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_ongoing_activity);
        dialog.setTitle("Update");

        final EditText edtoderid = (EditText) dialog.findViewById(R.id.edtoderid);
        final EditText edtname = (EditText) dialog.findViewById(R.id.edtname);
        final EditText edtnumber = (EditText) dialog.findViewById(R.id.edtnumber);
        final EditText edtaddress = (EditText) dialog.findViewById(R.id.edtaddress);
        final EditText edtprice = (EditText) dialog.findViewById(R.id.edtprice);
        final EditText edtdriverid = (EditText) dialog.findViewById(R.id.edtdriverid);
        final EditText edtdrivername = (EditText) dialog.findViewById(R.id.edtdrivername);
        final EditText edtcomplete = (EditText) dialog.findViewById(R.id.edtcomplete);
        Button btnupdate = (Button) dialog.findViewById(R.id.btnupdate);

        //set width for dialog

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);

        //set height for dialog

        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.8);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = edtoderid.getText().toString();
                String s2 = edtname.getText().toString();
                String s3 = edtnumber.getText().toString();
                String s4 = edtaddress.getText().toString();
                String s5 = edtprice.getText().toString();
                String s6 = edtdriverid.getText().toString();
                String s7 = edtdrivername.getText().toString();
                String s8 = edtcomplete.getText().toString().trim();
                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("") || s7.equals("") || s8.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Empty", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        MainActivity.sqliteHelper.updateDataDelivery(
                                edtoderid.getText().toString().trim(),
                                edtname.getText().toString().trim(),
                                edtnumber.getText().toString().trim(),
                                edtaddress.getText().toString().trim(),
                                edtprice.getText().toString().trim(),
                                edtdriverid.getText().toString().trim(),
                                edtdrivername.getText().toString().trim(),
                                edtcomplete.getText().toString().trim(),
                                position
                        );
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                    } catch (Exception error) {
                        Log.e("Error", error.getMessage());
                    }
                    updateOngoingList();
                }

            }
        });


    }

    private void showDialogDelete(final int idOngong){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(OngoingList.this);
        dialogDelete.setTitle("Warning");
        dialogDelete.setMessage("Do you really want to delete this ongoing oder?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    MainActivity.sqliteHelper.deleteDataDelivery(idOngong);
                    MainActivity.sqliteHelper.deleteDataDelivery(idOngong);
                    Toast.makeText(getApplicationContext(),"Deleted successfully",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("Error in delete",e.getMessage());
                }
                updateOngoingList();
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


    private void updateOngoingList(){
        Cursor cursor = MainActivity.sqliteHelper.getDataDelivery("SELECT * FROM ONGOING");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String edtoderid = cursor.getString(1);
            String edtname = cursor.getString(2);
            String edtnumber = cursor.getString(3);
            String edtaddress = cursor.getString(4);
            String edtprice = cursor.getString(5);
            String edtdriverid = cursor.getString(6);
            String edtdrivername = cursor.getString(7);
            String edtcomplete = cursor.getString(8);

            list.add(new Ongoing(edtoderid,edtname,edtnumber,edtaddress,edtprice,edtdriverid,edtdrivername,edtcomplete,id));

        }
        adapter.notifyDataSetChanged();
    }

}