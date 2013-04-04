package me.sonar.sdkdebug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import me.sonar.androidsdk.R;
import me.sonar.androidsdk.model.location.LocationChange;

import java.util.List;

public class LocationChangeArrayAdapter extends ArrayAdapter<LocationChange> {
    private final Context context;
    private final List<LocationChange> locationChanges;

    public LocationChangeArrayAdapter(Context context, List<LocationChange> locationChanges) {
        super(context, R.layout.list_location_change, locationChanges);
        this.context = context;
        this.locationChanges = locationChanges;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_location_change, parent, false);
        TextView providerView = (TextView) rowView.findViewById(R.id.location_change_provider);
        providerView.setText(locationChanges.get(position).getProvider());

        TextView latView = (TextView) rowView.findViewById(R.id.location_change_lat);
        latView.setText("lat: " + String.valueOf(locationChanges.get(position).getLatitude()));

        TextView lngView = (TextView) rowView.findViewById(R.id.location_change_lng);
        lngView.setText("lng: " + String.valueOf(locationChanges.get(position).getLongitude()));

        TextView accuracyView = (TextView) rowView.findViewById(R.id.location_change_accuracy);
        accuracyView.setText("accuracy: " + String.valueOf(locationChanges.get(position).getAccuracy()));

        TextView speedView = (TextView) rowView.findViewById(R.id.location_change_speed);
        speedView.setText("speed: " + String.valueOf(locationChanges.get(position).getSpeed()));

        TextView altitudeView = (TextView) rowView.findViewById(R.id.location_change_altitude);
        altitudeView.setText("altitude: " + String.valueOf(locationChanges.get(position).getAltitude()));

        TextView timestampView = (TextView) rowView.findViewById(R.id.location_change_timestamp);
        timestampView.setText("time: " + String.valueOf(locationChanges.get(position).getTimestamp()));

        return rowView;
    }
}