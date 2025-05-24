package model.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import model.Passenger;
import static model.json.JsonPassenger.readPassengers;

/**
 *
 * @author migue
 */
public class PassengerStorage {
    private static ArrayList <Passenger> passengers = new ArrayList<>();
    
    public PassengerStorage() throws IOException {
        //llenando lista de passengers con el archivo json
       passengers = readPassengers("json/passengers.json");
       passengers.sort(Comparator.comparing(Passenger::getId));
    }
    
    public ArrayList <Passenger> getPassengers() {
        return passengers;
    }
    
}
