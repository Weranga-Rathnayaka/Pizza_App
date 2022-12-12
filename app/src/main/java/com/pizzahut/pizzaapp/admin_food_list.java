package com.pizzahut.pizzaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class admin_food_list extends AppCompatActivity {


    GridView gridView;
    ArrayList<Food> list;
    FoodListAdapter adapter = null;

    public static sqlhelper sqliteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_food_list);



        gridView = findViewById(R.id.grideview);
        list = new ArrayList<>();
        adapter = new FoodListAdapter(this, R.layout.admin_food_item, list);
        gridView.setAdapter(adapter);

        //get all data from sqlite

        Cursor cursor = MainActivity.sqliteHelper.getdata("SELECT * FROM FOOD");
        list.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String type = cursor.getString(3);
            String description = cursor.getString(4);
            byte[] image = cursor.getBlob(5);

            list.add(new Food(id, name, price, type, description, image));

        }
        adapter.notifyDataSetChanged();




        //Admin panel update, delete side

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(admin_food_list.this);

                dialog.setTitle("Choose an action");

                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        if(item == 0){
                            //update
                            Cursor c = MainActivity.sqliteHelper.getdata("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //show the dialog update here
                            showDialogToUpdate(admin_food_list.this, arrID.get(position));

                        }else{
                            //delete
                            Cursor c = MainActivity.sqliteHelper.getdata("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogToDelete(arrID.get(position));
                        }

                    }
                });

                dialog.show();
                return true;
            }
        });
    }


    //create a dialog to confirm Update

    ImageView imageView;

    private void showDialogToUpdate(Activity activity,final int position){
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_food_item);
        dialog.setTitle("Update");

        imageView = dialog.findViewById(R.id.update_image);
        final EditText edtName = dialog.findViewById(R.id.update_food_name);
        final EditText edtPrice = dialog.findViewById(R.id.update_food_price);
        final EditText edtType = dialog.findViewById(R.id.update_food_type);
        final EditText edtDescription = dialog.findViewById(R.id.update_description);
        Button update_btn = dialog.findViewById(R.id.update_btn);


        //set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);

        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.8);

        dialog.getWindow().setLayout(width,height);
        dialog.show();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request photo in librarty
                ActivityCompat.requestPermissions
                        (admin_food_list.this,
                                new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                888   );
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = edtName.getText().toString();
                String s2 = edtPrice.getText().toString();
                String s3 = edtType.getText().toString();
                String s4 = edtDescription.getText().toString();

                if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Empty", Toast.LENGTH_SHORT).show();

                }else {
                    if (s3.equals("Large")||s3.equals("Medium")||s3.equals("Small")) {

                        try {

                            MainActivity.sqliteHelper.updateData(
                                    edtName.getText().toString().trim(),
                                    edtPrice.getText().toString().trim(),
                                    edtType.getText().toString().trim(),
                                    edtDescription.getText().toString().trim(),
                                    add_product.imageViewToByte(imageView),
                                    position
                            );
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Update Successfully...", Toast.LENGTH_SHORT).show();


                        } catch (Exception e) {
                            Log.e("Update error", e.getMessage());
                        }

                        updateFoodList();
                    }else {
                        Toast.makeText(getApplicationContext(), "Food Type Should Be Large, Medium or Small", Toast.LENGTH_SHORT).show();
                        edtType.setText("");
                    }
                }
            }
        });

    }



    private void showDialogToDelete(final int idFood){

        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(admin_food_list.this);

        dialogDelete.setTitle("Warnning!!");
        dialogDelete.setMessage("Are you sure you want to delete ?");

        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {

                    MainActivity.sqliteHelper.deleteData(idFood);
                    Toast.makeText(getApplicationContext(), "Delete Successfully...", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("Error",e.getMessage());
                }

                updateFoodList();

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
    private void updateFoodList(){

        //get all data from sqlite

        Cursor cursor = MainActivity.sqliteHelper.getdata("SELECT * FROM FOOD");
        list.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String type = cursor.getString(3);
            String description = cursor.getString(4);
            byte[] image = cursor.getBlob(5);

            list.add(new Food(id, name, price, type, description, image));

        }
        adapter.notifyDataSetChanged();

    }



    //inserting the image


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,888);
            }
            else{

                Toast.makeText(getApplicationContext(),"you do not have permission to access location", Toast.LENGTH_SHORT).show();
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 888 && resultCode == RESULT_OK && data != null){

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }






}
