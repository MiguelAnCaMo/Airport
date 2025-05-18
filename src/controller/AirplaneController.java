package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Plane;

/**
 *
 * @author migue
 */
public class AirplaneController {
    //lista de aviones
    private static ArrayList <Plane> planes = new ArrayList<>();
    
    public AirplaneController() {
        
    }
    //esto crea un avion
    public Plane createPlane(String id, String brand, String model, int maxCapacity, String Airline) {
        Plane plane = new Plane(id, brand, model, maxCapacity, Airline);
        planes.add(plane);
        return plane;
    }

    public static ArrayList<Plane> getPlanes() {
        return planes;
    }
    
    //funcion que devuelve un modelo para poner a la lista de aviones en la interfaz
    public DefaultTableModel toPlanesJList() {
        String[] columnas = {"ID", "Model", "Model", "Max Capacity", "Airline", "Number Flights"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); //modelo para ser devuelto
        
        if (planes.isEmpty()) {
            System.out.println("Lista de aviones vacia");
        } else {
             for (Plane p : planes) {
                 Object[] fila = new Object[] { //objeto para poner en el modelo
                    p.getId(), p.getBrand(), p.getModel(), p.getMaxCapacity(), p.getAirline(), p.getNumFlights()
                 };
                 model.addRow(fila);
             }
        }
        return model;
    }
    
}
