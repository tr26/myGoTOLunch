package com.example.mygotolunch.activities.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mygotolunch.R;
import com.example.mygotolunch.activities.models.Restaurant;
import com.example.mygotolunch.activities.models.Result;
import com.example.mygotolunch.activities.views.RestaurantViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private List<Result> listRestaurant;

    public RestaurantAdapter(List<Result> listRestaurant){
        this.listRestaurant = listRestaurant;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);

        RestaurantViewHolder mRestaurantViewHolder = new RestaurantViewHolder(itemView);

        return mRestaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.update(this.listRestaurant.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listRestaurant.size();
    }
}
