package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EathquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String loc=" of ";
    public EathquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context,0,earthquakes);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    View listItemView=convertView;
    if(listItemView==null){
        listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
    }
    Earthquake currenrEarthquake= getItem(position);
        TextView magnitudeView=(TextView) listItemView.findViewById(R.id.magnitude);
        String formattedMag= formatMagnitude(currenrEarthquake.getmMagnitude());
        magnitudeView.setText(formattedMag);

        String originalLocation=currenrEarthquake.getmLocation();
        String primary_location;
        String location_offset;
        if(originalLocation.contains(loc)){
            String[] parts=originalLocation.split(loc);
            location_offset=parts[0]+ loc;
            primary_location=parts[1];
        }else{
            location_offset=getContext().getString(R.string.near_the);
            primary_location=originalLocation;
        }
        TextView olocationView=(TextView) listItemView.findViewById(R.id.location_offset);
        olocationView.setText(location_offset);

        TextView plocationView=(TextView) listItemView.findViewById(R.id.primary_location);
        plocationView.setText(primary_location);

        Date dateObject=new Date(currenrEarthquake.getmDate());
        String formattedDate= formatDate(dateObject);
        TextView dateView= (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(formattedDate);

        String formattedTime= formatTime(dateObject);
        TextView timeView=(TextView) listItemView.findViewById(R.id.time);
        timeView.setText(formattedTime);
        GradientDrawable magnitudeCircle=(GradientDrawable)magnitudeView.getBackground();
        int magnitudeColor=getMagnitudeColor(currenrEarthquake.getmMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        return listItemView;
    }

    private int getMagnitudeColor(double getmMagnitude) {
        int magnitudeColorResourceId;
        int magFloor=(int)Math.floor(getmMagnitude);
        switch(magFloor){
            case 0:
            case 1:
                magnitudeColorResourceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId=R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId=R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId=R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId=R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId=R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId=R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);

    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat f=new DecimalFormat("0.0");
        return f.format(magnitude);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat= new SimpleDateFormat("h: mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
