package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.Flight;
import model.Storage.FlightStorage;

/**
 *
 * @author migue
 */
public class FlightController {
    
    private static FlightStorage fs;
    AirplaneController ac = new AirplaneController();
    LocationController lc = new LocationController();
    
    //dos contructores, uno para la carga del .json y el otro para agregar datos con la interfaz
    public FlightController(String a) throws IOException {
       fs = new FlightStorage();
    }
    
    public FlightController() {
        
    }
    
                               //buscará el avión y las ubicaciones por el id para un vuelo
    public Flight createFlight(String flightID, String planeID, String departureLocationId, String arrivalLocationId, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        //mega contructor para la creacion del vuelo 
        Flight flight = new Flight(flightID, ac.getPlaneByID(flightID), lc.getLocationByID(departureLocationId), lc.getLocationByID(arrivalLocationId), departureDate, hoursDurationArrival, minutesDurationArrival); 
        fs.getFlights().add(flight);
        return flight;
    }
    
    public static ArrayList<Flight> sendFlights() {
        return fs.getFlights();
    }
    
    public DefaultTableModel toFlightsJList() {
        String[] columnas = {"ID", "Departure Airport ID", "Arrival Airport ID", "Scale airport", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); //modelo para ser devuelto
        
        if (fs.getFlights().isEmpty()) {
            System.out.println("Lista de vuelos vacia");
        } else {
            for (Flight f : fs.getFlights()) {
                    //dos caminos, uno si scaleLocation es null y otro si no es asi
                    if (f.getScaleLocation() == null) {
                       Object[] fila = new Object[] { //objeto para poner en el modelo                                                                  
                           f.getId(), f.getDepartureLocation().getAirportId(), f.getArrivalLocation().getAirportId(), "NA" , f.getDepartureDate(), f.calculateArrivalDate(), f.getPlane().getId(), f.getNumPassengers()                         
                        };
                       model.addRow(fila);  
                    } else {
                        Object[] fila = new Object[] { 
                           f.getId(), f.getDepartureLocation().getAirportId(), f.getArrivalLocation().getAirportId(), f.getScaleLocation().getAirportCity() , f.getDepartureDate(), f.calculateArrivalDate(), f.getPlane().getId(), f.getNumPassengers()                         
                        };
                        model.addRow(fila);  
                    }              
            }
        }
        
        return model;
    }
    
    //devuelve un modelo para el comboBox de flights que esta en la sección Add to flight
    public static DefaultComboBoxModel<String> getFlightModel() {
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    for (Flight f : fs.getFlights()) {
        model.addElement(f.getId());
    }
    return model;
}
    
}
