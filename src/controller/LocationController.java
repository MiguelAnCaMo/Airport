package controller;

import controller.utils.Response;
import controller.utils.Status;
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
    

    
    public Location createLocation(String airportID, String airportName, String City, String Country,double latitude, double longitude) {
        Location location = new Location(airportID, airportName, airportID, airportName, 0, 0);
        ls.getLocations().add(location);
        return location;
    }

    public LocationController() {
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
    //metodo auxiliar
    private boolean hasMaxFourDecimals(double value) {
    String[] parts = String.valueOf(value).split("\\.");
    return parts.length < 2 || parts[1].length() <= 4;
}

   public Response registerLocation(String airportID, String airportName, String city, String country,
                                 double latitude, double longitude) {
    try {
        // Validación: campos de texto vacíos
        if (airportID == null || airportID.isEmpty() ||
            airportName == null || airportName.isEmpty() ||
            city == null || city.isEmpty() ||
            country == null || country.isEmpty()) {
            return new Response("No text field should be empty", Status.BAD_REQUEST);
        }

        // Validación: airportID exactamente 3 letras mayúsculas
        if (!airportID.matches("^[A-Z]{3}$")) {
            return new Response("Airport ID must be exactly 3 uppercase letters", Status.BAD_REQUEST);
        }

        // Validación: duplicado
        for (Location loc : ls.getLocations()) {
            if (loc.getAirportId().equalsIgnoreCase(airportID)) {
                return new Response("Airport ID already exists", Status.BAD_REQUEST);
            }
        }

        // Validación: latitud
        if (latitude < -90 || latitude > 90) {
            return new Response("Latitude must be in range [-90, 90]", Status.BAD_REQUEST);
        }
        if (!hasMaxFourDecimals(latitude)) {
            return new Response("Latitude must have at most 4 decimal places", Status.BAD_REQUEST);
        }

        // Validación: longitud
        if (longitude < -180 || longitude > 180) {
            return new Response("Longitude must be in range [-180, 180]", Status.BAD_REQUEST);
        }
        if (!hasMaxFourDecimals(longitude)) {
            return new Response("Longitude must have at most 4 decimal places", Status.BAD_REQUEST);
        }

       
        

        return new Response("Location created successfully", Status.CREATED);
    } catch (Exception ex) {
        return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
}


    
}
