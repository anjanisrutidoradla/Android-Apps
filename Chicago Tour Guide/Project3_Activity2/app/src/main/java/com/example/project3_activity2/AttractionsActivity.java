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

public class AttractionsActivity extends AppCompatActivity {
    //Initialising all the objects
    public static String[] mTitleArray;
    public static String[] mQuoteArray;
    private AttractionsQuoteFragment mQuoteFragment;
    private AttractionsTitlesFragment mTitlesFragment;
    FragmentManager mFragmentManager;
    private FrameLayout mTitleFrameLayout, mQuotesFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "AttractionsActivity";
    private ListViewModel mModel ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);
        //Accessing the data for displaying the attractions
        mTitleArray = getResources().getStringArray(R.array.Titles);
        mQuoteArray = getResources().getStringArray(R.array.Quotes);
        mTitleFrameLayout = (FrameLayout) findViewById(R.id.title_fragment_container);
        mQuotesFrameLayout = (FrameLayout) findViewById(R.id.quote_fragment_container);
        //Accessing Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar) ;
        setSupportActionBar(myToolbar);

        mFragmentManager = getSupportFragmentManager();
        mQuoteFragment = (AttractionsQuoteFragment) mFragmentManager.findFragmentByTag("data");
        mTitlesFragment = (AttractionsTitlesFragment) mFragmentManager.findFragmentByTag("data_t");

        //Retrieving the fragments.
        if(mQuoteFragment == null){
            mQuoteFragment = new AttractionsQuoteFragment();

        }
        if(mTitlesFragment == null){
            mTitlesFragment = new AttractionsTitlesFragment();
        }

        final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.title_fragment_container,mTitlesFragment,"data_t" );
        Log.i("Anjani","1");
        fragmentTransaction.commit();
        Log.i("Anjani","2");

        mFragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged(){setLayout(); }
                });

        Log.i("Anjani","3");
        mModel = new ViewModelProvider(this).get(ListViewModel.class) ;
        mModel.getSelectedItem().observe(this, item -> {
            if (!mQuoteFragment.isAdded()) {
                FragmentTransaction fragmentTransaction2 = mFragmentManager.beginTransaction() ;
                fragmentTransaction2.add(R.id.quote_fragment_container,
                        mQuoteFragment, "data");
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                mFragmentManager.executePendingTransactions();
            }
        });
        Log.i("Anjani","4");
        setLayout() ;
    }
    private void setLayout() {
        Log.i("Anjani","5");
        // Determine whether the QuoteFragment has been added
        if (!mQuoteFragment.isAdded()) {
            Log.i("Anjani","6");
            // Make the TitleFragment occupy the entire layout
            mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
            Log.i("Anjani","7");
        } else {

            // Make the TitleLayout take 1/3 of the layout's width
            mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));

            // Make the QuoteLayout take 2/3's of the layout's width
            mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
        }
    }
    //Displaying the options menu.
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
                Intent attractions= new Intent(AttractionsActivity.this,AttractionsActivity.class);
                startActivity(attractions);
                return true;
            case R.id.menu_r:
                //Toast.makeText(getApplicationContext(), "Message Received", Toast.LENGTH_SHORT).show();
                Intent restaurants= new Intent(AttractionsActivity.this,RestaurantsActivity.class);
                startActivity(restaurants);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}






