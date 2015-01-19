package org.vpenemetsa.oneplusweather.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.vpenemetsa.oneplusweather.R;
import org.vpenemetsa.oneplusweather.SavedLocations;
import org.vpenemetsa.oneplusweather.adapters.LocationAdapter;
import org.vpenemetsa.oneplusweather.api.ApiManager;
import org.vpenemetsa.oneplusweather.external.SwipeableRecyclerViewTouchListener;
import org.vpenemetsa.oneplusweather.responses.WeatherResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ListActivity extends ActionBarActivity implements
        ConnectionCallbacks, OnConnectionFailedListener {

    private static final String TAG = ListActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private LocationAdapter mAdapter;
    private GoogleApiClient mGoogleApiClient;
    private ApiManager mApiManager = new ApiManager();
    private Context mContext;

    private SavedLocations mSavedLocations;

    private android.location.Location mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.location_list_view);
        mRecyclerView.setHasFixedSize(true);

        mSavedLocations = SavedLocations.getInstance(getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);


        mAdapter = new LocationAdapter(getApplicationContext(),
                mSavedLocations.getAllWeatherResponses(), mSavedLocations);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton addLocationButton =
                (FloatingActionButton) findViewById(R.id.add_location);
        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Add Location");
                builder.setMessage("Type location name to add to your list.");

                final EditText editText = new EditText(mContext);
                builder.setView(editText);

                builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String location = editText.getText().toString();
                        if (!TextUtils.isEmpty(location)) {
                            addLocation(location);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView, new SwipeableRecyclerViewTouchListener.SwipeListener() {
                    @Override
                    public boolean canSwipe(int position) {
                        if (position == 0) {
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            mAdapter.remove(position);
                        }
                    }

                    @Override
                    public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            mAdapter.remove(position);
                        }
                    }
                });
        mRecyclerView.addOnItemTouchListener(swipeTouchListener);

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
        if (weatherResponse.getCod() == HttpStatus.SC_OK) {
            mSavedLocations.saveCurrentLocation(weatherResponse);
            if (mAdapter != null) {
                mAdapter.replace(weatherResponse, 0);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Location not found.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void addLocation(String location) {
        mApiManager.getCurrentWeather(location, new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                if (weatherResponse.getCod() == HttpStatus.SC_OK) {
                    mSavedLocations.saveLocation(weatherResponse);
                    if (mAdapter != null) {
                        mAdapter.add(weatherResponse);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Location not found.",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString());
            }
        });
    }
}
