package com.example.project_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
public class ActivityFacts extends Activity {
    int position;
    TextView name,lifespan, weight, diet, habitat, status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Starting the intent for the ActivityFacts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facts);
        Intent intent = getIntent();
        position = intent.getIntExtra(MainActivity.EXTRA_POS, 0);
        //Get the ID through textviews
        name = findViewById(R.id.Name);
        lifespan = findViewById(R.id.Lifespan);
        weight = findViewById(R.id.Weight);
        diet = findViewById(R.id.Diet);
        habitat = findViewById(R.id.Habitat);
        status = findViewById(R.id.Status);
        //Setting the appropriate text for all the facts
        name.setText(String.format("Name: %s", MainActivity.Name.get(position)));
        lifespan.setText(String.format("Lifespan: %s years", MainActivity.Lifespan.get(position)));
        weight.setText(String.format("Weight: %s kgs", MainActivity.Weight.get(position)));
        diet.setText(String.format("Diet: %s", MainActivity.Diet.get(position)));
        habitat.setText(String.format("Habitat: %s", MainActivity.Habitat.get(position)));
        status.setText(String.format("Status: %s", MainActivity.Status.get(position)));

}}