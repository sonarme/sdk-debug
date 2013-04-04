package me.sonar.sdkdebug.activity;

import me.sonar.androidsdk.dao.BaseDBFacade;
import me.sonar.androidsdk.model.location.LocationChange;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class ListLocationChangeTest {

    private BaseDBFacade<LocationChange> facade;
    private ListLocationChangeActivity activity;

    List<LocationChange> locationChanges = Arrays.asList(
            new LocationChange("gps", 1.1, 2.2, new Date()),
            new LocationChange("network", 3.3, 4.4, new Date())
    );

    @Before
    public void setUp() throws Exception {
        activity = new ListLocationChangeActivity();
        facade = new BaseDBFacade<LocationChange>(activity, LocationChange.class);
        for(LocationChange locationChange : locationChanges) {
            facade.create(locationChange);
        }
        activity.onCreate(null);
    }

    @Test
    public void testActivity() throws Exception {
        int count = activity.getListView().getCount();
        assertEquals(2, count);
    }
}