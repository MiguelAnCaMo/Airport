package model.Storage;

import java.io.IOException;
import java.util.ArrayList;
import model.Flight;
import static model.Storage.JsonFlight.readFlights;

/**
 *
 * @author migue
 */
public class FlightStorage {
    private static ArrayList <Flight> flights = new ArrayList<>();

    public FlightStorage() throws IOException {
        flights = readFlights("json/flights.json");
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
    
    
}
