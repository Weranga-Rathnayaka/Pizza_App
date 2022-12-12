package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {


    GridView gridView;
    ArrayList<Order> list;
    Button btn;
    OrderListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        gridView = (GridView) findViewById(R.id.grideViewOrders);
        // btn = (Button) findViewById(R.id.remove);
        list = new ArrayList<>();
        adapter = new OrderListAdapter(getApplicationContext(),R.layout.orders,list);
        gridView.setAdapter(adapter);

        sqlhelper obj= new sqlhelper(this, "FoodDB.sqlite",null, 1);
        Cursor c=obj.getData();
        list.clear();
        while (c.moveToNext()){
            int orderId = c.getInt( 0);
            String firstName=c.getString(1);
            String lastName=c.getString(2);
            String email=c.getString(3);
            String contactNumber=c.getString(4);
            String address=c.getString(5);
            String cardName=c.getString(6);
            String cardNumber=c.getString(7);
            String expDate=c.getString(8);
            String security=c.getString(9);
            String productId=c.getString(10);
            String qty=c.getString(11);
            String date = c.getString(12);
            list.add(new Order(orderId,productId,qty,address,contactNumber,date));


        }
        adapter.notifyDataSetChanged();

        //Admin panel update, delete side

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence[] items = {"Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(OrderList.this);

                dialog.setTitle("Choose an action");

                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {


                        //delete
                        sqlhelper obj= new sqlhelper(getApplicationContext(), "FoodDB.sqlite",null, 1);
                        Cursor c=obj.getData();
                        ArrayList<Integer> arrID = new ArrayList<Integer>();
                        while (c.moveToNext()){
                            arrID.add(c.getInt(0));
                        }
                        showDialogToDelete(arrID.get(position));


                    }
                });

                dialog.show();
                return true;
            }
        });
    }


    //create a dialog to confirm Update

    ImageView imageView;



    private void showDialogToDelete(final int orderId){

        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(OrderList.this);

        dialogDelete.setTitle("Warnning!!");
        dialogDelete.setMessage("Are you sure you want to delete ?");

        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sqlhelper obj= new sqlhelper(getApplicationContext(), "FoodDB.sqlite",null, 1);

                try {


                    obj.deleteOrderData(orderId);
                    Toast.makeText(getApplicationContext(), "Delete Successfully...", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("Error",e.getMessage());
                }

                updateOrderList();

            }
        });

        dialogDelete.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        dialogDelete.show();
    }






    //refresh grid view after update
    private void updateOrderList(){

        //get all data from sqlite

        sqlhelper obj= new sqlhelper(this, "FoodDB.sqlite",null, 1);
        Cursor c=obj.getData();
        list.clear();

        while (c.moveToNext()) {
            int orderId = c.getInt( 0);
            String firstName=c.getString(1);
            String lastName=c.getString(2);
            String email=c.getString(3);
            String contactNumber=c.getString(4);
            String address=c.getString(5);
            String cardName=c.getString(6);
            String cardNumber=c.getString(7);
            String expDate=c.getString(8);
            String security=c.getString(9);
            String productId=c.getString(10);
            String qty=c.getString(11);
            String date = c.getString(12);
            list.add(new Order(orderId,productId,qty,address,contactNumber,date));

        }
        adapter.notifyDataSetChanged();

    }


}