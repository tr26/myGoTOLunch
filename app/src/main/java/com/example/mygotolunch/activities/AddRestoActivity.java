package com.example.mygotolunch.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mygotolunch.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddRestoActivity extends BaseActivity {

    @BindView(R.id.viewImageRestaurant)
    ImageView mViewImageRestaurant;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.textViewName)
    TextView mTextViewName;
    @BindView(R.id.editTextViewRestaurant)
    EditText mEditTextRestaurant;
    @BindView(R.id.textView3)
    TextView mTextViewRating;
    @BindView(R.id.addRestaurantButton)
    Button btnAddRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_add_resto;
    }

    @OnClick(R.id.floatingActionButton)
    public void onClickTakePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mViewImageRestaurant.setImageBitmap(imageBitmap);
        }
    }
}
