package com.example.project3_activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//Reference taken from Android documentation, android app examples and a little from Project 1.

public class MainActivity extends AppCompatActivity {
    //Initialising buttons
    protected Button button1;
    protected Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.Button_1);
        button2 = findViewById(R.id.Button_2);

        button1.setOnClickListener(button1Listener);
        button2.setOnClickListener(button2Listener);
    }
    //Listeners for the buttons.
    private final View.OnClickListener button1Listener = v -> show_m1();
    private final View.OnClickListener button2Listener = v -> show_m2();

    public void show_m1(){
        //Sending broadcast to view attractions.
        Context context = getApplicationContext();
        CharSequence text = "Attractions";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent intent_attractions = new Intent();
        intent_attractions.setAction("com.example.project3_message");
        intent_attractions.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent_attractions);



    }
    public void show_m2(){
        //Sending broadcast to view restaurants.
        Context context = getApplicationContext();
        CharSequence text = "Restaurants";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent intent_restaurants = new Intent();
        intent_restaurants.setAction("com.example.project3_message1");
        intent_restaurants.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent_restaurants);

    }




}






