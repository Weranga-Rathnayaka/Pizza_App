package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.pizzahut.pizzaapp.driver_Add.sqLiteHelper;

public class assign_order extends AppCompatActivity {

    EditText edtoderid,edtname,edtnumber,edtaddress,edtprice,edtdriverid,edtdrivername,edtcomplete;
    Button btnadd,btnview;

    sqlhelper sqliteHelper;

    public static sqlhelper sqlhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_order);

        init();

        sqliteHelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ONGOING(ID INTEGER PRIMARY KEY AUTOINCREMENT,edtoderid VARCHAR, edtname VARCHAR,edtnumber VARCHAR,edtaddress VARCHAR,edtprice VARCHAR,edtdriverid VARCHAR,edtdrivername VARCHAR, edtcomplete VARCHAR  )");



        //catch the order table values

        String order_id = getIntent().getStringExtra("orderID");




        Cursor cursor = startup_screen.sqliteHelper.getOrderDetailsToDelivery(order_id);

        while (cursor.moveToNext()){
            String orderId = cursor.getString(0);
            String name = cursor.getString(1);
            String contact = cursor.getString(4);
            String address = cursor.getString(5);



            edtoderid.setText(orderId);
            edtname.setText(name);
            edtnumber.setText(contact);
            edtaddress.setText(address);
        }


/*



        Cursor cursor1 = MainActivity.sqliteHelper.getUserData("sawi");

        while (cursor1.moveToNext()){
            String user = cursor1.getString(0);
            String email = cursor1.getString(1);
            String con = cursor1.getString(2);
            String pass = cursor1.getString(3);

            edtoderid.setText(user);
            edtname.setText(email);
            edtnumber.setText(con);
            edtaddress.setText(pass);

        }

         */






        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    sqLiteHelper.insertDataDelivery(
                            edtoderid.getText().toString().trim(),
                            edtname.getText().toString().trim(),
                            edtnumber.getText().toString().trim(),
                            edtaddress.getText().toString().trim(),
                            edtprice.getText().toString().trim(),
                            edtdriverid.getText().toString().trim(),
                            edtdrivername.getText().toString().trim(),
                            edtcomplete.getText().toString().trim()


                    );

                    Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
                    edtoderid.setText("");
                    edtname.setText("");
                    edtnumber.setText("");
                    edtaddress.setText("");
                    edtprice.setText("");
                    edtdriverid.setText("");
                    edtdrivername.setText("");
                    edtcomplete.setText("");

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


    }


    private void init(){
        edtoderid = (EditText) findViewById(R.id.edtoderid);
        edtname = (EditText) findViewById(R.id.edtname);
        edtnumber = (EditText) findViewById(R.id.edtnumber);
        edtaddress = (EditText) findViewById(R.id.edtaddress);
        edtprice = (EditText) findViewById(R.id.edtprice);
        edtdriverid = (EditText) findViewById(R.id.edtdriverid);
        edtdrivername = (EditText) findViewById(R.id.edtdrivername);
        edtcomplete = (EditText) findViewById(R.id.edtcomplete);
        btnadd = (Button) findViewById(R.id.btnadd);
    }
}