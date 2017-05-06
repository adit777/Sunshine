package com.example.adityaa.perkiraancuaca;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aditya A on 01/05/2017.
 */

public class ForecastListAdapter extends BaseAdapter{
    private String[] days = {"Senin", "Selasa","Rabu","Kamis","Jum'at","Sabtu","Minggu"};
    private Context context;
    public ForecastListAdapter(Context context){
        this.context = context;
    }
    @Override public int getCount(){
        return days.length;
    }
    @Override public Object getItem(int i){
        return days[i];
    }
    @Override public long getItemId(int i){
        return 0;
    }
    @Override public View getView(int position, View view, ViewGroup viewGroup){
        TextView textView = new TextView(context);
        textView.setText(days[position]);
        return textView;
    }

    public void updateData(List<String> listStr) {
    }
}

