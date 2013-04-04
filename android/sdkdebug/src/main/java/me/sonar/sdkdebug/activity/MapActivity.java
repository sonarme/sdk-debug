package me.sonar.sdkdebug.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import me.sonar.androidsdk.R;

public class MapActivity extends Activity {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        double lat = bundle.getDouble("lat");
        double lng = bundle.getDouble("lng");
        setContentView(R.layout.map_view);
        setUpMapIfNeeded(lat, lng);
    }

    private void setUpMapIfNeeded(double lat, double lng) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                // The Map is verified. It is now safe to manipulate the map.
                LatLng latlng = new LatLng(lat, lng);
                map.addMarker(new MarkerOptions().position(latlng));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(17.0f).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                map.moveCamera(cameraUpdate);
            }
        }
    }
}