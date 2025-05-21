package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Location;
import model.Storage.LocationStorage;


/**
 *
 * @author migue
 */
public class LocationController { 
    
    private static LocationStorage ls;
    
    //dos contructores, uno para la carga del .json y el otro para agregar datos con la interfaz
    public LocationController(String a) throws IOException {
        ls = new LocationStorage();
    }
    
    public LocationController() {
        
    }
    
    public Location createLocation(String airportID, String airportName, String City, String Country) {
        Location location = new Location(airportID, airportName, airportID, airportName, 0, 0);
        ls.getLocations().add(location);
        return location;
    }
    
    public Location getLocationByID(String id) {
        ArrayList <Location> locations = ls.getLocations();
        for (Location location : locations) {
            if (location.getAirportId().equals(id)) {
                return location;
            }
        }
        return new Location("NA", "NA", "NA", "NA", 0, 0);
    }
    
    
    public DefaultTableModel toLocationsJList() {
        String[] columnas = {"Airport ID", "Airport Name", "City", "Contry"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); //modelo para ser devuelto
        
        if(ls.getLocations().isEmpty()) {
            System.out.println("Lista de ubicaciones vacia");
        } else {
             for (Location l : ls.getLocations()) {
                Object[] fila = new Object[] { //objeto para poner en el modelo
                    l.getAirportId(), l.getAirportName(), l.getAirportCity(), l.getAirportCountry()
                 };
                model.addRow(fila);
             }
        }
        return model;
    }
    
}
