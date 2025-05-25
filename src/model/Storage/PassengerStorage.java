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
    //almacenamiento general del sistema para guardar objetos de tipo Plane
    private static ArrayList <Passenger> passengers = new ArrayList<>();
    
    public PassengerStorage() throws IOException {
       //llenando la lista con el metodo para leer archivos json
       passengers = readPassengers("json/passengers.json");
       //ordenanlo la lista por ids
       passengers.sort(Comparator.comparing(Passenger::getId));
    }
    
    public ArrayList <Passenger> getPassengers() {
        return passengers;
    }
    
}
