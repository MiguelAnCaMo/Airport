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
    AirplaneController ac = new AirplaneController();
    LocationController lc = new LocationController();
    
    public FlightController() {
        
    }
    
                               //buscara el avión y las ubicaciones por el id para un vuelo
    public Flight createFlight(String flightID, String planeID, String departureLocationId, String arrivalLocationId, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        //mega contructor para la creacion del vuelo 
        Flight flight = new Flight(flightID, ac.getPlane(Integer.parseInt(flightID)), lc.getLocation(Integer.parseInt(departureLocationId)), lc.getLocation(Integer.parseInt(arrivalLocationId)), departureDate, hoursDurationArrival, minutesDurationArrival); 
        flights.add(flight);
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
