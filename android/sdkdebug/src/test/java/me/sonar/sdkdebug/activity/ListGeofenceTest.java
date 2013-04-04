package me.sonar.sdkdebug.activity;

import me.sonar.androidsdk.dao.BaseDBFacade;
import me.sonar.androidsdk.model.location.Geofence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class ListGeofenceTest {

    BaseDBFacade<Geofence> gf;
    private ListGeofenceActivity activity;

    List<Geofence> geofences = Arrays.asList(
            new Geofence("shq", "Sonar HQ", 40.745396, -73.983661, 100, new Date()),
            new Geofence("esb", "Empire State Building", 40.748265, -73.984916, 500, new Date()),
            new Geofence("cp", "Central Park", 40.765819, -73.976204, 100, new Date()),
            new Geofence("rc", "Rockafellar Center", 40.758652, -73.981032, 500, new Date())
    );

    @Before
    public void setUp() throws Exception {
        activity = new ListGeofenceActivity();
        gf = new BaseDBFacade<Geofence>(activity, Geofence.class);
        for(Geofence geofence : geofences) {
            gf.create(geofence);
        }
        activity.onCreate(null);
    }

    @Test
    public void testActivity() throws Exception {
        int count = activity.getListView().getCount();
        assertEquals(4, count);
    }
}