package com.example.mygotolunch.activities.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.mygotolunch.activities.models.User;
import com.example.mygotolunch.activities.views.RestaurantViewHolder;
import com.example.mygotolunch.activities.views.WorkMatesViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;

public class RestaurantAdapter extends FirestoreRecyclerAdapter<User, WorkMatesViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RestaurantAdapter(@NonNull FirestoreRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull WorkMatesViewHolder workMatesViewHolder, int i, @NonNull User user) {

    }

    @NonNull
    @Override
    public WorkMatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
}
