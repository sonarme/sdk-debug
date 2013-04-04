package me.sonar.sdkdebug.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import me.sonar.androidsdk.R;
import me.sonar.androidsdk.dao.BaseDBFacade;
import me.sonar.androidsdk.dao.DatabaseHelper;
import me.sonar.androidsdk.model.location.LocationChange;
import me.sonar.sdkdebug.adapter.LocationChangeArrayAdapter;

import java.sql.SQLException;
import java.util.List;

public class ListLocationChangeActivity extends OrmLiteBaseListActivity<DatabaseHelper> {

    private BaseDBFacade<LocationChange> facade;
    private List<LocationChange> locationChanges;
    private ArrayAdapter<LocationChange> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facade = new BaseDBFacade<LocationChange>(this, LocationChange.class);
        setContentView(R.layout.list_view); //robolectric throws exception without this
        adapter = new LocationChangeArrayAdapter(this, getLocationChanges());
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        LocationChange lc = (LocationChange) l.getItemAtPosition(position);
        Intent intent = new Intent(this, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", lc.getLatitude());
        bundle.putDouble("lng", lc.getLongitude());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private List<LocationChange> getLocationChanges() {
        locationChanges = facade.queryForAll();
        return locationChanges;
    }

    public void clear(View view) {
        try {
            facade.deleteAll();
            adapter.clear();
//            adapter.addAll(getLocationChanges());
            adapter.notifyDataSetChanged();
        } catch (SQLException e) {
            Toast.makeText(this, "Error clearing entries", Toast.LENGTH_LONG);
        }
    }
}
