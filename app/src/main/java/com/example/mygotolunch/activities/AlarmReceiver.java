package com.example.mygotolunch.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mygotolunch.activities.api.UserHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.Nullable;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String uid = this.getCurrentUser().getUid();
        Boolean aBoolean = false;

        UserHelper.updateHasMadeHisChoice(uid, aBoolean);
        UserHelper.updateRestaurantChoosen(uid, null);

    }

    @Nullable
    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser(); }
}
