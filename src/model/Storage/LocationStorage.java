package model.Storage;

import controller.LocationController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import model.Location;
import static model.json.JsonLocation.readLocations;

/**
 *
 * @author migue
 */
public class LocationStorage {
    //almacenamiento general del sistema para guardar objetos de tipo Location
    private static ArrayList <Location> locations = new ArrayList<>();
    LocationController lc = new LocationController();

    public LocationStorage() throws IOException {
       //llenando la lista con el metodo para leer archivos json
       locations = readLocations("json/locations.json");
       //ordenanlo la lista por ids
       locations.sort(Comparator.comparing(Location::getAirportId));
    }
    
    public ArrayList <Location> getLocations() {
        return locations;
    }
    
}
