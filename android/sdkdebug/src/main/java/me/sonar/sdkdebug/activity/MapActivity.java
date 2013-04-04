package me.sonar.sdkdebug.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import me.sonar.R;

public class MapActivity extends Activity {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        double lat = bundle.getDouble("lat");
        double lng = bundle.getDouble("lng");
        double radius = bundle.containsKey("radius") ? bundle.getDouble("radius") : 0;
        setContentView(R.layout.map_view);
        setUpMapIfNeeded(lat, lng, radius);
    }

    private void setUpMapIfNeeded(double lat, double lng, double radius) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                // The Map is verified. It is now safe to manipulate the map.
                LatLng latlng = new LatLng(lat, lng);
                map.addMarker(new MarkerOptions()
                        .position(latlng));
                map.addCircle(new CircleOptions()
                        .center(latlng)
                        .radius(radius)
                        .strokeColor(Color.argb(50, 0, 0, 255))
                        .strokeWidth(1)
                        .fillColor(Color.argb(50, 0, 0, 255)));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latlng)
                        .zoom(17.0f)
                        .build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                map.moveCamera(cameraUpdate);
            }
        }
    }
}