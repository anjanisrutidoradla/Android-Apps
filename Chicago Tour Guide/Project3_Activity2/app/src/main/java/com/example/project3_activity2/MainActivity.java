package com.example.project3_activity2;
//References - Android Documentation and Android app examples.
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar) ;
        setSupportActionBar(myToolbar);
        //Broadcast receiver object for attractions
        IntentFilter intentFilter_a = new IntentFilter("com.example.project3_message");
        MyReceiver1 objReceiver1 = new MyReceiver1();
        registerReceiver(objReceiver1,intentFilter_a);
        //Broadcast receiver object for restaurants
        IntentFilter intentFilter_r = new IntentFilter("com.example.project3_message1");
        MyReceiver2 objReceiver2 = new MyReceiver2();
        registerReceiver(objReceiver2,intentFilter_r);


    }


    //option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }
    //displaying options on selection items
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_a:
                //Toast.makeText(getApplicationContext(), "Message Received", Toast.LENGTH_SHORT).show();
                Intent attractions= new Intent(MainActivity.this,AttractionsActivity.class);
                startActivity(attractions);
                return true;
            case R.id.menu_r:
                //Toast.makeText(getApplicationContext(), "Message Received", Toast.LENGTH_SHORT).show();
                Intent restaurants= new Intent(MainActivity.this,RestaurantsActivity.class);
                startActivity(restaurants);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}