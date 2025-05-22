package model.Storage;

import java.io.IOException;
import java.util.ArrayList;
import model.Passenger;
import static model.Storage.JsonPassenger.readPassengers;

/**
 *
 * @author migue
 */
public class PassengerStorage {
    private static ArrayList <Passenger> passengers = new ArrayList<>();
    
    public PassengerStorage() throws IOException {
        //llenando lista de passengers con el archivo json
       passengers = readPassengers("json/passengers.json");
    }
    
    public ArrayList <Passenger> getPassengers() {
        return passengers;
    }
    
}
