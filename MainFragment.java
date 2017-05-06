package com.example.adityaa.perkiraancuaca;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adityaa.perkiraancuaca.data.Data;
import com.example.adityaa.perkiraancuaca.data.ModelWeather;
import com.example.adityaa.perkiraancuaca.data.WeatherData;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static android.content.ContentValues.TAG;

/**
 * Created by Aditya A on 23/04/2017.
 */

public class MainFragment extends Fragment {

    private ListView listView_forecast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView_forecast = (ListView) view.findViewById(R.id.listview_forecast);
        final ForecastListAdapter adapter = new ForecastListAdapter(getContext());
        listView_forecast.setAdapter(adapter);

        new Data(new Data.DataCallback() {

            @Override public void onResponse(String json) {

                Log.d(TAG, "onResponse: "+json);
                WeatherData data = new WeatherData();

                try {
                    data.getWeatherDataFromJson(json);

                    List<String> listStr = new ArrayList<String>();

                    for(ModelWeather weather : data.getModelWeatherList()){
                        listStr.add(weather.getDescription());
                    }
                    adapter.updateData(listStr);
                    listView_forecast.setAdapter(adapter);


                }catch (JSONException e){
                    e.printStackTrace();
                }

            }

            @Override public void onError(Throwable throwable) {

                Log.e(TAG, "onError: ", throwable);

            }

        }).process();
        //String url = buildUrl();

      //  Log.d(TAG, "onViewCreated: "+url);

       // new DataProcess().execute(url);
    }

}
