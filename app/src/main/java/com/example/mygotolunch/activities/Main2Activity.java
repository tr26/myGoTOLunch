package com.example.mygotolunch.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygotolunch.R;
import com.example.mygotolunch.activities.adapters.PageAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class Main2Activity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_main_navigation_drawer)
    DrawerLayout mDrawerLayout;

    //TextView mTextView2MainNav;
    //TextView mTextView1MainNav;
   // ImageView mImageViewMainNav;

    @BindView(R.id.activity_main_navView)
    NavigationView mNavigationView;
    @BindView(R.id.viewPagerActivityMain)
    ViewPager mViewPager;

    @BindView(R.id.tabMainActivity)
    TabLayout tab;
    //@BindView(R.id.fabFoo)
    //FloatingActionButton mFloatingActionButton;

    private GoogleMap mMap;
    private Button mSearchBtn;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 10, locationListener);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSearchBtn = (Button) findViewById(R.id.search) ;


       // mImageViewMainNav = findViewById(R.id.imageViewMainNav);
      //  mTextView1MainNav = findViewById(R.id.textView1MainNav);
       // mTextView2MainNav = findViewById(R.id.textView2MainNav);

        setSupportActionBar(mToolbar);
        this.configureDrawerLayout();
        this.configureNavigationView();
        this.configureViewPagerAndTabLayout();

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("location", location.toString());
                double currentLatitude = location.getLatitude();
                double currentLongitude = location.getLongitude();

                LatLng latLng = new LatLng(currentLatitude, currentLongitude);

                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title("I am here!");
                mMap.addMarker(options);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }


        };


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //askForPermissions
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);

        startAlarm(alarmManager, pendingIntent);

    }


    @Override
    public int getFragmentLayout() {
        return R.layout.activity_main2;
    }

    //2-DrawerLayout
    private void configureDrawerLayout(){

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

    }
    //3-Configure NavigationView
    private void configureNavigationView(){
        mNavigationView.setNavigationItemSelectedListener(this);

    }
    //4-Configure ViewPager et TabLayout
    private void configureViewPagerAndTabLayout(){
        mViewPager.setAdapter(
                new PageAdapter(getSupportFragmentManager(), getResources()
                        .getIntArray(R.array.colorPagesViewPager)));

        tab.setupWithViewPager(mViewPager);//linked to ViewPager
        tab.setTabMode(TabLayout.MODE_FIXED);//Same size for all buttons
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menu) {
        switch (menu.getItemId()){
            case R.id.search:
                Log.e("test", "test");
                return true;
            default:
                return super.onOptionsItemSelected(menu);
        }
    }
}
