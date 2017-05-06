package com.example.adityaa.perkiraancuaca.data;

import android.text.format.Time;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya A on 01/05/2017.
 */

public class WeatherData {
    String cityName;
    double cityLatitude;
    double cityLongitude;
    private List<ModelWeather> modelWeatherList;

    public void getWeatherDataFromJson(String forecastJsonStr)
        throws JSONException {
        final String OWM_CITY = "city";
        final String OWM_CITY_NAME = "name";
        final String OWM_COORD = "coord";

        final String OWM_LATITUDE = "lat";

        final String OWM_LONGITUDE = "lon";



        final String OWM_LIST = "list";



        final String OWM_PRESSURE = "pressure";

        final String OWM_HUMIDITY = "humidity";

        final String OWM_WINDSPEED = "speed";

        final String OWM_WIND_DIRECTION = "deg";



        final String OWM_TEMPERATURE = "temp";

        final String OWM_MAX = "max";

        final String OWM_MIN = "min";



        final String OWM_WEATHER = "weather";

        final String OWM_DESCRIPTION = "main";

        final String OWM_WEATHER_ID = "id";



        try {



            JSONObject forecastJson = new JSONObject(forecastJsonStr);



            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);



            JSONObject cityJson = forecastJson.getJSONObject(OWM_CITY);



            cityName = cityJson.getString(OWM_CITY_NAME);



            JSONObject cityCoord = cityJson.getJSONObject(OWM_COORD);

            cityLatitude = cityCoord.getDouble(OWM_LATITUDE);

            cityLongitude = cityCoord.getDouble(OWM_LONGITUDE);





            Time dayTime = new Time();

            dayTime.setToNow();



            // we start at the day returned by local time. Otherwise this is a mess.

            int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);



            // now we work exclusively in UTC

            dayTime = new Time();





            modelWeatherList = new ArrayList<>();



            for(int i = 0; i < weatherArray.length(); i++) {

                // These are the values that will be collected.

                long dateTime;

                double pressure;

                int humidity;

                double windSpeed;

                double windDirection;



                double high;

                double low;



                String description;

                int weatherId;



                // Get the JSON object representing the day

                JSONObject dayForecast = weatherArray.getJSONObject(i);



                // Cheating to convert this to UTC time, which is what we want anyhow

                dateTime = dayTime.setJulianDay(julianStartDay+i);



                pressure = dayForecast.getDouble(OWM_PRESSURE);

                humidity = dayForecast.getInt(OWM_HUMIDITY);

                windSpeed = dayForecast.getDouble(OWM_WINDSPEED);

                windDirection = dayForecast.getDouble(OWM_WIND_DIRECTION);



                // Description is in a child array called "weather", which is 1 element long.

                // That element also contains a weather code.

                JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);

                description = weatherObject.getString(OWM_DESCRIPTION);

                weatherId = weatherObject.getInt(OWM_WEATHER_ID);



                // Temperatures are in a child object called "temp".  Try not to name variables

                // "temp" when working with temperature.  It confuses everybody.

                JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);

                high = temperatureObject.getDouble(OWM_MAX);

                low = temperatureObject.getDouble(OWM_MIN);





                ModelWeather weather = new ModelWeather();

                weather.setDateTime(dateTime);

                weather.setPressure(pressure);

                weather.setHumidity(humidity);

                weather.setWindSpeed(windSpeed);

                weather.setWindDirection(windDirection);

                weather.setDescription(description);

                weather.setWeatherId(weatherId);

                weather.setHigh(high);

                weather.setLow(low);

                modelWeatherList.add(weather);

            }



        } catch (JSONException e) {

            e.printStackTrace();

        }

    }



    public void setCityName(String cityName) {

        this.cityName = cityName;

    }



    public void setCityLatitude(double cityLatitude) {

        this.cityLatitude = cityLatitude;

    }



    public void setCityLongitude(double cityLongitude) {

        this.cityLongitude = cityLongitude;

    }



    public void setModelWeatherList(List<ModelWeather> modelWeatherList) {

        this.modelWeatherList = modelWeatherList;

    }

    public List<ModelWeather> getModelWeatherList() {
        return modelWeatherList;
    }

    public String getCityName() {
        return cityName;
    }

    public double getCityLatitude() {
        return cityLatitude;
    }

    public double getCityLongitude() {
        return cityLongitude;
    }
}

