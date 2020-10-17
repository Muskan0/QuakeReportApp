package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.net.URL;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String mUrl;
    private static final String LOG_TAG=EarthquakeLoader.class.getName();
    public EarthquakeLoader(Context context, String Url){
        super(context);
        mUrl=Url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if(mUrl==null) {
            return null;
        }
        List<Earthquake> result=QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }
}
