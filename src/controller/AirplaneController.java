package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Plane;
import model.Storage.AirplaneStorage;

/**
 *
 * @author migue
 */
public class AirplaneController {
   
    private static AirplaneStorage as;
    
    //dos contructores, uno para la carga del .json y el otro para agregar datos con la interfaz
    public AirplaneController(String a) throws IOException {
       as = new AirplaneStorage();
    }
    
    public AirplaneController() {
        
    }
    
    //esto crea un avion
    public void createPlane(String id, String brand, String model, int maxCapacity, String Airline) {
        Plane plane = new Plane(id, brand, model, maxCapacity, Airline);
        as.getPlanes().add(plane); 
    }

    public static ArrayList<Plane> getPlanes() {
        return as.getPlanes();
    }
    
    public Plane getPlaneByID(String id) {      
        for (Plane plane : as.getPlanes()) {
            if (plane.getId().equals(id)) {
                return plane;               
            }
        }
        return new Plane("NA", "NA", "NA", 0 ,"NA");
    }
    
    //funcion que devuelve un modelo para poner a la lista de aviones en la interfaz
    public DefaultTableModel toPlanesJList() {
        String[] columnas = {"ID", "Model", "Model", "Max Capacity", "Airline", "Number Flights"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); //modelo para ser devuelto
        
        if (as.getPlanes().isEmpty()) {
            System.out.println("Lista de aviones vacia");
        } else {
             for (Plane p : as.getPlanes()) {
                 Object[] fila = new Object[] { //objeto para poner en el modelo
                    p.getId(), p.getBrand(), p.getModel(), p.getMaxCapacity(), p.getAirline(), p.getNumFlights()
                 };
                 model.addRow(fila);
             }
        }
        return model;
    }
    
}
