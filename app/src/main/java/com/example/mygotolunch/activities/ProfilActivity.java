package com.example.mygotolunch.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mygotolunch.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfilActivity extends BaseActivity {

    @BindView(R.id.profile_activity_imageview_profile)
    ImageView mImageView;
    @BindView(R.id.profile_activity_edit_text_username)
    EditText mEditText;
    @BindView(R.id.profile_activity_text_view_email)
    TextView mTextViewEmail;
    @BindView(R.id.profile_activity_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.profile_activity_check_box_is_mentor)
    CheckBox mCheckBox;
    @BindView(R.id.profile_activity_button_update)
    Button mButtonUpdate;
    @BindView(R.id.profile_activity_button_sign_out)
    Button mButtonSignOut;
    @BindView(R.id.profile_activity_button_delete)
    Button mButtonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_profil;
    }

    @OnClick(R.id.profile_activity_button_sign_out)
    public void onClickSignOutBtn(){
        FirebaseAuth.getInstance().signOut();
        goToMainActivity();
    }
    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
