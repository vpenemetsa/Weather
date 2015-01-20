package org.vpenemetsa.oneplusweather.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.vpenemetsa.oneplusweather.Constants;
import org.vpenemetsa.oneplusweather.R;
import org.vpenemetsa.oneplusweather.model.AdditionalData;
import org.vpenemetsa.oneplusweather.model.MainData;
import org.vpenemetsa.oneplusweather.responses.WeatherResponse;
import org.vpenemetsa.oneplusweather.utils.ImageUtils;

public class DetailActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    private ArrayList<WeatherResponse> mWeatherResponses;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherResponses = getIntent().getParcelableArrayListExtra(Constants.PARCEL_LOCATIONS);
        mPosition = getIntent().getIntExtra(Constants.LOCATION_POSITION, 0);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                mWeatherResponses);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(mPosition);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<WeatherResponse> weatherResponses = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm,
                                    List<WeatherResponse> weatherResponses) {
            super(fm);
            this.weatherResponses = weatherResponses;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(weatherResponses.get(position));
        }

        @Override
        public int getCount() {
            return weatherResponses.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return weatherResponses.get(position).getName();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private WeatherResponse mWeatherResponse;
        private Picasso mPicasso;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(WeatherResponse weatherResponse) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putParcelable(Constants.PARCEL_LOCATIONS, weatherResponse);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            mPicasso = Picasso.with(getActivity().getApplicationContext());

            mWeatherResponse = getArguments().getParcelable(Constants.PARCEL_LOCATIONS);

            ImageView ivLocation = (ImageView) rootView.findViewById(R.id.location_image);
            TextView tvLocationName = (TextView) rootView.findViewById(R.id.location_name);
            TextView tvDescription = (TextView) rootView.findViewById(R.id.description);
            TextView tvCurrentTemp = (TextView) rootView.findViewById(R.id.current_temperature);
            TextView tvHumidity = (TextView) rootView.findViewById(R.id.humidity);
            TextView tvPressure = (TextView) rootView.findViewById(R.id.pressure);
            TextView tvSunrise = (TextView) rootView.findViewById(R.id.sunrise);
            TextView tvSunset = (TextView) rootView.findViewById(R.id.sunset);
            TextView tvRain = (TextView) rootView.findViewById(R.id.rain);
            TextView tvSnow = (TextView) rootView.findViewById(R.id.snow);

            try {
                tvLocationName.setText(mWeatherResponse.getName() + ", " +
                        mWeatherResponse.getAdditionalData().getCountry());
            } catch (Exception e) {
                if (mWeatherResponse.getName() != null) {
                    tvLocationName.setText(mWeatherResponse.getName());
                } else {
                    tvLocationName.setText("No Data.");
                }
            }

            if (mWeatherResponse.getWeather().get(0) != null &&
                    mWeatherResponse.getWeather().get(0).getDescription() != null) {
                tvDescription.setText(mWeatherResponse.getWeather().get(0).getDescription() + ". "
                        + mWeatherResponse.getWind().getSpeed() + " mph "
                        + getWindDirection(mWeatherResponse.getWind().getDirection()));
            } else {
                tvDescription.setText("No Data.");
            }

            MainData mainData = mWeatherResponse.getMain();
            if (mainData != null) {
                if (mainData.getTemp() != null) {
                    tvCurrentTemp.setText(mainData.getTemp() + " " + (char) 0x00B0 + "F");
                } else {
                    tvCurrentTemp.setText("No Data.");
                }

                tvHumidity.setText(mWeatherResponse.getMain().getHumidity() + "%");
                tvPressure.setText(mWeatherResponse.getMain().getPressure());
            } else {
                tvCurrentTemp.setText("No Data.");
                tvHumidity.setText("No Data.");
                tvPressure.setText("No Data.");
            }

            AdditionalData additionalData = mWeatherResponse.getAdditionalData();
            if (additionalData != null) {
                Date sunrise = new Date(mWeatherResponse.getAdditionalData().getSunrise());
                Date sunset = new Date(mWeatherResponse.getAdditionalData().getSunset());
                SimpleDateFormat format = new SimpleDateFormat("k:m:s");
                tvSunrise.setText(format.format(sunrise));
                tvSunset.setText(format.format(sunset));
            } else {
                tvSunrise.setText("No Data.");
                tvSunset.setText("No Data.");
            }

            if (mWeatherResponse.getRain() != null &&
                    mWeatherResponse.getRain().getThreeHours() != null) {
                tvRain.setText(mWeatherResponse.getRain().getThreeHours() + " in.");
            } else {
                tvRain.setText("No Data.");
            }

            if (mWeatherResponse.getSnow() != null &&
                    mWeatherResponse.getSnow().getThreeHours() != null) {
                tvSnow.setText(mWeatherResponse.getSnow().getThreeHours() + " in.");
            } else {
                tvSnow.setText("No Data.");
            }

            ImageUtils.loadImageResource(mPicasso, ivLocation, mWeatherResponse.getWeather().get(0));

            return rootView;
        }

        private String getWindDirection(String direction) {
            double windDirection = Double.parseDouble(direction);
            if (windDirection >= 0 && windDirection <= 30) {
                return "N";
            } else if (windDirection > 30 && windDirection <= 60) {
                return "NE";
            } else if (windDirection > 60 && windDirection <= 120) {
                return "E";
            } else if (windDirection > 120 && windDirection <= 150) {
                return "SE";
            } else if (windDirection > 150 && windDirection <= 210) {
                return "S";
            } else if (windDirection > 210 && windDirection <= 240) {
                return "SW";
            } else if (windDirection > 240 && windDirection <= 300) {
                return "W";
            } else if (windDirection > 300 && windDirection <=330) {
                return "NW";
            } else {
                return "N";
            }
        }
    }
}
