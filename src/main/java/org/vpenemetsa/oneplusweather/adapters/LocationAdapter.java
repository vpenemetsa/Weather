package org.vpenemetsa.oneplusweather.adapters;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.vpenemetsa.oneplusweather.Constants;
import org.vpenemetsa.oneplusweather.R;
import org.vpenemetsa.oneplusweather.SavedLocations;
import org.vpenemetsa.oneplusweather.activities.DetailActivity;
import org.vpenemetsa.oneplusweather.activities.ListActivity;
import org.vpenemetsa.oneplusweather.model.Weather;
import org.vpenemetsa.oneplusweather.responses.WeatherResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private ArrayList<WeatherResponse> locations;
    private Picasso mPicasso;
    private SavedLocations mSavedLocations;

    private String mTransitionName;
    private ListActivity mContext;

    public LocationAdapter(ListActivity context, List<WeatherResponse> locations, SavedLocations savedLocations) {
        this.locations = new ArrayList<>(locations);
        mPicasso = Picasso.with(context);
        mSavedLocations = savedLocations;
        mTransitionName = context.getString(R.string.transition_weather_image);
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView ivLocation;
        protected TextView tvLocationName;
        protected TextView tvLocationTemp;
        protected ImageView ivWeatherIcon;
        protected CardView cvLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ivLocation = (ImageView) itemView.findViewById(R.id.location_image);
            tvLocationName = (TextView) itemView.findViewById(R.id.location_name);
            tvLocationTemp = (TextView) itemView.findViewById(R.id.location_temp);
            ivWeatherIcon = (ImageView) itemView.findViewById(R.id.location_weather_icon);
            cvLayout = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_element, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final LocationAdapter.ViewHolder holder, int position) {
        WeatherResponse location = locations.get(position);
        if (location != null) {
            holder.tvLocationTemp.setText("" + location.getMain().getTemp());
            holder.tvLocationName.setText(location.getName() + ", " + location.getAdditionalData().getCountry());

            Weather currentWeather = location.getWeather().get(0);
            if (currentWeather != null) {
                loadImageResource(holder.ivLocation, currentWeather);
                mPicasso.load(Constants.OPEN_WEATHER_ICON_ENDPOINT + currentWeather.getIcon() + ".png")
                        .resize(96, 96)
                        .into(holder.ivWeatherIcon);
            }

            holder.cvLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), DetailActivity.class);
                    intent.putParcelableArrayListExtra(Constants.PARCEL_LOCATIONS, locations);
                    ActivityCompat.startActivity(mContext, intent, null);
                }
            });
        }
    }

    private void loadImageResource(ImageView imageView, Weather weather) {
        if (weather != null) {
            Random random = new Random();
            int value;
            String weatherType = weather.getMain();
            if (weatherType.equals("Clouds")) {
                value = random.nextInt(6) + 1;
                if (value == 1) {
                    mPicasso.load(R.drawable.clouds1).fit().into(imageView);
                } else if (value == 2) {
                    mPicasso.load(R.drawable.clouds2).fit().into(imageView);
                } else if (value == 3) {
                    mPicasso.load(R.drawable.clouds3).fit().into(imageView);
                } else if (value == 4) {
                    mPicasso.load(R.drawable.clouds4).fit().into(imageView);
                } else if (value == 5) {
                    mPicasso.load(R.drawable.clouds5).fit().into(imageView);
                } else if (value == 6) {
                    mPicasso.load(R.drawable.clouds6).fit().into(imageView);
                }

            } else if (weatherType.equals("Clear")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    mPicasso.load(R.drawable.sun1).fit().into(imageView);
                } else if (value == 2) {
                    mPicasso.load(R.drawable.sun2).fit().into(imageView);
                } else if (value == 3) {
                    mPicasso.load(R.drawable.sun3).fit().into(imageView);
                }
            } else if (weatherType.equals("Rain") || weatherType.equals("Drizzle")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    mPicasso.load(R.drawable.rain1).fit().into(imageView);
                } else if (value == 2) {
                    mPicasso.load(R.drawable.rain2).fit().into(imageView);
                } else if (value == 3) {
                    mPicasso.load(R.drawable.rain3).fit().into(imageView);
                }
            } else if (weatherType.equals("Fog") || weatherType.equals("Mist")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    mPicasso.load(R.drawable.fog1).fit().into(imageView);
                } else if (value == 2) {
                    mPicasso.load(R.drawable.fog2).fit().into(imageView);
                } else if (value == 3) {
                    mPicasso.load(R.drawable.fog3).fit().into(imageView);
                }
            } else if (weatherType.equals("Haze")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    mPicasso.load(R.drawable.haze1).fit().into(imageView);
                } else if (value == 2) {
                    mPicasso.load(R.drawable.haze2).fit().into(imageView);
                } else if (value == 3) {
                    mPicasso.load(R.drawable.haze3).fit().into(imageView);
                }
            } else if (weatherType.equals("Thunderstorm")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    mPicasso.load(R.drawable.thunderstorm1).fit().into(imageView);
                } else if (value == 2) {
                    mPicasso.load(R.drawable.thunderstorm2).fit().into(imageView);
                } else if (value == 3) {
                    mPicasso.load(R.drawable.thunderstorm3).fit().into(imageView);
                }
            } else if (weatherType.equals("Snow")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    mPicasso.load(R.drawable.snow1).fit().into(imageView);
                } else if (value == 2) {
                    mPicasso.load(R.drawable.snow2).fit().into(imageView);
                } else if (value == 3) {
                    mPicasso.load(R.drawable.snow3).fit().into(imageView);
                }
            } else if (weatherType.equals("Smoke")) {
                mPicasso.load(R.drawable.smoke).fit().into(imageView);
            } else if (weatherType.equals("Sand")) {
                mPicasso.load(R.drawable.sand).fit().into(imageView);
            } else if (weatherType.equals("Dust")) {
                mPicasso.load(R.drawable.dust).fit().into(imageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public void add(WeatherResponse location, int position) {
        locations.add(position, location);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void add(WeatherResponse location) {
        locations.add(location);
        Log.i("****************", "Item inserted at " + getItemCount());
        notifyItemInserted(getItemCount() - 1);
        notifyDataSetChanged();
    }

    public void replace(WeatherResponse location, int position) {
        locations.remove(position);
        locations.add(position, location);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        locations.remove(position);
        notifyItemRemoved(position);
        mSavedLocations.saveLocationsFromAdapter(locations);
        notifyDataSetChanged();
    }
}