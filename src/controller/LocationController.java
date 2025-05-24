package controller;

import controller.utils.Response;
import controller.utils.Status;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Location;
import model.Storage.LocationStorage;
import view.AirportFrame;


/**
 *
 * @author migue
 */
public class LocationController { 
    
    private static LocationStorage ls;
    private static AirportFrame airportFrame;
    //dos contructores, uno para la carga del .json y el otro para agregar datos con la interfaz
    public LocationController(String a, AirportFrame af) throws IOException {
        ls = new LocationStorage();
        airportFrame = af;
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
        // verificacion si los campos estan vacios
        if (airportID == null || airportID.isEmpty() ||
            airportName == null || airportName.isEmpty() ||
            city == null || city.isEmpty() ||
            country == null || country.isEmpty()) {
            Response b = new Response("No text field should be empty", Status.BAD_REQUEST);
            return b.clone();
        }

        // airportid verificacion
        if (!airportID.matches("^[A-Z]{3}$")) {
            Response b =new Response("Airport ID must be exactly 3 uppercase letters", Status.BAD_REQUEST);
            return b.clone();
                    
        }

        // validar si hay duplicado 
        for (Location loc : ls.getLocations()) {
            if (loc.getAirportId().equalsIgnoreCase(airportID)) {
                Response b =new Response("Airport ID already exists", Status.BAD_REQUEST);
                return b.clone();
            }
        }

        // validar latitud
        if (latitude < -90 || latitude > 90) {
            Response b=new Response("Latitude must be in range [-90, 90]", Status.BAD_REQUEST);
            return b.clone();
        }
        if (!hasMaxFourDecimals(latitude)) {
            Response b= new Response("Latitude must have at most 4 decimal places", Status.BAD_REQUEST);
            return b.clone();
        }

        // validar longitud
        if (longitude < -180 || longitude > 180) {
            Response b= new Response("Longitude must be in range [-180, 180]", Status.BAD_REQUEST);
            return b.clone();
        }
        if (!hasMaxFourDecimals(longitude)) {
            Response b = new Response("Longitude must have at most 4 decimal places", Status.BAD_REQUEST);
            return b.clone();
        }

       
        
        Response b= new Response("Location created successfully", Status.CREATED);
        return b.clone();
    } catch (Exception ex) {
        Response b = new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        return b.clone();
    }
}


    
}
