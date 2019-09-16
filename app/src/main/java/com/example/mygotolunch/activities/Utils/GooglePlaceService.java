package com.example.mygotolunch.activities.Utils;

import com.example.mygotolunch.activities.models.Restaurant;
import com.example.mygotolunch.activities.models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GooglePlaceService {

    @GET("nearbysearch/json?location=48.8850953,2.477773&radius=100&type=restaurant")
    Call<Restaurant> getAllRestaurantAroundMe(@Query("key") String key);

    @GET("details/json")
    Call<Restaurant> getAllDetailOfRestaurant(@Query("placeid")String placeId, @Query("key") String key);

    @GET("photo?")
    Call<Restaurant> getPhotoRestaurant(@Query("maxwidth") String maxwidth,
                                        @Query("photoreference") String reference,
                                        @Query("key") String key);

    //photo?maxwidth=400&photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU&key=YOUR_API_KEY



}
