package com.example.project_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Getting the intent started
        Intent intent = getIntent();
        //Setting it as the image for this ImageView
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(intent.getIntExtra(MainActivity.EXTRA_RES_ID, 0));
        setContentView(imageView);
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Creating an intent for the Facts Activity
                Intent facts=new Intent(ImageViewActivity.this, ActivityFacts.class);
                facts.putExtra(MainActivity.EXTRA_POS, intent.getIntExtra(MainActivity.EXTRA_POS,0));
                startActivity(facts);
            }
        });
    }
}

