package model.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import model.Flight;
import model.Plane;
import static model.json.JsonFlight.readFlights;

/**
 *
 * @author migue
 */
public class FlightStorage {
    private static ArrayList <Flight> flights = new ArrayList<>();

    public FlightStorage() throws IOException {
        flights = readFlights("json/flights.json");
        flights.sort(Comparator.comparing(Flight::getId));
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
    
    
}
