package com.example.mygotolunch.activities.views;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygotolunch.activities.AddRestoActivity;
import com.example.mygotolunch.activities.Main2Activity;
import com.example.mygotolunch.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.UploadTask;
import com.example.mygotolunch.R;
import com.example.mygotolunch.activities.models.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkMatesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageViewWorkMate)
    ImageView mImageViewWorkMate;
    @BindView(R.id.textViewWprkMate)
    TextView mTextViewWorkMate;

    private final int colorBeforeHavingChoosen;
    private final int colorAfterHavingChoosen;
    //private final String idRestaurant;

    @Nullable
    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser(); }

    public WorkMatesViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        colorBeforeHavingChoosen = ContextCompat.getColor(itemView.getContext(), R.color.colorGreen);
        colorAfterHavingChoosen = ContextCompat.getColor(itemView.getContext(), R.color.colorError);

        //idRestaurant = AddRestoActivity;

    }

    public void updateLayout1(User user, RequestManager glide){


        if (this.itemView.getContext() instanceof AddRestoActivity ){
            Boolean isThisRestaurantChoosen;

            Log.d("toto", "toto");

            this.mTextViewWorkMate.setText(
                    (user.getIdRestaurantChoosen() == "49bbf3b738766e104b0e14dd403b840b957573c8") ?
                    user.getUsername() + " a choisi son restaurant" :
                    "aaa");


            if (user.getUrlPicture() != null) {
                Glide.with(this.itemView).load(user.getUrlPicture()).apply(RequestOptions.circleCropTransform()).into(mImageViewWorkMate);
            } else {
                this.mImageViewWorkMate.setImageResource(R.drawable.ic_anon_user_48dp);

            }


        } else {

            Boolean isTheChoiceMade = user.getHasMadeHisChoice();

            this.mTextViewWorkMate.setText(isTheChoiceMade ? user.getUsername() + " a choisi son restaurant" :
                    user.getUsername() + " n'a pas encore choisi son restaurant");
            this.mTextViewWorkMate.setTextColor(isTheChoiceMade ? colorBeforeHavingChoosen : colorAfterHavingChoosen);

            // Update profile picture ImageView

            if (user.getUrlPicture() != null) {
                Glide.with(this.itemView).load(user.getUrlPicture()).apply(RequestOptions.circleCropTransform()).into(mImageViewWorkMate);
            } else {
                this.mImageViewWorkMate.setImageResource(R.drawable.ic_anon_user_48dp);

            }
        }

    }


}
