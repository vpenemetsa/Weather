package org.vpenemetsa.oneplusweather.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.vpenemetsa.oneplusweather.R;
import org.vpenemetsa.oneplusweather.model.Location;
import org.vpenemetsa.oneplusweather.responses.WeatherResponse;

import java.util.List;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<WeatherResponse> locations;

    public LocationAdapter(List<WeatherResponse> locations) {
        this.locations = locations;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView ivLocation;
        protected TextView tvLocationName;
        protected TextView tvLocationTemp;
        protected ImageView ivWeatherIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ivLocation = (ImageView) itemView.findViewById(R.id.location_image);
            tvLocationName = (TextView) itemView.findViewById(R.id.location_name);
            tvLocationTemp = (TextView) itemView.findViewById(R.id.location_temp);
            ivWeatherIcon = (ImageView) itemView.findViewById(R.id.location_weather_icon);
        }
    }

    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_element, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LocationAdapter.ViewHolder holder, int position) {
        WeatherResponse location = locations.get(position);
        holder.tvLocationTemp.setText("" + location.getMain().getTemp());
        holder.tvLocationName.setText(location.getName() + ", " + location.getAdditionalData().getCountry());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public void add(WeatherResponse location, int position) {
        locations.add(position, location);
        notifyItemInserted(position);
    }

    public void add(WeatherResponse location) {
        locations.add(location);
        Log.e("****************", "Item inserted at " + getItemCount());
        notifyItemInserted(getItemCount() - 1);
    }
    public void add(List<WeatherResponse> locations) {
        locations.addAll(locations);
        notifyDataSetChanged();
    }
}