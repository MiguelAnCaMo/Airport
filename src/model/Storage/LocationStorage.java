package model.Storage;

import controller.LocationController;
import java.io.IOException;
import java.util.ArrayList;
import model.Location;
import static model.JsonLocation.readLocations;

/**
 *
 * @author migue
 */
public class LocationStorage {
    private static ArrayList <Location> locations = new ArrayList<>();
    LocationController lc = new LocationController();

    public LocationStorage() throws IOException {
       locations = readLocations("json/locations.json");
    }
    
    public ArrayList <Location> getLocations() {
        return locations;
    }
    
}
