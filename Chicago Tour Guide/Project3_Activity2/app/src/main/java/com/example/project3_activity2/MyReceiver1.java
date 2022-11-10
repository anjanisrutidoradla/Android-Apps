package com.example.project3_activity2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //Receiving broadcast for attractions.
        Toast.makeText(context, "Message Received", Toast.LENGTH_SHORT).show();
        Intent attractions= new Intent(context,AttractionsActivity.class);
        context.startActivity(attractions);

    }
}