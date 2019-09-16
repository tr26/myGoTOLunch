package com.example.mygotolunch.activities.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygotolunch.R;
import com.example.mygotolunch.activities.AddRestoActivity;
import com.example.mygotolunch.activities.models.Restaurant;
import com.example.mygotolunch.activities.models.Result;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    private final Context context;

    TextView restaurantName;
    TextView restaurantAdress;
    TextView scheduleRestaurant;
    @BindView(R.id.restaurant_distance)
    TextView restaurantDistance;
    @BindView(R.id.restaurant_nb_workmates)
    TextView restaurantNbWorkMate;
    TextView restaurantRate;
    LinearLayout mLinearLayout;
    ImageView mImageView;


    public RestaurantViewHolder(@NonNull View itemView) {

        super(itemView);
        context = itemView.getContext();
        restaurantName = itemView.findViewById(R.id.restaurant_name);
        restaurantAdress = itemView.findViewById(R.id.restaurant_adresse);
        restaurantRate = itemView.findViewById(R.id.restaurant_rate);
        scheduleRestaurant = itemView.findViewById(R.id.restaurant_schedule);
        mLinearLayout = itemView.findViewById(R.id.container_item_restaurant);
        mImageView = itemView.findViewById(R.id.imgRestaurantItem);

        ButterKnife.bind(itemView);
    }

    public void update(final Result restaurant){

        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddRestoActivity(restaurant.getName(), restaurant.getRating(), restaurant.getVicinity(), restaurant.getPlaceId());
            }
        });

        this.restaurantName.setText(restaurant.getName());
        this.restaurantAdress.setText(restaurant.getVicinity());
        this.restaurantRate.setText(rateMethod(restaurant.getRating()));
        this.scheduleRestaurant.setText((restaurant.getOpeningHours().getOpenNow()) ? "Open" : "Closed" );
        Picasso.with(itemView.getContext())
                .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth="+restaurant.getPhotos().get(0).getWidth()+"&photoreference="+restaurant.getPhotos().get(0).getPhotoReference()+"&key=AIzaSyBA3VufhPMa9khCfBD4MjESeWusHEwoc6w")
                .fit()
                .into(mImageView);
    }


    public String rateMethod(Double rating){
        if (rating <= 0.5) {
            return "";
        } else if (rating > 0.5 && rating <= 1.5) {
            return "*";
        } else if (rating >1.5 && rating <= 2.5) {
            return "**";
        } else if (rating > 2.5 && rating <= 3.5) {
            return "***";
        } else if (rating >3.5 && rating <= 4.5) {
            return "****";
        } else {
            return "toto";
        }
    }

    private void goToAddRestoActivity(String name, Double rating, String adress, String place_id ){
        Intent intent = new Intent(context, AddRestoActivity.class);

        intent.putExtra("name", name);
        intent.putExtra("rating", rating);
        intent.putExtra("adress", adress);
        intent.putExtra("place_id", place_id);

        context.startActivity(intent);
    }
}
