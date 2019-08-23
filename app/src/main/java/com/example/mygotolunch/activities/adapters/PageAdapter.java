package com.example.mygotolunch.activities.adapters;

import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import com.example.mygotolunch.R;
import com.example.mygotolunch.activities.fragments.ListRestaurantFragment;
import com.example.mygotolunch.activities.fragments.MapFragment;
import com.example.mygotolunch.activities.fragments.WorkMatesFragment;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    Drawable myDrawable;
    String title;

    public PageAdapter(FragmentManager fm, int[] colors) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MapFragment.newInstance();
            case 1:
                return ListRestaurantFragment.newInstance();
            case 2:
                return WorkMatesFragment.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                title = "MAP VIEW";
            case 1:
                title = "LIST VIEW";
            case 2:
                title = "WORK MATES";

            default:
                return null;
        }

    }
}
