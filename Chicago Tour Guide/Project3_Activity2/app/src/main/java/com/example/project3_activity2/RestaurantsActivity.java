package com.example.project3_activity2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class RestaurantsActivity extends AppCompatActivity {
    //Initializing all the required objects for running the restaurant application.
    public static String[] rTitleArray;
    public static String[] rQuoteArray;
    private RestaurantsQuoteFragment rQuoteFragment;
    private RestaurantsTitlesFragment rTitleFragment;
    FragmentManager rFragmentManager;
    private FrameLayout rTitleFrameLayout, rQuotesFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "RestaurantsActivity";
    private ListViewModel mModel ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        //Getting the resources for the application
        rTitleArray = getResources().getStringArray(R.array.Titles_R);
        rQuoteArray = getResources().getStringArray(R.array.Quotes_R);
        rTitleFrameLayout = (FrameLayout) findViewById(R.id.title_fragment_container);
        rQuotesFrameLayout = (FrameLayout) findViewById(R.id.quote_fragment_container);
        //Toolbar working
        Toolbar myToolbar = findViewById(R.id.my_toolbar) ;
        setSupportActionBar(myToolbar);

        rFragmentManager = getSupportFragmentManager();
        //Retaining the fragment
        rQuoteFragment = (RestaurantsQuoteFragment) rFragmentManager.findFragmentByTag("data");
        rTitleFragment = (RestaurantsTitlesFragment) rFragmentManager.findFragmentByTag("data_t");

        if(rQuoteFragment == null){
            rQuoteFragment = new RestaurantsQuoteFragment();

        }
        if(rTitleFragment == null){
            rTitleFragment = new RestaurantsTitlesFragment();
        }

        final FragmentTransaction fragmentTransaction = rFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.title_fragment_container, rTitleFragment, "data_t");
        //fragmentTransaction.replace(R.id.quote_fragment_container, new RestaurantsQuoteFragment());

        Log.i("Anjani","1");
        fragmentTransaction.commit();
        Log.i("Anjani","2");

        rFragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged(){setLayout(); }
                });

        Log.i("Anjani","3");
        mModel = new ViewModelProvider(this).get(ListViewModel.class) ;
        mModel.getSelectedItem().observe(this, item -> {
            if (!rQuoteFragment.isAdded()) {
                FragmentTransaction fragmentTransaction2 = rFragmentManager.beginTransaction() ;
                fragmentTransaction2.add(R.id.quote_fragment_container,
                        rQuoteFragment, "data");
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                rFragmentManager.executePendingTransactions();
            }
        });
        Log.i("Anjani","4");
        setLayout() ;
    }
    private void setLayout() {
        Log.i("Anjani","5");
        // Determine whether the QuoteFragment has been added
        if (!rQuoteFragment.isAdded()) {
            Log.i("Anjani","6");
            // Make the TitleFragment occupy the entire layout
            rTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            rQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
            Log.i("Anjani","7");
        } else {

            // Make the TitleLayout take 1/3 of the layout's width
            rTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));

            // Make the QuoteLayout take 2/3's of the layout's width
            rQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_a:
                //Toast.makeText(getApplicationContext(), "Message Received", Toast.LENGTH_SHORT).show();
                Intent attractions= new Intent(RestaurantsActivity.this,AttractionsActivity.class);
                startActivity(attractions);
                return true;
            case R.id.menu_r:
                //Toast.makeText(getApplicationContext(), "Message Received", Toast.LENGTH_SHORT).show();
                Intent restaurants= new Intent(RestaurantsActivity.this,RestaurantsActivity.class);
                startActivity(restaurants);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}






