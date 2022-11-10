package com.example.project3_activity2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //Receiving broadcast for restaurants.
        Toast.makeText(context, "Message Received", Toast.LENGTH_SHORT).show();
        Intent restaurants= new Intent(context,RestaurantsActivity.class);
        context.startActivity(restaurants);
    }
}