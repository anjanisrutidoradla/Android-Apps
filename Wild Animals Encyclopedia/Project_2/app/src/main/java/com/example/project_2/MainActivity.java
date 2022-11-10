package com.example.project_2;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    protected static final String EXTRA_RES_ID = "RES";
    protected static final String EXTRA_POS = "POS";
//Creating ArrayLists for all the required attributes to be displayed
    protected static final ArrayList<Integer> mThumbIdsAnimals = new ArrayList<Integer>(
            Arrays.asList(R.drawable.cat,R.drawable.d, R.drawable.deer, R.drawable.elephant,
                    R.drawable.monkey, R.drawable.crocodile, R.drawable.lion, R.drawable.leopard));
    //ArrayList of the wiki links
    protected static final ArrayList<String> Wiki = new ArrayList<String>(
            Arrays.asList("https://en.wikipedia.org/wiki/Cat", "https://en.wikipedia.org/wiki/Dog",
                    "https://en.wikipedia.org/wiki/Deer", "https://en.wikipedia.org/wiki/ELephant",
                    "https://en.wikipedia.org/wiki/Monkey", "https://en.wikipedia.org/wiki/Crocodile",
                    "https://en.wikipedia.org/wiki/Lion", "https://en.wikipedia.org/wiki/Leopard"));
    //ArrayList of the names
    protected static final ArrayList<String> Name = new ArrayList<String>(
            Arrays.asList("Cat", "Dog", "Deer", "Elephant",
                    "Monkey", "Crocodile", "Lion", "Leopard"));

    protected static final ArrayList<String> Weight = new ArrayList<String>(
            Arrays.asList("4 - 6", "30 - 70", "60 - 220", "6000 - 13000",
                    "10 - 40", "450 - 1200", "280 - 420", "250 - 500"));

    protected static final ArrayList<String> Diet = new ArrayList<String>(
            Arrays.asList("Carnivorous", "Carnivorous", "Herbivorous", "Herbivorous",
                    "Omnivorous", "Carnivorous", "Carnivorous", "Carnivorous"));

    protected static final ArrayList<String> Habitat = new ArrayList<String>(
            Arrays.asList("Cities, Forests, Grasslands", "Cities, Forests, Grasslands",
                    " Wetlands, Deciduous forests, Grasslands, Rain forests",
                    "Forests, Savannahs, Deserts", "Forests,Grasslands","Mangrove Swamps, Estuaries", "Grasslands, Savannahs, Woodlands"
                    ,"Woodlands, Forests, Grasslands, Mountains"));

    protected static final ArrayList<String> Status = new ArrayList<String>(
            Arrays.asList("Least Concern", "Least Concern", "Least Concern", "Endangered",
                    "Least Concern", "Vulnerable", "Vulnerable", "Vulnerable"));

    protected static final ArrayList<String> Lifespan = new ArrayList<String>(
            Arrays.asList("12 - 18", "10 - 13", "6 - 14", "48 - 70",
                    "25 - 45", "70 - 100", "10 - 15", "12 - 17"));


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        //Creating a new Image Adapter
        gridview.setAdapter(new ImageAdapter(this, mThumbIdsAnimals, Name));
        registerForContextMenu(gridview);
        //Setting an onItemClickListener
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Creating an intent for the ImageViewActivity
                Intent intent = new Intent(MainActivity.this, ImageViewActivity.class);
                intent.putExtra(EXTRA_RES_ID, (int) id);
                intent.putExtra(EXTRA_POS, position);
                startActivity(intent);
            }
        });
    }

    @Override
    //Creating onCreateContextMenu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.gridview) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_item, menu);
        }
    }

    @Override

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        long id = info.id;
        int position = info.position;
        //A place to choose the option on long click.
        switch(item.getItemId()) {
            case R.id.seePicture:
                Intent intent = new Intent(MainActivity.this, ImageViewActivity.class);
                intent.putExtra(EXTRA_RES_ID, (int) id);
                intent.putExtra(EXTRA_POS, position);
                startActivity(intent);
                return true;

            case R.id.seeFacts:
                Intent facts = new Intent(MainActivity.this, ActivityFacts.class);
                facts.putExtra(MainActivity.EXTRA_POS, position);
                startActivity(facts);
                return true;

            case R.id.seeWiki:
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(Wiki.get(position)));
                startActivity(viewIntent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}