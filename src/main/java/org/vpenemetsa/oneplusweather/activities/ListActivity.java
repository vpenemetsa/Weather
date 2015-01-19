package org.vpenemetsa.oneplusweather.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import org.vpenemetsa.oneplusweather.R;
import org.vpenemetsa.oneplusweather.SavedLocations;
import org.vpenemetsa.oneplusweather.adapters.LocationAdapter;
import org.vpenemetsa.oneplusweather.api.ApiManager;
import org.vpenemetsa.oneplusweather.responses.WeatherResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ListActivity extends ActionBarActivity implements
        ConnectionCallbacks, OnConnectionFailedListener {

    private RecyclerView mRecyclerView;
    private LocationAdapter mAdapter;
    private GoogleApiClient mGoogleApiClient;
    private ApiManager mApiManager = new ApiManager();

    private SavedLocations mSavedLocations;

    private android.location.Location mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.location_list_view);
        mRecyclerView.setHasFixedSize(true);

        mSavedLocations = SavedLocations.getInstance(getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new LocationAdapter(mSavedLocations.getStoredWeatherResponses());
        mRecyclerView.setAdapter(mAdapter);

        buildGoogleApiClient();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        String lat = String.valueOf(mCurrentLocation.getLatitude());
        String lon = String.valueOf(mCurrentLocation.getLongitude());

        mApiManager.getCurrentWeather(lat, lon, new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                Log.i("(***********************", "Response received");
                setCurrentLocationWeather(weatherResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("(***********************", error.toString());
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("********************", "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e("*************************", "Connection failed");
    }

    private void setCurrentLocationWeather(WeatherResponse weatherResponse) {

        Log.d("******************", weatherResponse.getName());

//        locations.add(location);
        if (mAdapter != null) {
            mAdapter.add(weatherResponse);
        }
    }
}
