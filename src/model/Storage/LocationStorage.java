package model.Storage;

import controller.LocationController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import model.Location;
import model.Plane;
import static model.json.JsonLocation.readLocations;

/**
 *
 * @author migue
 */
public class LocationStorage {
    private static ArrayList <Location> locations = new ArrayList<>();
    LocationController lc = new LocationController();

    public LocationStorage() throws IOException {
       locations = readLocations("json/locations.json");
       locations.sort(Comparator.comparing(Location::getAirportId));
    }
    
    public ArrayList <Location> getLocations() {
        return locations;
    }
    
}
