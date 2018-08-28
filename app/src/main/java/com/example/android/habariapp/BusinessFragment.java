package com.example.android.habariapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BusinessFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Habari>>{

    private static final String LOG_TAG = BusinessFragment.class.getName();

    private static final String HABARI_REQUEST_URL = "https://content.guardianapis.com/business?api-key=test";

    private static final int HABARI_LOADER_ID = 1;

    private HabariAdapter mAdapter;

    private TextView mEmptyStateTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.habari_list, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_text_view);
        listView.setEmptyView(mEmptyStateTextView);

        //creates list view
        mAdapter = new HabariAdapter(getActivity(), new ArrayList<Habari>());
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Habari currentNew = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri previewUri = Uri.parse(currentNew.getWebUrl());

                // Create a new intent to view the new url
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, previewUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();
        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(HABARI_LOADER_ID, null, BusinessFragment.this);
        return rootView;
    }

    @Override
    public Loader<List<Habari>> onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse(HABARI_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // Create a new loader for the given URL
        return new HabariLoader(getActivity(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Habari>> loader, List<Habari> news) {

        mEmptyStateTextView.setText(R.string.no_news);

        mAdapter.clear();

        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Habari>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
