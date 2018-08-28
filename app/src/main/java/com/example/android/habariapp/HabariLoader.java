package com.example.android.habariapp;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class HabariLoader extends AsyncTaskLoader<List<Habari>> {

    /** Tag for log messages */
    private static final String LOG_TAG = HabariLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public HabariLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Habari> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<Habari> news = QueryUtils.fetchHabariData(mUrl);
        return news;
    }
}
