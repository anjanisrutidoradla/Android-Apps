package course.examples.Services.KeyService;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;

import course.examples.Services.KeyCommon.KeyGenerator;

public class MovieCentral extends Service {
//	public String[] movieTitles;
//	public String[] movieDirectors;
//	public String[] movieURLs;

	String[] movieTitles, movieDirectors, movieURLs;

	// Implement the Stub for this Object
	private final KeyGenerator.Stub mBinder = new KeyGenerator.Stub() {

		@Override
		public Bundle getAllMoviesInfo() throws RemoteException {
			movieTitles = getResources().getStringArray(R.array.movie_titles);
			movieDirectors = getResources().getStringArray(R.array.movie_directors);
			movieURLs = getResources().getStringArray(R.array.movie_links);

			synchronized (this) {
				Bundle bundle = new Bundle();
				bundle.putStringArray("Titles", movieTitles);
				bundle.putStringArray("Directors", movieDirectors);
				bundle.putStringArray("Movie URL", movieURLs);
				return bundle;
			}
		}

		public Bundle getOneMovie(int index) throws RemoteException {
			movieTitles = getResources().getStringArray(R.array.movie_titles);
			movieDirectors = getResources().getStringArray(R.array.movie_directors);
			movieURLs = getResources().getStringArray(R.array.movie_links);

			synchronized (this) {
				Bundle bundle = new Bundle();
				bundle.putString("Titles", movieTitles[index]);
				bundle.putString("Directors", movieDirectors[index]);
				bundle.putString("Movie URL", movieURLs[index]);
				return bundle;
			}
		}
		public String getURL(int index) throws RemoteException{
			movieURLs = getResources().getStringArray(R.array.movie_links);
			synchronized (this){
				Bundle bundle = new Bundle();
				return movieURLs[index];
			}
		}
	};




	// Return the Stub defined above
	@Override
	public IBinder onBind(Intent intent) {

			return mBinder;
	}
}
