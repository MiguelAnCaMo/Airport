package model.Storage;

import java.util.ArrayList;
import java.util.Comparator;
import model.Flight;

/**
 *
 * @author migue
 */
public class MyFlightsStorage {
    
    private ArrayList<Flight> myflights = new ArrayList<>();

    public MyFlightsStorage() {
        myflights.sort(Comparator.comparing(Flight::getId));
    }

    public ArrayList<Flight> getMyflights() {
        return myflights;
    }
    
    
    
}
