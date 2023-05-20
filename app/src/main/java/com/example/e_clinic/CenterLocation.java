package com.example.e_clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class CenterLocation extends FragmentActivity implements OnMapReadyCallback {

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_location);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(),currentLocation.getLatitude()+""+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(CenterLocation.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Your Location");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        googleMap.addMarker(markerOptions);


        //Covid Center's
        LatLng center1 = new LatLng(19.15154087876843, 72.83200476096786);
        MarkerOptions markerOptions1 = new MarkerOptions().position(center1).title("Andheri (West)  " + center1);
        markerOptions1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions1);

        LatLng center2 = new LatLng(19.088343268567638, 72.90529088751565);
        MarkerOptions markerOptions2 = new MarkerOptions().position(center2).title("Ghatkoper (West)  " + center2);
        markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions2);

        LatLng center3 = new LatLng(19.179902166206332, 72.8425695014002);
        MarkerOptions markerOptions3 = new MarkerOptions().position(center3).title("Malad (West)  " + center3);
        markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions3);

        LatLng center4 = new LatLng(18.972403840275717, 72.80920119731272);
        MarkerOptions markerOptions4 = new MarkerOptions().position(center4).title("Pedder Road  " + center4);
        markerOptions4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions4);

        LatLng center5 = new LatLng(19.242668669853085, 72.85442296848153);
        MarkerOptions markerOptions5 = new MarkerOptions().position(center5).title("Borivali (West)  " + center5);
        markerOptions5.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions5);

        LatLng center6 = new LatLng(19.360479483632478, 72.81096633863426);
        MarkerOptions markerOptions6 = new MarkerOptions().position(center6).title("Vasai (West)  " + center6);
        markerOptions6.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions6);

        LatLng center7 = new LatLng(19.21250762284874, 72.85196942289494);
        MarkerOptions markerOptions7 = new MarkerOptions().position(center7).title("Kandivali (West)  " + center7);
        markerOptions7.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions7);

        LatLng center8 = new LatLng(19.091384481377816, 72.86535686725675);
        MarkerOptions markerOptions8 = new MarkerOptions().position(center8).title("International Airport  " + center8);
        markerOptions8.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions8);

        LatLng center9 = new LatLng(19.00615053358532, 73.12030479731331);
        MarkerOptions markerOptions9 = new MarkerOptions().position(center9).title("Panvel (East)  " + center9);
        markerOptions9.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions9);

        LatLng center10 = new LatLng(19.246853726976898, 72.97586683964619);
        MarkerOptions markerOptions10 = new MarkerOptions().position(center10).title("Thane (West)  " + center10);
        markerOptions10.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions10);

        LatLng center11 = new LatLng(19.07177555100922, 72.86254971661377);
        MarkerOptions markerOptions11 = new MarkerOptions().position(center11).title("Chembur  " + center11);
        markerOptions11.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions11);


        LatLng center12 = new LatLng(19.05461842483412, 72.89143119863719);
        MarkerOptions markerOptions12 = new MarkerOptions().position(center12).title("Chembur  " + center12);
        markerOptions12.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOptions12);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }
}