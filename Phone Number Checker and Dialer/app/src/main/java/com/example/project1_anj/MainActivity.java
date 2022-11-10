package com.example.project1_anj;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Initialising all the variables to be used in the app.
    protected Button button1;
    protected Button button2;
    protected String ph_no;
    protected int result_code;
    protected String res = "False";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.Button_1);
        button2 = findViewById(R.id.Button_2);

        button1.setOnClickListener(button1Listener);
        button2.setOnClickListener(button2Listener);

    }
    //Listeners for both the buttons.
    private final View.OnClickListener button1Listener = v -> activity2();
    private final View.OnClickListener button2Listener = v -> addnumber(ph_no);

    public void addnumber(String name){
        //Setting all the result code and the actions.
        if(result_code == -1){
            Uri u = Uri.parse("tel:" + ph_no);
            Intent addAphNumber = new Intent(Intent.ACTION_DIAL,u);
            startActivity(addAphNumber);
        }
        else if (result_code == 0  && ph_no != null && ph_no.length() >0){
            Context context = getApplicationContext();
            CharSequence text = "Wrong Number! The entered phone number is: "+ph_no;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if (result_code == 0){
            Context context = getApplicationContext();
            CharSequence text = "No number was entered. Please enter a number";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }



    }
    //Checking and setting result variable.
    protected void onActivityResult(int code, int result_code, Intent i) {
        super.onActivityResult(code, result_code, i);
        this.result_code = result_code;
        if (code == 2) {
            ;
            if (i != null) {
                Bundle numberData;
                numberData = i.getExtras();
                ph_no = (String) numberData.get("Phone Number");
                res = (String) numberData.get("Result");
            } else {
                ph_no = "";
                res = "False";

            }
        }
    }
    //Going to the next activity ( Validation)
    private void activity2(){
        Intent i = new Intent(MainActivity.this,second.class);
        startActivityForResult(i,2);
    }
    //Saving all the values in key-value pairs using onSaveInstanceState.
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString("Phone Number",ph_no);
        savedInstanceState.putString("Result",res);
        savedInstanceState.putInt("result_code",result_code);
        super.onSaveInstanceState(savedInstanceState);
    }
    //Restoring all the saved instances.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            ph_no = savedInstanceState.getString("ph_no");
            res = savedInstanceState.getString("Result");
            result_code = savedInstanceState.getInt("result_code");

        }
    }


}



