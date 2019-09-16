package com.example.mygotolunch.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mygotolunch.R;
import com.example.mygotolunch.activities.Utils.GooglePlaceService;
import com.example.mygotolunch.activities.Utils.RetrofitInstance;
import com.example.mygotolunch.activities.adapters.RestaurantAdapter;
import com.example.mygotolunch.activities.adapters.WorkMatesAdapter;
import com.example.mygotolunch.activities.api.UserHelper;
import com.example.mygotolunch.activities.models.Details;
import com.example.mygotolunch.activities.models.Restaurant;
import com.example.mygotolunch.activities.models.Result;
import com.example.mygotolunch.activities.models.User;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AddRestoActivity extends BaseActivity implements WorkMatesAdapter.Listener {

    RecyclerView recyclerView;
    private WorkMatesAdapter mWorkMatesAdapter;


    ImageView mViewImageRestaurant;
    TextView mTextViewName;
    TextView mTextViewAdress;
    LinearLayout mLinearLayoutCall;
    LinearLayout mLinearLayoutLike;
    LinearLayout mLinearLayoutWebsite;
    RecyclerView mRecyclerView;
    FloatingActionButton mFloatingActionButton;
    TextView mTextViewRate;

    private List<Result> mRestaurantList;

    private String nameRestaurant;
    private String place_id;
    private String mTelRestaurant;
    private String urlWebsite;
    private String maxWidth;
    private String referencePhtoto;
    private String idRestaurant;

    private final String baseURL = "https://maps.googleapis.com/maps/api/place/photo?";
    private static final String API_KEY = "AIzaSyBA3VufhPMa9khCfBD4MjESeWusHEwoc6w";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mTextViewName = findViewById(R.id.textView2RestaurantName);
        mTextViewAdress = findViewById(R.id.textView2RestaurantAdress);
        mViewImageRestaurant = findViewById(R.id.viewImageRestaurant);
        mLinearLayoutCall = findViewById(R.id.btnCall);
        mLinearLayoutLike = findViewById(R.id.btnLike);
        mLinearLayoutWebsite = findViewById(R.id.btnWebsite);
        mRecyclerView = findViewById(R.id.recyclerViewWorkMatesRestaurant);
        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mTextViewRate = findViewById(R.id.textView2RestaurantRate);

        Bundle bundle = getIntent().getExtras();

        nameRestaurant = bundle.getString("name");
        String adressRestaurant = bundle.getString("adress");
        Double ratingRestaurant = bundle.getDouble("rating");
        place_id = bundle.getString("place_id");

        mTextViewName.setText(nameRestaurant);
        mTextViewRate.setText(rateMethod(ratingRestaurant));
        mTextViewAdress.setText(adressRestaurant);

        this.HTTPRequest();

        //this.configureRecyclerView();

    }

    private void HTTPRequest(){
        GooglePlaceService googlePlaceService = RetrofitInstance.getRetrofitInstance().create(GooglePlaceService.class);
        Call<Restaurant> call = googlePlaceService.getAllDetailOfRestaurant(place_id, API_KEY);

        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {

                idRestaurant = response.body().getResult().getId();
                mTelRestaurant = response.body().getResult().getInternationalPhoneNumber();
                urlWebsite = response.body().getResult().getWebsite();
                maxWidth = response.body().getResult().getPhotos().get(0).getWidth().toString();
                referencePhtoto = response.body().getResult().getPhotos().get(0).getPhotoReference();

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;

                Picasso.with(getApplicationContext())
                        .load(baseURL+"maxwidth=5100&photoreference="+referencePhtoto+"&key="+API_KEY)
                        .fit()
                        .into(mViewImageRestaurant);

                configureRecyclerView();
            }
            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

            }
        });
    }

    private void configureRecyclerView(){

        this.mWorkMatesAdapter = new WorkMatesAdapter(generateOptionsForAdapter(
                UserHelper.getUsersCollection()),
                Glide.with(this),
                this,
                this.getCurrentUser().getUid());

        mWorkMatesAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                mRecyclerView.smoothScrollToPosition(mWorkMatesAdapter.getItemCount()); // Scroll to bottom on new messages
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mWorkMatesAdapter);
    }

    private FirestoreRecyclerOptions<User> generateOptionsForAdapter(Query query){
        return new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .setLifecycleOwner(this)
                .build();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_add_resto;
    }

    // ----- ACTION BUTTON ------



    @OnClick(R.id.floatingActionButton)
    public void chooseRestaurant(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mFloatingActionButton.setForeground(ContextCompat.getDrawable(this, R.drawable.ic_check_circle_black_24dp));
        }
        if (this.getCurrentUser() != null){
            UserHelper.updateRestaurantChoosen(this.getCurrentUser().getUid(), idRestaurant)
                        .addOnFailureListener(this.onFailureListener());
                       // .addOnSuccessListener(this.updateUIAfterRESTRequestsCompleted(UPDATE_USERNAME));

            UserHelper.updateHasMadeHisChoice(this.getCurrentUser().getUid(), Boolean.TRUE);
        }
        //Toast.makeText(this, "pppppppp", Toast.LENGTH_LONG).show();
    }


    @OnClick(R.id.btnWebsite)
    public void onClickWebsiteBtn(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(nameRestaurant);
        alertDialog.setMessage("Do you want to go to this website ?");
        alertDialog.setIcon(R.drawable.ic_public_blue_24dp);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtra("url", urlWebsite);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    @OnClick(R.id.btnCall)
    public void onClickCallBtn(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(mTelRestaurant);
        alertDialog.setMessage("Do you want to Call ?");
        alertDialog.setIcon(R.drawable.ic_phone_blue_24dp);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mTelRestaurant));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //request permission from user if the app hasn't got the required permission
                    ActivityCompat.requestPermissions(getParent(),
                                                        new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                                                        10);
                    return;
                } else {     //have got permission
                    try {
                        startActivity(callIntent);  //call activity and make phone call
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();

    }
    @OnClick(R.id.btnLike)
    public void onClickLikeBtn(){
        Toast.makeText(this, "tatatata", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mViewImageRestaurant.setImageBitmap(imageBitmap);
        }
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

    @Override
    public void onDataChanged() {

    }
}
