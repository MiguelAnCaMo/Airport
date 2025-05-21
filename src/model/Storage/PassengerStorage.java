package model.Storage;

import controller.PassengerController;
import java.io.IOException;
import java.util.ArrayList;
import model.Passenger;
import static model.JsonPassenger.readPassengers;

/**
 *
 * @author migue
 */
public class PassengerStorage {
    private static ArrayList <Passenger> passengers = new ArrayList<>();
    PassengerController pc = new PassengerController();
    
    public PassengerStorage() throws IOException {
        //llenando lista de passengers con el archivo json
       passengers = readPassengers("json/passengers.json");
    }
    
    public ArrayList <Passenger> getPassengers() {
        return passengers;
    }
    
}
