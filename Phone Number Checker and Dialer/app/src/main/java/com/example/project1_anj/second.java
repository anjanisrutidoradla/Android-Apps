package com.example.project1_anj;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class second extends AppCompatActivity {
    //Validation for the phone number
    public static boolean checkPhoneNumber(String ph_no)
    {
        String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{3})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ph_no);

        if (matcher.matches()){
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = new Intent(); // Implicit Intent
        EditText editText = (EditText) findViewById(R.id.myNumber);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            //Action when the editor is selected.
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String ph_no = editText.getText().toString().trim();
                if(checkPhoneNumber(ph_no)) {
                    //transmitting the valid result to the parent activity
                    i.putExtra("Phone Number", ph_no);
                    setResult(RESULT_OK, i);
                }
                else{
                    //transmitting the invalid result to the parent activity
                    i.putExtra("Phone Number", ph_no);
                    setResult(RESULT_CANCELED, i);
                }

                finish();
                return true;
            }
        });
    }

    //When pressed back button, this method is called.
    @Override
    public void onBackPressed() {

        Intent i = new Intent();
        i.putExtra("Phone Number","");
        setResult(RESULT_CANCELED,i);
        super.onBackPressed();
    }
}