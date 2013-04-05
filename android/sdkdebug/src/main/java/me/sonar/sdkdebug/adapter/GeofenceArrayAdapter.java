package me.sonar.sdkdebug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import me.sonar.R;
import me.sonar.androidsdk.model.location.Geofence;

import java.util.List;

public class GeofenceArrayAdapter extends ArrayAdapter<Geofence> {
    private final Context context;
    private final List<Geofence> geofences;

    public GeofenceArrayAdapter(Context context, List<Geofence> geofences) {
        super(context, R.layout.list_geofence, geofences);
        this.context = context;
        this.geofences = geofences;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_geofence, parent, false);
        TextView nameView = (TextView) rowView.findViewById(R.id.geofence_name);
        nameView.setText(geofences.get(position).getId());

        TextView latView = (TextView) rowView.findViewById(R.id.geofence_lat);
        latView.setText("lat: " + String.valueOf(geofences.get(position).getLatitude()));

        TextView lngView = (TextView) rowView.findViewById(R.id.geofence_lng);
        lngView.setText("lng: " + String.valueOf(geofences.get(position).getLongitude()));

        TextView radView = (TextView) rowView.findViewById(R.id.geofence_rad);
        radView.setText("radius: " + String.valueOf(geofences.get(position).getRadius()));

        TextView timestampView = (TextView) rowView.findViewById(R.id.geofence_timestamp);
        timestampView.setText("time: " + String.valueOf(geofences.get(position).getCreated()));

        return rowView;
    }
}