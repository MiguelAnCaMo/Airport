package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import static model.JsonLocation.readLocations;
import model.Location;


/**
 *
 * @author migue
 */
public class LocationController { 
    
    private static ArrayList <Location> locations = new ArrayList<>();
   
    //dos contructores, uno para la carga del .json y el otro para agregar datos con la interfaz
    public LocationController(String a) throws IOException {
        locations = readLocations("C:\\Users\\migue\\Downloads\\Airport\\json\\locations.json");
    }
    
    public LocationController() {
        
    }
    
    public Location createLocation(String airportID, String airportName, String City, String Country) {
        Location location = new Location(airportID, airportName, airportID, airportName, 0, 0);
        locations.add(location);
        return location;
    }
    
    public Location getLocation(int index) {
        return locations.get(index);
    }
    
    public DefaultTableModel toLocationsJList() {
        String[] columnas = {"Airport ID", "Airport Name", "City", "Contry"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); //modelo para ser devuelto
        
        if(locations.isEmpty()) {
            System.out.println("Lista de ubicaciones vacia");
        } else {
             for (Location l : locations) {
                Object[] fila = new Object[] { //objeto para poner en el modelo
                    l.getAirportId(), l.getAirportName(), l.getAirportCity(), l.getAirportCountry()
                 };
                model.addRow(fila);
             }
        }
        return model;
    }
    
}
