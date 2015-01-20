package org.vpenemetsa.oneplusweather.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.vpenemetsa.oneplusweather.R;
import org.vpenemetsa.oneplusweather.model.Weather;

import java.util.Random;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class ImageUtils {

    public static void loadImageResource(Picasso picasso, ImageView imageView, Weather weather) {
        if (weather != null) {
            Random random = new Random();
            int value;
            String weatherType = weather.getMain();
            if (weatherType.equals("Clouds")) {
                value = random.nextInt(6) + 1;
                if (value == 1) {
                    picasso.load(R.drawable.clouds1).fit().into(imageView);
                } else if (value == 2) {
                    picasso.load(R.drawable.clouds2).fit().into(imageView);
                } else if (value == 3) {
                    picasso.load(R.drawable.clouds3).fit().into(imageView);
                } else if (value == 4) {
                    picasso.load(R.drawable.clouds4).fit().into(imageView);
                } else if (value == 5) {
                    picasso.load(R.drawable.clouds5).fit().into(imageView);
                } else if (value == 6) {
                    picasso.load(R.drawable.clouds6).fit().into(imageView);
                }

            } else if (weatherType.equals("Clear")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    picasso.load(R.drawable.sun1).fit().into(imageView);
                } else if (value == 2) {
                    picasso.load(R.drawable.sun2).fit().into(imageView);
                } else if (value == 3) {
                    picasso.load(R.drawable.sun3).fit().into(imageView);
                }
            } else if (weatherType.equals("Rain") || weatherType.equals("Drizzle")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    picasso.load(R.drawable.rain1).fit().into(imageView);
                } else if (value == 2) {
                    picasso.load(R.drawable.rain2).fit().into(imageView);
                } else if (value == 3) {
                    picasso.load(R.drawable.rain3).fit().into(imageView);
                }
            } else if (weatherType.equals("Fog") || weatherType.equals("Mist")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    picasso.load(R.drawable.fog1).fit().into(imageView);
                } else if (value == 2) {
                    picasso.load(R.drawable.fog2).fit().into(imageView);
                } else if (value == 3) {
                    picasso.load(R.drawable.fog3).fit().into(imageView);
                }
            } else if (weatherType.equals("Haze")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    picasso.load(R.drawable.haze1).fit().into(imageView);
                } else if (value == 2) {
                    picasso.load(R.drawable.haze2).fit().into(imageView);
                } else if (value == 3) {
                    picasso.load(R.drawable.haze3).fit().into(imageView);
                }
            } else if (weatherType.equals("Thunderstorm")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    picasso.load(R.drawable.thunderstorm1).fit().into(imageView);
                } else if (value == 2) {
                    picasso.load(R.drawable.thunderstorm2).fit().into(imageView);
                } else if (value == 3) {
                    picasso.load(R.drawable.thunderstorm3).fit().into(imageView);
                }
            } else if (weatherType.equals("Snow")) {
                value = random.nextInt(3) + 1;
                if (value == 1) {
                    picasso.load(R.drawable.snow1).fit().into(imageView);
                } else if (value == 2) {
                    picasso.load(R.drawable.snow2).fit().into(imageView);
                } else if (value == 3) {
                    picasso.load(R.drawable.snow3).fit().into(imageView);
                }
            } else if (weatherType.equals("Smoke")) {
                picasso.load(R.drawable.smoke).fit().into(imageView);
            } else if (weatherType.equals("Sand")) {
                picasso.load(R.drawable.sand).fit().into(imageView);
            } else if (weatherType.equals("Dust")) {
                picasso.load(R.drawable.dust).fit().into(imageView);
            }
        }
    }
}
