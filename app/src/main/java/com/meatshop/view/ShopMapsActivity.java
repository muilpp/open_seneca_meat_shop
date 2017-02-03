package com.meatshop.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.widget.CheckBox;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.meatshop.BuildConfig;
import com.meatshop.R;
import com.meatshop.model.ShopLocation;
import com.meatshop.model.StaticData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    @BindView(R.id.sells_meat_cb) CheckBox sellsMeatCb;
    @BindView(R.id.family_friendly_cb) CheckBox familyFriendlyCb;
    @BindView(R.id.is_24h_cb) CheckBox is24hCb;
    @BindView(R.id.take_away_service_cb) CheckBox takeAwayCb;

    private final static String TAG = ShopMapsActivity.class.getName();
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_maps);
        ButterKnife.bind(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (savedInstanceState != null) {
            takeAwayCb.setChecked(savedInstanceState.getBoolean(BuildConfig.TAKE_AWAY_STATE));
            sellsMeatCb.setChecked(savedInstanceState.getBoolean(BuildConfig.SELLS_MEAT_STATE));
            familyFriendlyCb.setChecked(savedInstanceState.getBoolean(BuildConfig.FAMILY_FRIENDLY_STATE));
            is24hCb.setChecked(savedInstanceState.getBoolean(BuildConfig.TWENTY_FOUR_H_STATE));
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
//        Set the camera in Barcelona
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.4050794,2.1662683), 12));

        updateMap();
    }

    @OnClick(R.id.family_friendly_cb)
    public void familyFriendlyClick() {
        updateMap();
    }

    @OnClick(R.id.take_away_service_cb)
    public void takeAwayClick() {
        updateMap();
    }

    @OnClick(R.id.is_24h_cb)
    public void is24hClick() {
        updateMap();
    }

    @OnClick(R.id.sells_meat_cb)
    public void sellsMeatClick() {
        updateMap();
    }

    private void updateMap() {
        mGoogleMap.clear();

        final ShopLocation locationFilter = new ShopLocation(null, 0, 0, sellsMeatCb.isChecked(), is24hCb.isChecked(), familyFriendlyCb.isChecked(), takeAwayCb.isChecked());

        for (ShopLocation location : StaticData.getShopLocationList()) {
            if (location.equals(locationFilter))
                mGoogleMap.addMarker(new MarkerOptions()
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(location.getLat(), location.getLon()))
                    .title(location.getTitle()));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean(BuildConfig.SELLS_MEAT_STATE, sellsMeatCb.isChecked());
        outState.putBoolean(BuildConfig.FAMILY_FRIENDLY_STATE, sellsMeatCb.isChecked());
        outState.putBoolean(BuildConfig.TAKE_AWAY_STATE, sellsMeatCb.isChecked());
        outState.putBoolean(BuildConfig.TWENTY_FOUR_H_STATE, sellsMeatCb.isChecked());
    }
}