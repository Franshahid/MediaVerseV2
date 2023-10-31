package com.example.myapplication;

import static androidx.core.app.AppOpsManagerCompat.*;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment implements View.OnClickListener  {

    HttpTransport HTTP_TRANSPORT = new com.google.api.client.http.javanet.NetHttpTransport();
    JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private SearchView searchBar;
    private Button searchButton;
    private RecyclerView videoList;
    private YouTube youtube;
    private List<SearchResult> searchResults;
    private SearchListAdapter searchResultsAdapter;
    private final String API_KEY = "AIzaSyANCeqsxRm4V1UVDHOl1jQ8VXgJnnsthe4";

    NotificationManager notificationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view= inflater.inflate(R.layout.fragment_search, container, false);

        searchBar = view.findViewById(R.id.searchBar);
        videoList = view.findViewById(R.id.videoList);
        videoList.setLayoutManager(new LinearLayoutManager(getActivity()));


        searchResults = new ArrayList<>();
        searchResultsAdapter = new SearchListAdapter(searchResults);
        videoList = view.findViewById(R.id.videoList);
        videoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        videoList.setAdapter(searchResultsAdapter);
        createChannel();

        searchBar.setIconifiedByDefault(false);

        // Expand the SearchView when it is clicked
        searchBar.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.setIconified(false);
            }
        });
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!query.isEmpty()) {
                    searchYoutubeVideos(query);
                    Integer val = searchResults.size();
                }

                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchBar.getWindowToken(), 0);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle changes to the search query text as the user types (optional).
                return true;
            }
        });

        return view;
}

    private void createChannel() {
        Log.d("debug", "creatingChannel");
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CreateNotification.CHANNELID, "Farhan", NotificationManager.IMPORTANCE_DEFAULT);
        }

        notificationManager = (NotificationManager) getContext().getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        notificationManager.cancelAll();
    }

    //
    private void searchYoutubeVideos(final String query) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
                            .setApplicationName("MusicMaster")
                            .setYouTubeRequestInitializer(new YouTubeRequestInitializer(API_KEY))
                            .build();

                    searchResults.clear();
                    YouTube.Search.List search = youtube.search().list("id,snippet");
                    search.setKey(API_KEY);
                    search.setQ(query);
                    search.setType("video");
                    search.setMaxResults(10L);
                    SearchListResponse searchResponse = search.execute();
                    searchResults.addAll(searchResponse.getItems());

                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            searchResultsAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {

    }

}