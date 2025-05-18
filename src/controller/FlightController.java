package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Flight;

/**
 *
 * @author migue
 */
public class FlightController {
    
    private static ArrayList <Flight> flights = new ArrayList<>();
    
    
    public FlightController() {
        
    }

    public Flight createFlight(String id, String depAirID, String sAirport, String depDate, String arrDate, String planeID, int numPassergers) {
        Flight flight = new Flight(); //corregir
        return flight;
    }
    
    public static ArrayList<Flight> getFlights() {
        return flights;
    }
    
    public DefaultTableModel toFlightsJList() {
        String[] columnas = {"ID", "Departure Airport ID", "Arrival Airport ID", "Scale airport", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); //modelo para ser devuelto
        
        if (flights.isEmpty()) {
            System.out.println("Lista de vuelos vacia");
        } else {
            for (Flight f : flights) {
                Object[] fila = new Object[] { //objeto para poner en el modelo
                    f.getId(), f.getDepartureLocation().getAirportId(), f.getArrivalLocation().getAirportId(), f.getScaleLocation().getAirportCity() , f.getDepartureDate(), f.calculateArrivalDate(), f.getPlane().getId(), f.getNumPassengers()
                 };
            }
        }
        
        return model;
    }
    
}
