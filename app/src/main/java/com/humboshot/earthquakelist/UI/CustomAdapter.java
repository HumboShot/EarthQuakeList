package com.humboshot.earthquakelist.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.humboshot.earthquakelist.DataObject.Earthquake;
import com.humboshot.earthquakelist.R;

import java.util.ArrayList;

/**
 * Created by Christian on 01-03-2017.
 */

public class CustomAdapter extends BaseAdapter{
    Context c;
    ArrayList<Earthquake> earthquakes;

    public CustomAdapter(Context c, ArrayList<Earthquake> earthquakes) {
        this.c = c;
        this.earthquakes = earthquakes;
    }

    @Override
    public int getCount() {
        return earthquakes.size();
    }

    @Override
    public Object getItem(int position) {
        return earthquakes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(c).inflate(R.layout.model, parent, false);
        }

        TextView locationTxt = (TextView) convertView.findViewById(R.id.locationTxt);
        TextView magnitudeTxt = (TextView) convertView.findViewById(R.id.magnitudeTxt);
        TextView timeTxt = (TextView) convertView.findViewById(R.id.timeTxt);

        final Earthquake earthquake = (Earthquake) this.getItem(position);

        locationTxt.setText(earthquake.getLocationOfQuake());
        magnitudeTxt.setText(earthquake.getMag());
        timeTxt.setText(earthquake.getTime());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, earthquake.getLocationOfQuake(),Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
