package course.examples.Services.KeyClient;
//References : Stack Overflow
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import course.examples.Services.KeyCommon.KeyGenerator;

public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity";
	protected static final int PERMISSION_REQUEST = 0;

	private Button bind;
	private Button unbind;
	private Button alldetails;
	private TextView status;
	private TextView view_one_movie;
	private TextView view_details;
	private Spinner all_movie_details;
	private Spinner one_movie;

	private KeyGenerator mKeyGeneratorService;
	private boolean mIsBound = false;


	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		bind = (Button) findViewById(R.id.bind);
		unbind = (Button) findViewById(R.id.unbind);
		alldetails = (Button) findViewById(R.id.allmovies);
		all_movie_details = (Spinner) findViewById(R.id.details);
		one_movie = (Spinner) findViewById(R.id.movie);
		status = (TextView) findViewById(R.id.status);
		status.setText("The service is not binded");
		view_one_movie = findViewById(R.id.onemovieview);
		view_details = findViewById(R.id.viewMovies);

		alldetails.setVisibility(View.INVISIBLE);
		all_movie_details.setVisibility(View.INVISIBLE);
		one_movie.setVisibility(View.INVISIBLE);
		view_one_movie.setVisibility(View.INVISIBLE);
		view_details.setVisibility(View.INVISIBLE);

		bind.setOnClickListener(v -> {
			if (checkSelfPermission("course.examples.Services.KeyService.GEN_ID")
					!= PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(this,
						new String[]{"course.examples.Services.KeyService.GEN_ID"},
						PERMISSION_REQUEST);
			}
			else {
				checkBindingAndBind();
			}

		});

		unbind.setOnClickListener(v -> {
			try {
				unbind();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});



		alldetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				try{
					if(mIsBound){
						Bundle bundle = mKeyGeneratorService.getAllMoviesInfo();
						String[] movie_titles;
						String[] movie_directors;
						String[] movie_url;
						movie_titles = bundle.getStringArray("Titles");
						movie_directors = bundle.getStringArray("Directors");
						movie_url = bundle.getStringArray("Movie URL");


						Intent intent = new Intent(MainActivity.this,ListMovies.class);
						intent.putExtras(bundle);
						startActivity(intent);

					}
				}
				catch (RemoteException e){
					e.printStackTrace();

				}
			}
		});

		// For Spinner
			String[] movie_index= {"Movie Number","1","2","3","4","5"};
			ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,movie_index);
			aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			one_movie.setEnabled(true);
			one_movie.setClickable(true);
			one_movie.setAdapter(aa);

		all_movie_details.setEnabled(true);
		all_movie_details.setClickable(true);
		all_movie_details.setAdapter(aa);





		//Setting the ArrayAdapter data on the Spinner


		one_movie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				if(position!=0){
					try {
						String movie_url = mKeyGeneratorService.getURL(position-1);
						Intent intent=new Intent(MainActivity.this,MainActivity2.class);
						intent.putExtra("URL",movie_url);
						one_movie.setSelection(0);
						startActivity(intent);
					} catch (RemoteException e) {
						e.printStackTrace();
					}


				}

			}
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		all_movie_details.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				if(position!=0){
					try {
						Bundle specific_movie = mKeyGeneratorService.getOneMovie(position-1);
						Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
						intent.putExtras(specific_movie);
						all_movie_details.setSelection(0);
						startActivity(intent);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

			}
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

	}

	private void unbind() throws RemoteException {
		if(mIsBound){
			status.setText("The service is not binded");
			//mIsBound=false;

			if (mIsBound) {
				unbindService(this.mConnection);
				mIsBound=false;
			}

			if(!mIsBound){
				alldetails.setVisibility(View.INVISIBLE);
				all_movie_details.setVisibility(View.INVISIBLE);
				one_movie.setVisibility(View.INVISIBLE);

				view_one_movie.setVisibility(View.INVISIBLE);
				view_details.setVisibility(View.INVISIBLE);


			}

		}
	}




	// Bind to KeyGenerator Service
	@Override
	protected void onStart() {
		super.onStart();

//		if (checkSelfPermission("course.examples.Services.KeyService.GEN_ID")
//			!= PackageManager.PERMISSION_GRANTED) {
//			ActivityCompat.requestPermissions(this,
//					new String[]{"course.examples.Services.KeyService.GEN_ID"},
//					PERMISSION_REQUEST);
//		}
//		else {
//			checkBindingAndBind();
//		}
	}

	protected void checkBindingAndBind() {
		if (!mIsBound) {

			boolean b = false;
			Intent i = new Intent(KeyGenerator.class.getName());

			// UB:  Stoooopid Android API-21 no longer supports implicit intents
			// to bind to a service #@%^!@..&**!@
			// Must make intent explicit or lower target API level to 20.
			ResolveInfo info = getPackageManager().resolveService(i, 0);
			i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));

			b = bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);
			if (b) {
				Log.i(TAG, "Anjani says bindService() succeeded!");
			} else {
				Log.i(TAG, "Anjani says bindService() failed!");
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case PERMISSION_REQUEST: {

				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

					// Permission granted, go ahead and display map

					checkBindingAndBind();
				}
				else {
					Toast.makeText(this, "BUMMER: No Permission :-(", Toast.LENGTH_LONG).show() ;
				}
			}
			default: {
				// do nothing
			}
		}
	}
	// Unbind from KeyGenerator Service
	@Override
	protected void onStop() {

		super.onStop();


	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mIsBound) {
			unbindService(this.mConnection);
			mIsBound=false;
		}

	}

	private final ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder iservice) {

			mKeyGeneratorService = KeyGenerator.Stub.asInterface(iservice);
			mIsBound = true;
			status.setText("The service is binded");
			alldetails.setVisibility(View.VISIBLE);
			all_movie_details.setVisibility(View.VISIBLE);
			one_movie.setVisibility(View.VISIBLE);
			view_one_movie.setVisibility(View.VISIBLE);
			view_details.setVisibility(View.VISIBLE);


		}

		public void onServiceDisconnected(ComponentName className) {

			mKeyGeneratorService = null;
			mIsBound = false;
			status.setText("The service is not binded");

		}
	};

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}
