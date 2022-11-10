package course.examples.Services.KeyClient;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends Activity {
    private TextView textView2;
    private TextView textView4;
    private TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textView2= findViewById(R.id.textView2);
        textView4= findViewById(R.id.textView4);
        textView6= findViewById(R.id.textView6);

        String movieTitle;
        String movieDirector;
        String movieUrl;
        movieTitle = (String) getIntent().getExtras().getString("Titles");
        movieDirector = (String) getIntent().getExtras().getString("Directors");
        movieUrl = (String) getIntent().getExtras().getString("Movie URL");

        textView2.setText(movieTitle);
        textView4.setText(movieDirector);
        textView6.setText(movieUrl);




    }
}