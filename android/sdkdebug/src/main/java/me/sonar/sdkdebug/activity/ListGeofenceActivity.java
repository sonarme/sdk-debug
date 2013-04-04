package me.sonar.sdkdebug.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import me.sonar.androidsdk.R;
import me.sonar.androidsdk.dao.BaseDBFacade;
import me.sonar.androidsdk.dao.DatabaseHelper;
import me.sonar.androidsdk.model.location.Geofence;
import me.sonar.sdkdebug.adapter.GeofenceArrayAdapter;

import java.sql.SQLException;
import java.util.List;

public class ListGeofenceActivity extends OrmLiteBaseListActivity<DatabaseHelper> {

    private BaseDBFacade<Geofence> facade;
    private ArrayAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facade = new BaseDBFacade<Geofence>(this, Geofence.class);
        setContentView(R.layout.list_view);   // robolectric throws exception without this
        adapter = new GeofenceArrayAdapter(this, getGeofences());
        setListAdapter(adapter);
    }

    private List<Geofence> getGeofences() {
        /*
        List<Geofence> geofences = new ArrayList<Geofence>() {{
            add(new Geofence("sonarhq", "Sonar HQ", 40.745396, -73.983661, 100, new Date()));
            add(new Geofence("esb", "Empire State Building", 40.748265, -73.984916, 100, new Date()));
            add(new Geofence("mhs", "Macy's Herald Square", 40.750346, -73.988178, 100, new Date()));
            add(new Geofence("gcs", "Grand Central Station", 40.751093, -73.975368, 100, new Date()));
            add(new Geofence("bp", "Bryant Park", 40.754458, -73.983843, 100, new Date()));
            add(new Geofence("rc", "Rockafellar Center", 40.758652, -73.981032, 100, new Date()));
            add(new Geofence("cp", "Central Park", 40.765819, -73.976204, 100, new Date()));
        }};

        for (Geofence geofence : geofences) {
            facade.create(geofence);
        }
        */
        return facade.queryForAll();
    }

    public void clear(View view) {
        try {
            facade.deleteAll();
            adapter.clear();
            adapter.addAll(getGeofences());
            adapter.notifyDataSetChanged();

        } catch (SQLException e) {
            Toast.makeText(this, "Error clearing entries", Toast.LENGTH_LONG);
        }
    }
}