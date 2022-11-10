package course.examples.Services.KeyClient;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ListMovies extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        String[] movie_titles;
        String[] movie_directors;
        String[] movie_url;


        
        movie_titles = (String[]) getIntent().getExtras().getSerializable("Titles");
        movie_directors = (String[]) getIntent().getExtras().getSerializable("Directors");
        movie_url = (String[]) getIntent().getExtras().getSerializable("Movie URL");

        String[] merged_info = new String[movie_titles.length];;
        for (int i=0; i< movie_titles.length; i++) {
            merged_info[i] = movie_titles[i]+" - "+movie_directors[i];
        }

        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_list_movies, merged_info));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    Intent intent = new Intent(ListMovies.this,MainActivity2.class);
			    intent.putExtra("URL",movie_url[position]);
                startActivity(intent);


           // }


			}
		});
    }
}
    // Method onListItemClick() is an alternative to lv.setOnItemClickListener() in ListActivity
    // instances.  To switch methods, comment this method and uncomment the code above.  The
    // behavior of the app should not change.
//    public void onListItemClick(ListView parent, View view,
//                                int position, long id) {
//
//        // Display a Toast message indicting the selected item
//        Toast.makeText(getApplicationContext(),
//                ((TextView) view).getText(), Toast.LENGTH_LONG).show();
//    }
//}