package com.example.mygotolunch.activities.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
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

    @Nullable
    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser(); }

    public WorkMatesViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        colorBeforeHavingChoosen = ContextCompat.getColor(itemView.getContext(), R.color.colorGreen);
        colorAfterHavingChoosen = ContextCompat.getColor(itemView.getContext(), R.color.colorError);

    }

    public void updateLayout(User user, RequestManager glide){

        Boolean isTheChoiceMade = user.getHasMadeHisChoice();
        this.mTextViewWorkMate.setText(isTheChoiceMade ? user.getUsername()+" a choisi son restaurant" :
                                                         user.getUsername()+" n'a pas encore choisi son restaurant");
        this.mTextViewWorkMate.setTextColor(!isTheChoiceMade ? colorBeforeHavingChoosen : colorAfterHavingChoosen);

        // Update profile picture ImageView
        /*if (user.getUrlPicture() != null) {
            glide.load(user.getUrlPicture())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mImageViewWorkMate);


        }*/
        if (this.getCurrentUser().getPhotoUrl() != null){
            Glide.with(this.itemView)
                    .load(this.getCurrentUser().getPhotoUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mImageViewWorkMate);

        }
        //this.mImageViewWorkMate
    }

}
