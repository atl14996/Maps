package com.example.week6project;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.renderscript.Allocation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, PermissionsManager.IPermissionManager,
        LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
PermissionsManager permissionsManager;
boolean isGranted;
LocationRequest locationRequest;
    private GoogleMap mMap;
    MarkerOptions markerOptionsOlive;
    MarkerOptions markerOptionsOutback;
    MarkerOptions markerOptionsFudd;
    MarkerOptions markerOptionsSchlotzskys;
    MarkerOptions markerOptionsPappaD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        permissionsManager = new PermissionsManager(this);
        permissionsManager.requestPermission();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng oliveGarden = new LatLng(34.0334068, -84.574854);
        LatLng outbackSteakhouse = new LatLng(34.033248, -84.5748547);
        LatLng fuddruckers = new LatLng(33.9049774, -84.4672558);
        LatLng schlotzskys = new LatLng(33.90429724, -84.5000867);
        LatLng pappadeaux = new LatLng(33.9031476, -84.4725289);
        mMap = googleMap;

        // Add a marker in Sydney and move the camera


        mMap.addMarker(new MarkerOptions().position(oliveGarden).icon(BitmapDescriptorFactory.fromResource(R.drawable.olive_garden_logo)));
        mMap.addMarker(new MarkerOptions().position(outbackSteakhouse).icon(BitmapDescriptorFactory.fromResource(R.drawable.outback_steakhouse_1)));
        mMap.addMarker(new MarkerOptions().position(fuddruckers).icon(BitmapDescriptorFactory.fromResource(R.drawable.fudd)));
        mMap.addMarker(new MarkerOptions().position(schlotzskys).icon(BitmapDescriptorFactory.fromResource(R.drawable.Schlotzskys)));
        mMap.addMarker(new MarkerOptions().position(pappadeaux).icon(BitmapDescriptorFactory.fromResource(R.drawable.Pappadeaux_1024x226)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(outbackSteakhouse));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.checkPermission();
        if(isGranted) {

        }
    }

    public void getLastKnownLocation() {
        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(this);

fusedLocationProviderClient.getLastLocation().addOnSuccessListener(moveToLocation((Location location, location.toString));)



    }

    public void moveToLocation(Location location, String LocationTag) {
        LatLng locationsLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(locationsLatLng).title(LocationTag));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(locationsLatLng));

    }

    public String getAddress(Location location) {
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList = new ArrayList<>();
        try {
            addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addressList.size() > 0 ? addressList.get(0).getAddressLine(0) : "";
    }

    @Override
    public void onPermissionResult(boolean isGranted) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
