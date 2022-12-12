package com.pizzahut.pizzaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class user_Home_Page extends AppCompatActivity {


    GridView gridView;
    ArrayList<Food> list;
    useFoodListAdapter adapter = null;

    SearchView mySearchView;
    ImageView imageView;
    TextView t;

    public static sqlhelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__home__page);


        gridView = findViewById(R.id.grideview12);
        list = new ArrayList<>();
        adapter = new useFoodListAdapter(this,R.layout.user_food_item,list);
        gridView.setAdapter(adapter);

        //get the user details to the user profile

        String username = getIntent().getStringExtra("username1");


        /*

        imageView = findViewById(R.id.profile_btn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(user_Home_Page.this, userProfile.class);
                intent.putExtra("username1",username);
                startActivity(intent);

            }
        });


         */


        //get all data from sqlite

        Cursor cursor = startup_screen.sqliteHelper.getdata("SELECT * FROM FOOD");
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







    //menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        };



        menu.findItem(R.id.search).setOnActionExpandListener(onActionExpandListener);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setQueryHint("search here......");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:

                String username = getIntent().getStringExtra("username1");

               Intent intent = new Intent(user_Home_Page.this, userProfile.class);
                intent.putExtra("username1",username);
               startActivity(intent);

                return true;

            case R.id.item2:
           //     Intent intent2 = new Intent(user_Home_Page.this, MainActivity2.class);
            //    startActivity(intent2);

            case R.id.item3:
                Intent intent3 = new Intent(user_Home_Page.this, startup_screen.class);
                startActivity(intent3);
                return true;
        }
        return true;
    }

}