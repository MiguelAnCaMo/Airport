package model.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import model.Flight;
import static model.json.JsonFlight.readFlights;

/**
 *
 * @author migue
 */
public class FlightStorage {
    //almacenamiento general del sistema para guardar objetos de tipo Flight
    private static ArrayList <Flight> flights = new ArrayList<>();

    public FlightStorage() throws IOException {
        //llenando la lista con el metodo para leer archivos json
        flights = readFlights("json/flights.json");
        //ordenanlo la lista por ids
        flights.sort(Comparator.comparing(Flight::getId));
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
    
    
}
