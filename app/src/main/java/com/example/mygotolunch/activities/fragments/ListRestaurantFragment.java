package com.example.mygotolunch.activities.fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mygotolunch.R;
import com.example.mygotolunch.activities.Utils.GooglePlaceService;
import com.example.mygotolunch.activities.Utils.RetrofitInstance;
import com.example.mygotolunch.activities.adapters.RestaurantAdapter;
import com.example.mygotolunch.activities.models.Restaurant;
import com.example.mygotolunch.activities.models.Result;
import com.google.android.libraries.places.api.model.Place;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListRestaurantFragment extends Fragment {

    @BindView(R.id.recyclerRestaurant)
    RecyclerView mRecyclerView;

    private List<Result> mRestaurantList;
    private RestaurantAdapter mRestaurantAdapter;
    private RecyclerView.LayoutManager layoutManager;




    private static final String API_KEY = "AIzaSyBA3VufhPMa9khCfBD4MjESeWusHEwoc6w";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_DETAILS = "/details";
    private static final String TYPE_SEARCH = "/nearbysearch";
    private static final String OUT_JSON = "/json?";
    private static final String LOG_TAG = "ListRest";


    public ListRestaurantFragment() {
        // Required empty public constructor
    }
    public static ListRestaurantFragment newInstance() {
        return (new ListRestaurantFragment());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_list_restaurant, container, false);

        ButterKnife.bind(this, rootView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Double lat = Double.parseDouble("48.8850953");
        Double lng = Double.parseDouble("2.477773");
        int radius = 100;

        this.HTTPRequest();

        return rootView;
    }



    private void HTTPRequest(){
        GooglePlaceService googlePlaceService = RetrofitInstance.getRetrofitInstance().create(GooglePlaceService.class);
        Call<Restaurant> call = googlePlaceService.getAllRestaurantAroundMe(API_KEY);

        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {

                List<Result> mListResuts;
                mListResuts = response.body().getResults();

                mRestaurantList = new ArrayList<>();

                for (Result restaurant : mListResuts){
                    mRestaurantList.add(restaurant);

                }

                mRestaurantAdapter = new RestaurantAdapter(mRestaurantList);
                layoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mRestaurantAdapter);

            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.d("toto", "toto");
            }
        });
    }


}
