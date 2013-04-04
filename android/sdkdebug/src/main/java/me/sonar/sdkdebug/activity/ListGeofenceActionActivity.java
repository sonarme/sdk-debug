package me.sonar.sdkdebug.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import me.sonar.R;
import me.sonar.androidsdk.dao.BaseDBFacade;
import me.sonar.androidsdk.dao.DatabaseHelper;
import me.sonar.androidsdk.model.location.GeofenceAction;
import me.sonar.sdkdebug.adapter.GeofenceActionArrayAdapter;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ListGeofenceActionActivity extends OrmLiteBaseListActivity<DatabaseHelper> {

    private BaseDBFacade<GeofenceAction> facade;
    private ArrayAdapter<GeofenceAction> adapter;

    private List<GeofenceAction> geofenceActions = Arrays.asList(
            new GeofenceAction("esb", "Empire State Building", 10, 10, 10, new Date(), null),
            new GeofenceAction("sonarhq", "", 11, 11, 11, null, new Date()),
            new GeofenceAction("cp", null, 12, 12, 12, new Date(), null)
    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facade = new BaseDBFacade<GeofenceAction>(this, GeofenceAction.class);
        setContentView(R.layout.list_view); //robolectric throws exception without this
        adapter = new GeofenceActionArrayAdapter(this, getGeofenceActions());
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        GeofenceAction ga = (GeofenceAction) l.getItemAtPosition(position);
        Intent intent = new Intent(this, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", ga.getLatitude());
        bundle.putDouble("lng", ga.getLongitude());
        bundle.putDouble("radius", ga.getRadius());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private List<GeofenceAction> getGeofenceActions() {
        return facade.queryForAll();
    }

    public void clear(View view) {
        facade.deleteAll();
        adapter.clear();
//            adapter.addAll(getGeofenceActions());
        adapter.notifyDataSetChanged();

    }
}