package com.example.mygotolunch.activities.api;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;

import java.util.Collections;
import java.util.List;

public class PlaceHelper {

    List<Place.Field> placeFields = Collections.singletonList(Place.Field.NAME);
    FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(placeFields);


}
