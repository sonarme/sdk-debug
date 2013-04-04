package me.sonar.sdkdebug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import me.sonar.androidsdk.R;
import me.sonar.androidsdk.model.location.GeofenceAction;

import java.text.SimpleDateFormat;
import java.util.List;

public class GeofenceActionArrayAdapter extends ArrayAdapter<GeofenceAction> {
    private final Context context;
    private final List<GeofenceAction> geofenceActions;

    public GeofenceActionArrayAdapter(Context context, List<GeofenceAction> geofenceActions) {
        super(context, R.layout.list_geofence, geofenceActions);
        this.context = context;
        this.geofenceActions = geofenceActions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_geofence_action, parent, false);

        TextView idView = (TextView) rowView.findViewById(R.id.geofence_id);
        idView.setText(geofenceActions.get(position).getGeofenceId());

        TextView nameView = (TextView) rowView.findViewById(R.id.geofence_name);
        nameView.setText(geofenceActions.get(position).getGeofenceName());

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        TextView enterView = (TextView) rowView.findViewById(R.id.geofence_action_entered);
        enterView.setText("entered: " + (geofenceActions.get(position).getEntered() == null ? "" : sdf.format(geofenceActions.get(position).getEntered())));

        TextView exitView = (TextView) rowView.findViewById(R.id.geofence_action_exited);
        exitView.setText("exited: " + (geofenceActions.get(position).getExited() == null ? "" : sdf.format(geofenceActions.get(position).getExited())));

        return rowView;
    }
}