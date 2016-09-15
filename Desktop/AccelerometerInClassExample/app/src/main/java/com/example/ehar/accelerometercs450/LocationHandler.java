package com.example.ehar.accelerometercs450;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.Observable;
import java.util.jar.Manifest;

/**
 * Created by slee13 on 9/14/2016.
 */
public class LocationHandler extends Observable implements LocationListener, ActivityCompat.OnRequestPermissionsResultCallback {

    String[] permissions = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION};
    private LocationManager locationManager = null;
    private LocationListener locationListener = null;
    private MainActivity act;
    Location location;
    double latitude, longitude;




    public LocationHandler(MainActivity act) {
        this.act = act;
        this.locationManager = (LocationManager) act.getSystemService(Activity.LOCATION_SERVICE);
        getLocation();
        setChanged();
        notifyObservers();

    }

    public Location getLocation() {
        if (ActivityCompat.checkSelfPermission(act, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(act, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(act,permissions,0);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        longitude = getLongitude();
        latitude = getLatitude();
        return location;
    }


    public double getLatitude() {
        if (location != null)
            latitude= location.getLatitude();
        return latitude;
    }

    public double getLongitude(){
        if(location != null)
            longitude=location.getLongitude();
        return longitude;
    }



    @Override
    public void onLocationChanged(Location location) {

        getLocation();
        setChanged();
        notifyObservers();

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }
}
