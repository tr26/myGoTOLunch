package com.example.mygotolunch.activities.api;

import android.location.Location;

import com.example.mygotolunch.activities.models.Restaurant;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RestaurantHelper {

    private static final String COLLECTION_NAME = "restaurants";

    // --- COLLECTION REFERENCE ---

    /*public static CollectionReference getRestaurantsCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createRestaurant(String name, String adress, String schedule, String distance, String nbOfWorkmates, String rate, String urlPicture) {
        Restaurant restaurantToCreate = new Restaurant(name, adress,  schedule,  distance,  nbOfWorkmates,  rate,  urlPicture);

        return RestaurantHelper.getRestaurantsCollection().document(name).set(restaurantToCreate);
    }*/

}
